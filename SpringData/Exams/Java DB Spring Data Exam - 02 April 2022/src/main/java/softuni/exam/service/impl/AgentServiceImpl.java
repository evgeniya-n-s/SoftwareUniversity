package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Agents.AgentImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constants.Paths.*;

@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository, ModelMapper modelMapper, Gson gson, Validator validator) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count()>0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(AGENTS_JSON_PATH);
    }

    @Override
    public String importAgents() throws IOException {
        String json = this.readAgentsFromFile();
        AgentImportDTO[] agentImportDTOS = this.gson.fromJson(json, AgentImportDTO[].class);
        return Arrays.stream(agentImportDTOS).map(this::importAgent).collect(Collectors.joining("\n"));
    }

    private String importAgent(AgentImportDTO dto) {
        Set<ConstraintViolation<AgentImportDTO>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid agent";
        }
        Optional<Agent> checkAgentName = this.agentRepository.findAgentByFirstName(dto.getFirstName());
        if(checkAgentName.isPresent()){
            return "Invalid agent";
        }
        Agent agent = this.modelMapper.map(dto, Agent.class);
        Optional<Town> town = this.townRepository.findByTownName(dto.getTown());
        agent.setTown(town.get());
        this.agentRepository.save(agent);
        String message = String.format("Successfully imported agent - %s %s",agent.getFirstName(),agent.getLastName());
        return message;
    }
}
