package com.eljhoset.bowlingscoring.formatter.model;

import java.util.Collections;
import java.util.List;

public class GameScoreImpl implements GameScore {

    private final String header;
    private final List<String> players;

    public GameScoreImpl(String header, List<String> players) {
        this.header = header;
        this.players = players;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public List<String> getPlayers() {
        return Collections.unmodifiableList(players);
    }

}
