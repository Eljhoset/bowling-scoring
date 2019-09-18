package com.eljhoset.bowlingscoring.app;

import com.eljhoset.bowlingscoring.formatter.model.GameScore;

public class DefaultScoreAppConsumer implements ScoreAppConsumer {

    @Override
    public void consume(GameScore gameScore) {
        StringBuilder sb = new StringBuilder(gameScore.getHeader());
        for (String player : gameScore.getPlayers()) {
            sb.append(player);
        }
        System.out.println(sb.toString());
    }

}
