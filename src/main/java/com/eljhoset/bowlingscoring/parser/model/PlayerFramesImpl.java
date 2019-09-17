package com.eljhoset.bowlingscoring.parser.model;

public class PlayerFramesImpl implements PlayerFrames {

    private final Player player;
    private final Frames frames;

    public PlayerFramesImpl(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Frames getFrames() {
        return frames;
    }

}
