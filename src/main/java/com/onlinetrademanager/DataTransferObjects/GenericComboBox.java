package com.onlinetrademanager.DataTransferObjects;

import java.util.UUID;

public class GenericComboBox {
    private UUID value;
    private String text;

    public GenericComboBox() {}

    public String getText() {
        return text;
    }

    public UUID getValue() {
        return value;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setValue(UUID value) {
        this.value = value;
    }
}
