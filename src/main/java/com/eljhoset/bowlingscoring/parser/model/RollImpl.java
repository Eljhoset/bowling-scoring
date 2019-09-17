package com.eljhoset.bowlingscoring.parser.model;

public class RollImpl implements Roll {

    private final String pins;

    public RollImpl(String pins) {
        this.pins = pins;
    }

    @Override
    public String getPins() {
        return pins;
    }

    @Override
    public Integer getValue() {
        if (pins.equals("F")) {
            return 0;
        }
        return Integer.valueOf(pins);
    }

}
