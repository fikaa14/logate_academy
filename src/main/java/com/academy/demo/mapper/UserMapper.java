package com.academy.demo.mapper;

import com.academy.demo.dto.UserDTO;
import com.academy.demo.entity.User;
import com.academy.demo.security.dto.UserForRegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    
    @Mapping(source = "usename", target = "username")
    UserDTO toUserDTO(User user);

}
