package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.User.UserImportDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.instagraphlite.constants.Paths.USERS_JSON_PATH;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Gson gson;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PictureRepository pictureRepository, ModelMapper modelMapper, Validator validator, Gson gson) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.userRepository.count()>0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(USERS_JSON_PATH);
    }

    @Override
    public String importUsers() throws IOException {
        String json = this.readFromFileContent();
        UserImportDto[] userImportDtos = this.gson.fromJson(json, UserImportDto[].class);

        return Arrays.stream(userImportDtos).map(this::importUser).collect(Collectors.joining("\n"));
    }

    private String importUser(UserImportDto dto) {
        Set<ConstraintViolation<UserImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid Users";
        }

        Optional<Picture> checkPicture = this.pictureRepository.findPictureByPath(dto.getProfilePicture());
        if(!checkPicture.isPresent()){
            return "Invalid Picture Profile";
        }
        User user = this.modelMapper.map(dto, User.class);
        user.setPicture(checkPicture.get());
        this.userRepository.save(user);

        String message = String.format("Successfully imported User: %s",user.getUserName());
        return message;
    }

    @Override
    public String exportUsersWithTheirPosts() {
        List<User> users = this.userRepository.findAllByOrderByPosts();
        return users.stream().map(User::toString).collect(Collectors.joining("\n"));
    }
}
