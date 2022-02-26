package com.academy.demo.mapper;

import com.academy.demo.dto.RoleDTO;
import com.academy.demo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDTO(Role role);

}
