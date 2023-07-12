package com.cac.minibank.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(MessageError message) {
        super(message.message);
    }
}