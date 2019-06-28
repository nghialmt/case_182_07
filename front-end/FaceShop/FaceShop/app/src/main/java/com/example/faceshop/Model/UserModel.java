package com.example.faceshop.Model;

import java.util.List;

public class UserModel {
    private boolean success;
    private String message;

    public UserModel() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
