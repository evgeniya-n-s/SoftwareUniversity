package com.example.football.service.impl;

import com.example.football.models.dto.Stat.StartImportWrapperDTO;
import com.example.football.models.dto.Stat.StatImportDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
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

import static com.example.football.constants.Path.*;

@Service
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;
    private final Unmarshaller unmarshaller;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public StatServiceImpl(StatRepository statRepository) throws JAXBException {
        this.statRepository = statRepository;
        this.modelMapper = new ModelMapper();

        JAXBContext context = JAXBContext.newInstance(StartImportWrapperDTO.class);
        this.unmarshaller = context.createUnmarshaller();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() >0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(STATS_XML_PATH);
    }

    @Override
    public String importStats() throws FileNotFoundException, JAXBException {
        StartImportWrapperDTO statDTO = (StartImportWrapperDTO)
                this.unmarshaller.unmarshal(new FileReader(STATS_XML_PATH.toAbsolutePath().toString()));

        return statDTO.getStats().stream().map(this::importStat).collect(Collectors.joining("\n"));
    }

    private String importStat(StatImportDTO dto) {
        Set<ConstraintViolation<StatImportDTO>> errors = this.validator.validate(dto);

        if(!errors.isEmpty()){
            return "Invalid Stat";
        }

        Optional<Stat> statOptional=this.statRepository.findByShootingAndPassingAndEndurance(dto.getShooting(),dto.getPassing(),dto.getEndurance());

        if(statOptional.isPresent()){
            return "Invalid Stat";
        }

        Stat stat = this.modelMapper.map(dto, Stat.class);
        this.statRepository.save(stat);
        return "Successfully imported Stat " + stat;
    }
}
