package com.academy.demo.mapper;

import com.academy.demo.entity.User;
import com.academy.demo.security.dto.UserForRegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    User toUser(UserForRegistrationDTO userForRegistrationDTO);

}
