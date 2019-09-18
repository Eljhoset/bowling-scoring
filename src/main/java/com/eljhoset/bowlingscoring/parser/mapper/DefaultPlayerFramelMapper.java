package com.eljhoset.bowlingscoring.parser.mapper;

import com.eljhoset.bowlingscoring.parser.model.Frame;
import com.eljhoset.bowlingscoring.parser.model.FrameImpl;
import com.eljhoset.bowlingscoring.parser.model.FrameListImpl;
import com.eljhoset.bowlingscoring.parser.model.FrameRolls;
import com.eljhoset.bowlingscoring.parser.model.FrameRollsImpl;
import com.eljhoset.bowlingscoring.parser.model.Player;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.model.PlayerFramesImpl;
import com.eljhoset.bowlingscoring.parser.model.PlayerImpl;
import static com.eljhoset.bowlingscoring.parser.model.REGEXS.VALUE_SEPARATOR;
import com.eljhoset.bowlingscoring.parser.model.Roll;
import com.eljhoset.bowlingscoring.parser.model.RollImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultPlayerFramelMapper implements PlayerFramelMapper {

    @Override
    public List<PlayerFrames> map(List<String> line) {
        Objects.requireNonNull(line, "line cannot be null.");
        return line.stream()
                .filter(f -> f != null && !f.trim().isEmpty())
                .map(e -> e.split(VALUE_SEPARATOR.get()))
                .collect(Collectors.groupingBy(e -> e[0], Collectors.mapping(e -> e[1], Collectors.toList())))
                .entrySet().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private PlayerFrames map(Map.Entry<String, List<String>> entry) {

        Player player = new PlayerImpl(entry.getKey());
        List<Roll> allRolls = entry.getValue()
                .stream()
                .map(RollImpl::new)
                .collect(Collectors.toList());
        Iterator<Roll> rolls = allRolls.iterator();

        List<Frame> frameList = new ArrayList<>(10);
        int frameNumber = 0;

        while (rolls.hasNext()) {
            final List<Roll> rollsOfFrame = new ArrayList<>(1);
            Roll firstRoll = rolls.next();
            rollsOfFrame.add(firstRoll);

            //if roll is not a strike get the second one
            if (10 - firstRoll.getValue() > 0 && rolls.hasNext()) {
                rollsOfFrame.add(rolls.next());
            }
            frameNumber++;

            //if is the las frame get the remaining rolls
            boolean lastFrame = frameNumber == 10;
            if (frameNumber == 10) {
                while (rolls.hasNext()) {
                    rollsOfFrame.add(rolls.next());
                }
            }
            FrameRolls fr = new FrameRollsImpl(rollsOfFrame);
            final Frame frame = new FrameImpl(fr, frameNumber, lastFrame);
            frameList.add(frame);
        }
        FrameListImpl frames = new FrameListImpl(frameList);
        return new PlayerFramesImpl(player, frames);
    }
}
