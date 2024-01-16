package com.codersbay.backendfinalproject.exception;

public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(Long id){
        super("Could not found the contact with id " + id);
    }



}
