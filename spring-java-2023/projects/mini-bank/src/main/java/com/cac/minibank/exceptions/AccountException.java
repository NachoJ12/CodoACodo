package com.cac.minibank.exceptions;

public class AccountException extends Exception{

    public AccountException(MessageError message) {
        super(message.message);
    }

}
