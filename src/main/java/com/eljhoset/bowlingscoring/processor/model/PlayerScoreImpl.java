package com.eljhoset.bowlingscoring.processor.model;

import com.eljhoset.bowlingscoring.parser.model.Player;
import java.util.Objects;

public class PlayerScoreImpl implements PlayerScore {

    private final Player player;
    private final FrameScoreList scores;

    public PlayerScoreImpl(Player player, FrameScoreList scores) {
        Objects.requireNonNull(player, "frames player not be null");
        Objects.requireNonNull(scores, "scores must not be null");
        this.player = player;
        this.scores = scores;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public FrameScoreList getFrames() {
        return scores;
    }

}
