package com.leavehub.model;

public enum LeaveType {
    ANNUAL("Concediu de Odihna"),
    SICK("Concediu Medical"),
    UNPAID("Concediu Fara Plata"),
    SPECIAL("Eveniment Deosebit");

    private final String label;

    LeaveType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}