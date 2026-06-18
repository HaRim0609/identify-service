package com.identify.identify_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.identify.identify_service.dto.request.UserCreationRequest;
import com.identify.identify_service.dto.request.UserUpdateRequest;
import com.identify.identify_service.entity.User;
import com.identify.identify_service.exception.AppException;
import com.identify.identify_service.exception.ErrorCode;
import com.identify.identify_service.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    // Create user
    public User createUser(UserCreationRequest request) {
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);
            
        
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDab(request.getDab());

        return userRepository.save(user);
    }

    // List user
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //  Get user by id
    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


    //  Update user
    public User updateUser(long userId, UserUpdateRequest request){
        User user = getUser(userId);

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDab(request.getDab());

        return userRepository.save(user);

    }

    // Delete user
    public void deleteUserById(long userId){
        userRepository.deleteById(userId);
    }


}
