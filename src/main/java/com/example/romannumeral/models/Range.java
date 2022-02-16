package com.example.romannumeral.models;

public class Range {

    private final RomanNumeral[] conversions;

    public Range(int size) {
        this.conversions = new RomanNumeral[size];
    }

    public RomanNumeral[] getConversions() {
        return conversions;
    }

}
