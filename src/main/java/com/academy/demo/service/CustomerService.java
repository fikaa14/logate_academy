package com.academy.demo.service;

import com.academy.demo.dto.CustomerWithAdressDTO;
import com.academy.demo.entity.Customer;
import com.academy.demo.mapper.CustomerMapper;
import com.academy.demo.repository.CustomerRepository;
import com.academy.demo.search.CustomerSearch;
import com.academy.demo.specs.CustomerSearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    public Page<CustomerWithAdressDTO> getCustomers(Pageable pageable)
    {
        Page<Customer> customerPage = customerRepository.getAll(pageable);
        List<Customer> customerList = customerPage.getContent();
        List<CustomerWithAdressDTO> customers = new LinkedList<>();

        for(Customer customer:customerList)
        {
            customers.add(customerMapper.toAddressDTO(customer));
        }

        return new PageImpl<>(customers, pageable, customerPage.getTotalElements());
    }

    public List<Customer> search(CustomerSearchSpecification customerSearchSpecification)
    {
        List<Customer> resultSet = customerRepository.findAll(customerSearchSpecification);
//        List<CustomerWithAdressDTO> customers = resultSet.stream()
//                .map(customerMapper::toAddressDTO).collect(Collectors.toList());

        return customerRepository.findAll(customerSearchSpecification);
        //SELECT * FROM customer WHERE...
    }
}
