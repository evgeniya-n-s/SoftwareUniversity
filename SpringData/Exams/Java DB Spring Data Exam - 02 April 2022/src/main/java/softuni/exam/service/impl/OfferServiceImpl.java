package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Offers.OfferImportDTO;
import softuni.exam.models.dto.Offers.OfferImportWrapperDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.enums.ApartmentType;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constants.Paths.*;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final AgentRepository agentRepository;
    private final ApartmentRepository apartmentRepository;
    private final ModelMapper modelMapper;

    private final Unmarshaller unmarshaller;
    private final Validator validator;

    public OfferServiceImpl(OfferRepository offerRepository, AgentRepository agentRepository, ApartmentRepository apartmentRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(OfferImportWrapperDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count()>0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(OFFERS_XML_PATH);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        FileReader fileReader = new FileReader(OFFERS_XML_PATH.toFile());
        OfferImportWrapperDTO offerDTO = (OfferImportWrapperDTO) this.unmarshaller.unmarshal(fileReader);
        
        return offerDTO.getOffers().stream().map(this::importOffer).collect(Collectors.joining("\n"));
    }

    private String importOffer(OfferImportDTO dto) {
        Set<ConstraintViolation<OfferImportDTO>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid offer";
        }

        Optional<Agent> checkAgentName = this.agentRepository.findAgentByFirstName(dto.getAgent().getName());
        if(checkAgentName.isEmpty()){
            return "Invalid offer";
        }

        Offer offer = this.modelMapper.map(dto, Offer.class);
        Optional<Apartment> apartment = this.apartmentRepository.findById(dto.getApartment().getId());
        offer.setApartment(apartment.get());
        offer.setAgent(checkAgentName.get());

        this.offerRepository.save(offer);

        String message = String.format("Successfully imported offer %.2f",offer.getPrice());
        return message;
    }

    @Override
    public String exportOffers() {
//•	Filter only three_rooms apartments and order them by the area in descending order, then by the price in ascending order.
        List<Offer> offers = this.offerRepository.findAllByApartment_ApartmentTypeOrderByApartment_AreaDescPriceAsc(ApartmentType.three_rooms);

        return offers.stream().map(Offer::toString).collect(Collectors.joining("\n"));
    }
}
