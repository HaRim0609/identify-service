package com.identify.identify_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.identify.identify_service.dto.request.UserCreationRequest;
import com.identify.identify_service.dto.request.UserUpdateRequest;
import com.identify.identify_service.dto.response.UserResponse;
import com.identify.identify_service.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUpResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
