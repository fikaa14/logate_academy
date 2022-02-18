package com.academy.demo.controller;

import com.academy.demo.dto.CustomerWithAdressDTO;
import com.academy.demo.entity.Customer;
import com.academy.demo.search.CustomerSearch;
import com.academy.demo.service.CustomerService;
import com.academy.demo.specs.CustomerSearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    //@PreAuthorize("@customAuth.hasPermission()")
    @PreAuthorize("@customAuth.hasPermissionBasedOnSomething(#authKey)")
    //@Secured("MANAGER") <=> @PreAuthorize("hasRole('ADMIN')") <=> security hasRole()
    public ResponseEntity<Page<CustomerWithAdressDTO>> getCustomers(
            Pageable pageable, @RequestHeader(value = "Authorization") String authKey)
    {
        Page<CustomerWithAdressDTO> customers = customerService.getCustomers(pageable);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping(value = "search")
    public ResponseEntity<List<Customer>> search(CustomerSearch customerSearch)
    {
        CustomerSearchSpecification customerSearchSpecification = new CustomerSearchSpecification(customerSearch);

        List<Customer> customers = customerService.search(customerSearchSpecification);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
