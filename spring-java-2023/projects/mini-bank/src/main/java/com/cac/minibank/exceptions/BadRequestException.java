package com.cac.minibank.exceptions;

public class BadRequestException extends Exception{
    public BadRequestException(MessageError message) {
        super(message.message);
    }

}
