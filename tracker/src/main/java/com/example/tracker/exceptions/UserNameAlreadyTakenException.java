package com.example.tracker.exceptions;


public class UserNameAlreadyTakenException extends RuntimeException{
    public UserNameAlreadyTakenException(String message) {
        super(message);
    }
}