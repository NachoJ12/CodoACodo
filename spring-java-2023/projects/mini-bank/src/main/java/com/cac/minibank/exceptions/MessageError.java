package com.cac.minibank.exceptions;

public enum MessageError {
    ACCOUNT_ID_NOT_FOUND("Account ID not found"),
    ACCOUNT_NOT_HAVE_FUNDS("Insufficient funds"),
    NEGATIVE_NUMBER_NOT_ALLOWED("Cannot enter a negative number"),
    MOVEMENT_ID_NOT_FOUND("Movement ID not found");


    String message;

    MessageError(String message) {
        this.message=message;
    }
}