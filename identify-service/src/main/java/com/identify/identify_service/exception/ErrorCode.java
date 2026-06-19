package com.identify.identify_service.exception;

public enum ErrorCode {
    
    UNCATEGORIZED_EXCEPTION(9999, "Uncategoried error"),
    INVALID_KEY(1001, "Invalid key"),
    USERNAME_INVALID(1002, "Username must be at least 3 charaters"),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters"),
    USER_EXISTED(1004, "User existed"),
    USER_NOT_EXISTED(1005, "User not existed")
    ;

    private int code;
    private String message;
    // Contructor
    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    
}
