package com.example.apidemo.api;

public class ApiResponse {
    private String status;
    private String message;

    // Constructor
    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getter methods
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
