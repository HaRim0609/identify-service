package com.identify.identify_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.identify.identify_service.dto.request.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> HandlingRuntimeException(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(1001);
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> HandlingAppException(AppException exception){        
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<String> handlingValidation(MethodArgumentNotValidException exception){
        return ResponseEntity.badRequest().body(exception.getFieldError().getDefaultMessage());
    }
}
