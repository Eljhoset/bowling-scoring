package com.eljhoset.bowlingscoring.parser.model;

public class PlayerImpl implements Player {

    private final String name;

    public PlayerImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
