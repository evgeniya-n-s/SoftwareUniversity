package exam.service.impl;

import com.google.gson.Gson;
import exam.model.Customer;
import exam.model.Town;
import exam.model.dto.Customer.CustomerImportDto;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static exam.constants.Paths.CUSTOMERS_JSON_PATH;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Gson gson;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository, ModelMapper modelMapper, Validator validator, Gson gson) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count()>0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(CUSTOMERS_JSON_PATH);
    }

    @Override
    public String importCustomers() throws IOException {
        String json = this.readCustomersFileContent();
        CustomerImportDto[] customerImportDtos = this.gson.fromJson(json, CustomerImportDto[].class);
        return Arrays.stream(customerImportDtos).map(this::importCustomer).collect(Collectors.joining("\n"));
    }

    private String importCustomer(CustomerImportDto dto) {
        Set<ConstraintViolation<CustomerImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid Customer";
        }
        Optional<Customer> checkCustomerEmail = this.customerRepository.findCustomerByEmail(dto.getEmail());
        if(checkCustomerEmail.isPresent()){
            return "Invalid Customer";
        }
        Optional<Town> townName = this.townRepository.findTownByName(dto.getTown().getName());
        Customer customer = this.modelMapper.map(dto, Customer.class);
        customer.setTown(townName.get());
        this.customerRepository.save(customer);
        String message = String.format("Successfully imported Customer %s %s - %s",customer.getFirstName(),customer.getLastName(),customer.getEmail());
        return message;
    }
}
