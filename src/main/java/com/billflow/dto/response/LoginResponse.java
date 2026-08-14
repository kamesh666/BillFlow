package com.billflow.dto.response;

public class LoginResponse {
    private String message;

    public LoginResponse(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
