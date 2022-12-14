package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Passenger;
import softuni.exam.models.Plane;
import softuni.exam.models.Ticket;
import softuni.exam.models.Town;
import softuni.exam.models.dto.Ticket.TicketImportDto;
import softuni.exam.models.dto.Ticket.TicketImportWrapperDto;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;

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

import static softuni.exam.constants.Paths.TICKETS_XML_PATH;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TownRepository townRepository;
    private final PassengerRepository passengerRepository;
    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, TownRepository townRepository, PassengerRepository passengerRepository, PlaneRepository planeRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.ticketRepository = ticketRepository;
        this.townRepository = townRepository;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(TicketImportWrapperDto.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count()>0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(TICKETS_XML_PATH);
    }

    @Override
    public String importTickets() throws FileNotFoundException, JAXBException {
        FileReader fileReader = new FileReader(TICKETS_XML_PATH.toFile());
        TicketImportWrapperDto ticketImportWrapperDto = (TicketImportWrapperDto) this.unmarshaller.unmarshal(fileReader);

        return ticketImportWrapperDto.getTickets().stream().map(this::importTicket).collect(Collectors.joining("\n"));
    }

    private String importTicket(TicketImportDto dto) {
        Set<ConstraintViolation<TicketImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid Ticket";
        }
        Optional<Town> checkTownFrom = this.townRepository.findTownByName(dto.getTownFrom().getName());
        Optional<Town> checkTownTo = this.townRepository.findTownByName(dto.getTownTo().getName());
        Optional<Passenger> checkPassengerEmail =this.passengerRepository.findPassengerByEmail(dto.getPassenger().getEmail());
        Optional<Plane> checkPlaneRegisterNumber = this.planeRepository.findPlaneByRegisterNumber(dto.getPlane().getRegisterNumber());

        if(!checkTownFrom.isPresent()){
            return "Invalid Ticket";
        }
        if (!checkTownTo.isPresent()){
            return "Invalid Ticket";
        }

        if(!checkPassengerEmail.isPresent()){
            return "Invalid Ticket";
        }

        if(!checkPlaneRegisterNumber.isPresent()){
            return "Invalid Ticket";
        }

        Ticket ticket = this.modelMapper.map(dto, Ticket.class);
        ticket.setFromTown(checkTownFrom.get());
        ticket.setToTown(checkTownTo.get());
        ticket.setPassenger(checkPassengerEmail.get());
        ticket.setPlane(checkPlaneRegisterNumber.get());

        this.ticketRepository.save(ticket);

        String message = String.format("Successfully imported Ticket %s - Los Angels",ticket.getToTown().getName(),ticket.getFromTown().getName());
        return message;
    }
}
