package com.example.dashboard.ui.model;

public class Item {
    private final String column1Data;
    private final int column2Data;

    public Item(String column1Data, int column2Data) {
        this.column1Data = column1Data;
        this.column2Data = column2Data;
    }

    public String getColumn1Data() {
        return column1Data;
    }

    public int getColumn2Data() {
        return column2Data;
    }
}
