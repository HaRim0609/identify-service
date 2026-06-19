package com.identify.identify_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identify.identify_service.dto.request.ApiResponse;
import com.identify.identify_service.dto.request.AuthenticationRequest;
import com.identify.identify_service.dto.request.IntrospecRequest;
import com.identify.identify_service.dto.response.AuthenticationResponse;
import com.identify.identify_service.dto.response.IntrospecResponse;
import com.identify.identify_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("Đăng nhập thành công")
                .result(result)
                .build();
    }
    
    @PostMapping("/introspect")
    ApiResponse<IntrospecResponse> authenticate(@RequestBody IntrospecRequest request) throws JOSEException, ParseException{
        
        var result = authenticationService.introspec(request);
        return ApiResponse.<IntrospecResponse>builder()
                .message("Verified token")
                .result(result)
                .build();
    
    }
    
}
