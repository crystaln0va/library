package com.example.BookCatalog;

public class CustomErrorType {
    private String errorMessage;

    public CustomErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    String getErrorMessage() {
        return errorMessage;
    }
}
