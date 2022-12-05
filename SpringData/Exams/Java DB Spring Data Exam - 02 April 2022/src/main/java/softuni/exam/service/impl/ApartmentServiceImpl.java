package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Apartments.ApartmentImportDTO;
import softuni.exam.models.dto.Apartments.ApartmentImportWrapperDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;

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

import static softuni.exam.constants.Paths.APARTMENTS_XML_PATH;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(ApartmentImportWrapperDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count()>0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(APARTMENTS_XML_PATH);
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        FileReader fileReader = new FileReader(APARTMENTS_XML_PATH.toFile());
        ApartmentImportWrapperDTO apartmentDTO = (ApartmentImportWrapperDTO) this.unmarshaller.unmarshal(fileReader);

        return apartmentDTO.getApartments().stream().map(this::importApartment).collect(Collectors.joining("\n"));
    }

    private String importApartment(ApartmentImportDTO dto) {
        Set<ConstraintViolation<ApartmentImportDTO>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid apartment";
        }

        Optional<Apartment> checkApartmentExist = this.apartmentRepository.findByTown_TownNameAndArea(dto.getTown(),dto.getArea());
       // apartmentRepository.findApartmentByAreaAndTown(dto.getArea(),townRepository.findByTownName(dto.getTown()));
        if(checkApartmentExist.isPresent()) {
            return "Invalid apartment";
        }

        Optional<Town> townName = this.townRepository.findByTownName(dto.getTown());
        Apartment apartment = this.modelMapper.map(dto, Apartment.class);
        apartment.setTown(townName.get());

        this.apartmentRepository.save(apartment);
        String message = String.format("Successfully imported apartment %s - %.2f",apartment.getApartmentType().toString(),apartment.getArea());
        return message;
    }
}
