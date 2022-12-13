package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car;
import softuni.exam.models.Offer;
import softuni.exam.models.Seller;
import softuni.exam.models.dto.Offer.OfferImportDto;
import softuni.exam.models.dto.Offer.OfferImportWrapperDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constans.Paths.OFFERS_XML_PATH;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    public OfferServiceImpl(OfferRepository offerRepository, CarRepository carRepository, SellerRepository sellerRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(OfferImportWrapperDto.class);
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
        OfferImportWrapperDto offerImportWrapperDto = (OfferImportWrapperDto) this.unmarshaller.unmarshal(OFFERS_XML_PATH.toFile());

        return offerImportWrapperDto.getOffers().stream().map(this::importOffer).collect(Collectors.joining("\n"));
    }

    private String importOffer(OfferImportDto dto) {
        Set<ConstraintViolation<OfferImportDto>> validateErrors = this.validator.validate(dto);
        if (!validateErrors.isEmpty()){
            return "Invalid offer";
        }

        Optional<Car> checkCarId = this.carRepository.findCarById(dto.getCar().getId());
        Optional<Seller> checkSellerId = this.sellerRepository.findById(dto.getSeller().getId());

        if(!checkCarId.isPresent()){
            return "Invalid offer";
        }

        if(!checkSellerId.isPresent()){
            return "Invalid offer";
        }

        Offer offer = this.modelMapper.map(dto, Offer.class);
        offer.setCar(checkCarId.get());
        offer.setSeller(checkSellerId.get());
        this.offerRepository.save(offer);

        String message = String.format("Successfully import offer %tT - %b",offer.getAddedOn(),offer.isHasGoldStatus());
        return message;
    }
}
