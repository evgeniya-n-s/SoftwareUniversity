package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.Picture.PictureImportDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.instagraphlite.constants.Paths.PICTURES_JSON_PATH;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Gson gson;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, Validator validator, Gson gson) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count()>0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(PICTURES_JSON_PATH);
    }

    @Override
    public String importPictures() throws IOException {
        String json = this.readFromFileContent();
        PictureImportDto[] pictureImportDtos = this.gson.fromJson(json, PictureImportDto[].class);
        return Arrays.stream(pictureImportDtos).map(this::importPicture).collect(Collectors.joining("\n"));
    }

    private String importPicture(PictureImportDto dto) {
        Set<ConstraintViolation<PictureImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid Picture";
        }

        Optional<Picture> checkPath = this.pictureRepository.findPictureByPath(dto.getPath());

        if(checkPath.isPresent()){
            return "Invalid Picture Path";
        }

        Picture picture = this.modelMapper.map(dto, Picture.class);
        this.pictureRepository.save(picture);

        String message = String.format("Successfully imported Picture, with size %.2f",picture.getSize());
        return message;
    }

    @Override
    public String exportPictures() {
        double size = 30000;
        List<Picture> pictures = this.pictureRepository.findAllBySizeGreaterThanOrderBySizeAsc(size);
        return pictures.stream().map(Picture::toString).collect(Collectors.joining("\n"));
    }
}
