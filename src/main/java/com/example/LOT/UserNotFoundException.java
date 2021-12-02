package com.example.LOT;

public class UserNotFoundException extends RuntimeException {

    private final Code code = Code.ACCOUNT_NOT_FOUND;

    public Code getCode() {
        return code;
    }
}
