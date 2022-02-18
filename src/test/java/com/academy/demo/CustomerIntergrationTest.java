package com.academy.demo;

import com.academy.demo.dto.CustomerWithAdressDTO;
import com.academy.demo.entity.Adress;
import com.academy.demo.entity.Customer;
import com.academy.demo.mapper.CustomerMapper;
import com.academy.demo.projection.CustomerProjection;
import com.academy.demo.projection.CustomerWithAddressInCityProjection;
import com.academy.demo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest(classes = DemoAcademyApplication.class)
public class CustomerIntergrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findByPhoneNumberStartingWith()
    {
        Pageable pageable = PageRequest.of(0, 2);

        Page<Customer> customers = customerRepository.findCustomersWithPhoneNumberStartingWith("069", pageable);

        if(customers.hasContent())
        {
            List<Customer> customerList = customers.getContent();
            long totalElemnts = customers.getTotalElements();
            int totalPages = customers.getTotalPages();
            log.info("Customers: {}", customerList);
            log.info("Total Elements: {}", totalElemnts);
            log.info("Total Pages: {}", totalPages);
        }
    }

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void findCustomersWithAddresses()
    {
        Pageable pageable = PageRequest.of(0, 3);

        Page<Integer> customersIds = customerRepository.findCustomerIds(pageable);
        assertThat(customersIds).isNotNull().isNotEmpty();

        List<Customer> customers = customerRepository.findAllByIdIn(customersIds.getContent());
        assertThat(customers).isNotNull().isNotEmpty();

        List<CustomerWithAdressDTO> customersWithAddresses = customers
            .stream()
            .map(customerMapper::toAddressDTO) // new CustomerWithAddressDTO(customer)
            .collect(Collectors.toList());

        customersWithAddresses.forEach(customer -> log.info("Customer: {}", customer));
    }

    @Test
    public void findCustomerWithAddressInCity()
    {
        List<CustomerWithAddressInCityProjection> customers =
                customerRepository.findAllByAddressInCity("Podgorica");
        for(CustomerWithAddressInCityProjection customer: customers)
        {
            String fullName = customer.getFullName();
            String email = customer.getEmail();
            String cityName = customer.getCityName();
            String countryName = customer.getCountryName();
            String addressStreet = customer.getAddressStreet();

            log.info("Customer full name: {}, email: {}, City: {}, Country: {}, Adress Street: {}",
                    fullName, email, cityName, countryName, addressStreet);
        }
    }

    @Test
    public void findCustomersInChina()
    {
        Pageable pageable = PageRequest.of(0, 15);

        Page<Customer> customers = customerRepository.findCustomerByAddressInChinaUsingJPQL(pageable);

        if(customers.hasContent())
        {
            List<Customer> customerList = customers.getContent();
            long totalElemnts = customers.getTotalElements();
            int totalPages = customers.getTotalPages();
            log.info("Customers: {}", customerList);
            log.info("Total Elements: {}", totalElemnts);
            log.info("Total Pages: {}", totalPages);
        }
        else
        {
            log.info("List is empty");
        }
    }

    @Test
    public void findCustomersInChinaSQL()
    {
        Pageable pageable = PageRequest.of(0, 15);

        Page<Customer> customers = customerRepository.findCustomerByAddressInChinaUsingSQL(pageable);

        if(customers.hasContent())
        {
            List<Customer> customerList = customers.getContent();
            long totalElemnts = customers.getTotalElements();
            int totalPages = customers.getTotalPages();
            log.info("Customers: {}", customerList);
            log.info("Total Elements: {}", totalElemnts);
            log.info("Total Pages: {}", totalPages);
        }
        else
        {
            log.info("List is empty");
        }
    }

    @Test
    public void findCustomerWithPhoneNumber()
    {
        Integer customerId = customerRepository.findCustomerIdWithPhoneNumber("069274226");

        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent())
        {
            log.info("Customer: {}", customer);
        }

    }

    @Test
    public void findAllCustomersWithUpperFullNameTest()
    {
        List<String> customerFullName = customerRepository.findAllCustomerWithUpperName();
        customerFullName.forEach(fullName -> log.info("Full name: {}", fullName));
    }

    @Test
    @Transactional
    public void findAllCustomersUsingStoredProcedureTest()
    {
        List<CustomerProjection> customerProjections = customerRepository.findAllCustomersUsingStoredProcedure();
        customerProjections.forEach(
                customer -> log.info("ID: {} | FULL NAME: {} | PHONE NUMBER: {} | EMAIL: {}",
                        customer.getId(), customer.getFullName(), customer.getPhone(), customer.getEmail())
        );
    }

    @Test
    @Transactional
    public void fundAllCustomersByEmailDomainUsingStoredProcedureTest()
    {
        List<CustomerProjection> customerProjections = customerRepository
                .findAllCustomersByEmailPartUsingStoredProcedureTest("ail");
         customerProjections.forEach(
                customer -> log.info("ID: {} | FULL NAME: {} | PHONE NUMBER: {} | EMAIL: {}",
                        customer.getId(), customer.getFullName(), customer.getPhone(), customer.getEmail())
        );
    }

    @Test
    @Transactional
    public void findCustomerIdByPhoneNumberUsingStoredProcedure()
    {
        Integer customerId = customerRepository.findCustomerIdWithPhoneNumber("069274226");
        log.info("Customer id: {}", customerId);
    }
}
