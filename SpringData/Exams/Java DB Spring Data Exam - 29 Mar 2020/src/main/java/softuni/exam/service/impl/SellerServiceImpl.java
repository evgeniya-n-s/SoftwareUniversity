package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Seller;
import softuni.exam.models.dto.Seller.SellerImportDto;
import softuni.exam.models.dto.Seller.SellerImportWrapperDto;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constans.Paths.SELLERS_XML_PATH;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(SellerImportWrapperDto.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count()>0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(SELLERS_XML_PATH);
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        FileReader fileReader = new FileReader(SELLERS_XML_PATH.toFile());
        SellerImportWrapperDto sellerImportWrapperDto = (SellerImportWrapperDto) this.unmarshaller.unmarshal(fileReader);

        return sellerImportWrapperDto.getSeller().stream().map(this::importSeller).collect(Collectors.joining("\n"));
    }

    private String importSeller(SellerImportDto dto) {
        Set<ConstraintViolation<SellerImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid seller";
        }

        Seller seller = this.modelMapper.map(dto, Seller.class);
        this.sellerRepository.save(seller);

        String message = String.format("Successfully import seller %s - %s",seller.getLastName(),seller.getEmail());
        return message;
    }
}
