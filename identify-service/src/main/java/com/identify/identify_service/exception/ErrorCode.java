package com.identify.identify_service.exception;

public enum ErrorCode {
    
    USER_EXISTED(1003, "Hoàng Hà đẹp trái");

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
