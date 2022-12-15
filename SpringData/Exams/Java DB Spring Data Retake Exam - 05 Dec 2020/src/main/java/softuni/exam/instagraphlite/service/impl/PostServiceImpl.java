package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.Post.PostImportDto;
import softuni.exam.instagraphlite.models.dto.Post.PostImportWrapperDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.instagraphlite.constants.Paths.POSTS_XML_PATH;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, PictureRepository pictureRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(PostImportWrapperDto.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.postRepository.count()>0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(POSTS_XML_PATH);
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        FileReader fileReader=new FileReader(POSTS_XML_PATH.toFile());
        PostImportWrapperDto postImportWrapperDto = (PostImportWrapperDto) this.unmarshaller.unmarshal(fileReader);
        return postImportWrapperDto.getPosts().stream().map(this::importPost).collect(Collectors.joining("\n"));
    }

    private String importPost(PostImportDto dto) {
        Set<ConstraintViolation<PostImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid Post";
        }

        Optional<User> checkUserUserName = this.userRepository.findUserByUserName(dto.getUser().getUserName());

        if(!checkUserUserName.isPresent()){
            return "Invalid User name";
        }

        Optional<Picture> checkPicturePath = this.pictureRepository.findPictureByPath(dto.getPicture().getPath());

        if(!checkPicturePath.isPresent()){
            return "Invalid Picture path";
        }

        Post post = this.modelMapper.map(dto, Post.class);
        post.setUser(checkUserUserName.get());
        post.setPicture(checkPicturePath.get());
        this.postRepository.save(post);

        String message = String.format("Successfully imported Post, made by %s",post.getUser().getUserName());
        return message;
    }
}
