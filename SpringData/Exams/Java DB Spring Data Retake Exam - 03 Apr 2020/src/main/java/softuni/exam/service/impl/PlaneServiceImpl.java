package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Plane;
import softuni.exam.models.dto.Plane.PlaneImportDto;
import softuni.exam.models.dto.Plane.PlaneImportWrapperDto;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constants.Paths.PLANES_XML_PATH;

@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(PlaneImportWrapperDto.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count()>0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(PLANES_XML_PATH);
    }

    @Override
    public String importPlanes() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(PLANES_XML_PATH.toFile());
        PlaneImportWrapperDto planeImportWrapperDto = (PlaneImportWrapperDto) this.unmarshaller.unmarshal(fileReader);
        return planeImportWrapperDto.getPlanes().stream().map(this::importPlane).collect(Collectors.joining("\n"));
    }

    private String importPlane(PlaneImportDto dto) {
        Set<ConstraintViolation<PlaneImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid Plane";
        }
        Plane plane = this.modelMapper.map(dto, Plane.class);
        this.planeRepository.save(plane);

        String message = String.format("Successfully imported Plane %s",plane.getRegisterNumber());
        return message;
    }
}
