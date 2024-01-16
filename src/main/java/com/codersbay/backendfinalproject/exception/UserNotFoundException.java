package com.codersbay.backendfinalproject.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("Could not find the user!");
    }
}



