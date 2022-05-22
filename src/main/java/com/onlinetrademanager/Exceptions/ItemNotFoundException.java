package com.onlinetrademanager.Exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String msg) {
        super(msg);
    }
}
