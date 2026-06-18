package com.identify.identify_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.identify.identify_service.dto.request.UserCreationRequest;
import com.identify.identify_service.dto.request.UserUpdateRequest;
import com.identify.identify_service.dto.response.UserResponse;
import com.identify.identify_service.entity.User;
import com.identify.identify_service.exception.AppException;
import com.identify.identify_service.exception.ErrorCode;
import com.identify.identify_service.mapper.UserMapper;
import com.identify.identify_service.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor 
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;

    
    UserMapper userMapper;
    
    // Create user
    public User createUser(UserCreationRequest request) {
        

        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);
            
        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    // List user
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //  Get user by id
    public UserResponse getUser(Long id){
        return userMapper.toUpResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }


    //  Update user
    public UserResponse updateUser(long userId, UserUpdateRequest request){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);

        return userMapper.toUpResponse(userRepository.save(user));

    }

    // Delete user
    public void deleteUserById(long userId){
        userRepository.deleteById(userId);
    }


}
