package com.eljhoset.bowlingscoring.processor.model;

import com.eljhoset.bowlingscoring.parser.model.Player;
import java.util.Objects;

public class PlayerScoreImpl implements PlayerScore {

    private final Player player;
    private final FrameScoreList scores;
    private final Integer score;

    public PlayerScoreImpl(Player player, FrameScoreList scores, Integer score) {
        Objects.requireNonNull(player, "frames player not be null");
        Objects.requireNonNull(scores, "scores must not be null");
        this.player = player;
        this.scores = scores;
        this.score = score;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public FrameScoreList getFrames() {
        return scores;
    }

    @Override
    public Integer getScore() {
        return score;
    }

}
