package exam.service.impl;

import exam.model.Shop;
import exam.model.Town;
import exam.model.dto.Shop.ShopImportDto;
import exam.model.dto.Shop.ShopImportWrapperDto;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
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

import static exam.constants.Paths.SHOPS_XML_PATH;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(ShopImportWrapperDto.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count()>0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(SHOPS_XML_PATH);
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        FileReader fileReader = new FileReader(SHOPS_XML_PATH.toFile());
        ShopImportWrapperDto shopDto = (ShopImportWrapperDto) this.unmarshaller.unmarshal(fileReader);

        return shopDto.getShops().stream().map(this::importShop).collect(Collectors.joining("\n"));
    }

    private String importShop(ShopImportDto dto) {
        Set<ConstraintViolation<ShopImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid shop";
        }
        Optional<Shop> checkShopName = this.shopRepository.findShopByName(dto.getName());
        if(checkShopName.isPresent()){
            return "Invalid shop";
        }
        Optional<Town> townName = this.townRepository.findTownByName(dto.getTown().getName());
        Shop shop = this.modelMapper.map(dto, Shop.class);
        shop.setTown(townName.get());
        this.shopRepository.save(shop);
        String message = String.format("Successfully imported Shop %s - %f",shop.getName(),shop.getIncome());
        return message;
    }
}
