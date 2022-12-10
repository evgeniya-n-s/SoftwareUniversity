package exam.service.impl;

import exam.model.Town;
import exam.model.dto.Town.TownImportDto;
import exam.model.dto.Town.TownImportWrapperDto;
import exam.repository.TownRepository;
import exam.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static exam.constants.Paths.TOWNS_XML_PATH;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(TownImportWrapperDto.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count()>0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(TOWNS_XML_PATH);
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        FileReader fileReader = new FileReader(TOWNS_XML_PATH.toFile());
        TownImportWrapperDto townDto = (TownImportWrapperDto) this.unmarshaller.unmarshal(fileReader);

        return townDto.getTowns().stream().map(this::importTown).collect(Collectors.joining("\n"));
    }

    private String importTown(TownImportDto dto) {
        Set<ConstraintViolation<TownImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid town";
        }
        Optional<Town> checkTown = this.townRepository.findTownByName(dto.getName());
        if(checkTown.isPresent()){
            return "Town name already exists";
        }
        Town town = this.modelMapper.map(dto, Town.class);
        this.townRepository.save(town);
        String message = String.format("Successfully imported Town %s",town.getName());
        return message;
    }
}
