package com.eljhoset.bowlingscoring.parser.model;

public class PlayerFramesImpl implements PlayerFrames {

    private final Player player;
    private final FrameList frames;

    public PlayerFramesImpl(Player player, FrameList frames) {
        this.player = player;
        this.frames = frames;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public FrameList getFrames() {
        return frames;
    }

}
