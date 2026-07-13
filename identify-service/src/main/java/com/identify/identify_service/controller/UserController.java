package com.identify.identify_service.controller;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.identify.identify_service.dto.request.ApiResponse;
import com.identify.identify_service.dto.request.UserCreationRequest;
import com.identify.identify_service.dto.request.UserUpdateRequest;
import com.identify.identify_service.dto.response.UserResponse;
import com.identify.identify_service.entity.User;
import com.identify.identify_service.service.UserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.extern.slf4j.Slf4j;
// import org.springframework.web.bind.annotation.RequestParam;



@RestController // Annotation này đánh dấu class là một REST controller, cho phép nó xử lý các yêu cầu HTTP và trả về dữ liệu dưới dạng JSON hoặc XML.
// Annotation này định nghĩa đường dẫn cơ sở cho tất cả các phương thức trong controller. Trong trường hợp này, tất cả các phương thức sẽ được truy cập thông qua đường dẫn /users. 
@RequestMapping("/users")
@RequiredArgsConstructor 
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    // @Autowired // Annotation này cho phép Spring tự động tiêm (inject) một instance của UserService vào controller. 
    UserService userService;
    
    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        apiResponse.setMessage("Tạo người dùng thành công");
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<User>> getUsers(){
        // Get information of the currently authenticated user from the security context
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info((grantedAuthority.getAuthority())));
        return ApiResponse.<List<User>>builder()
                .result(userService.getAllUsers())
                .build();
    }
    
    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") long userID){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userID))
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable("userId") long userId, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("{userId}")
    String deleteUser(@PathVariable("userId") long userId){
        userService.deleteUserById(userId);
        return "User has been deleted";
    }    
}
