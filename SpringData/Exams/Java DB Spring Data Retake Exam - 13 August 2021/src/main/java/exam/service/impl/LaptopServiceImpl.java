package exam.service.impl;

import com.google.gson.Gson;
import exam.model.Laptop;
import exam.model.Shop;
import exam.model.dto.Laptop.LaptopImportDto;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static exam.constants.Paths.LAPTOPS_JSON_PATH;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepository laptopRepository;
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository, ModelMapper modelMapper, Gson gson, Validator validator) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count()>0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(LAPTOPS_JSON_PATH);
    }

    @Override
    public String importLaptops() throws IOException {
        String json = this.readLaptopsFileContent();
        LaptopImportDto[] laptopImportDtos = this.gson.fromJson(json, LaptopImportDto[].class);

        return Arrays.stream(laptopImportDtos).map(this::importLaptop).collect(Collectors.joining("\n"));
    }

    private String importLaptop(LaptopImportDto dto) {
        Set<ConstraintViolation<LaptopImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid Laptop";
        }
        Optional<Laptop> checkLaptopMacAddress = this.laptopRepository.findLaptopByMacAddress(dto.getMacAddress());
        if(checkLaptopMacAddress.isPresent()){
            return "Invalid Laptop";
        }
        Optional<Shop> shopName = this.shopRepository.findShopByName(dto.getShop().getName());
        Laptop laptop = this.modelMapper.map(dto, Laptop.class);
        laptop.setShop(shopName.get());
        this.laptopRepository.save(laptop);
        String message = String.format("Successfully imported Laptop %s - %.2f - %d - %d",laptop.getMacAddress(),laptop.getCpuSpeed(),laptop.getRam(),laptop.getStorage());
        return message;
    }

    @Override
    public String exportBestLaptops() {
        List<Laptop> laptop = this.laptopRepository.findAllByOrderByCpuSpeedDescRamDescStorageDescMacAddressAsc();
        return laptop.stream().map(Laptop::toString).collect(Collectors.joining("\n"));
    }
}
