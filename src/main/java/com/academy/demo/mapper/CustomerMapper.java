package com.academy.demo.mapper;

import com.academy.demo.dto.CustomerWithAdressDTO;
import com.academy.demo.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerMapper
{
    @Mapping(source = "fullName", target = "name")
    CustomerWithAdressDTO toAddressDTO(Customer customer);
}