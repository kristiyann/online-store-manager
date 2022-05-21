package com.onlinetrademanager.Exceptions;

public class SaleNotFoundException extends RuntimeException {
    public SaleNotFoundException(String msg) {
        super(msg);
    }
}
