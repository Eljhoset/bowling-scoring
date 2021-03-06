package com.eljhoset.bowlingscoring.parser.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FrameListImpl implements FrameList {

    private final List<Frame> frames;

    public FrameListImpl(List<Frame> frames) {
        Objects.requireNonNull(frames, "frames must not be null");
        if (frames.size() != 10) {
            throw new IllegalArgumentException("There number or frames must be 10");
        }
        this.frames = frames;
    }

    @Override
    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

}
