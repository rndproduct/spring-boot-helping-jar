package com.brother.customresponse.enums;

public enum ActiveStatus {
    ACTIVE(1),
    ARCHIVE(2),
    DELETE(3),
    OPEN(4),
    CLOSED(5),
    PREVIOUS(6);

    private final int value;

    ActiveStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
