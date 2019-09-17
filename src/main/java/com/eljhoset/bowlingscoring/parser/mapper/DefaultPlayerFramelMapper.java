package com.eljhoset.bowlingscoring.parser.mapper;

import com.eljhoset.bowlingscoring.parser.model.Frame;
import com.eljhoset.bowlingscoring.parser.model.FrameImpl;
import com.eljhoset.bowlingscoring.parser.model.FrameRolls;
import com.eljhoset.bowlingscoring.parser.model.FrameRollsImpl;
import com.eljhoset.bowlingscoring.parser.model.FramesImpl;
import com.eljhoset.bowlingscoring.parser.model.Player;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.model.PlayerFramesImpl;
import com.eljhoset.bowlingscoring.parser.model.PlayerImpl;
import static com.eljhoset.bowlingscoring.parser.model.REGEXS.VALUE_SEPARATOR;
import com.eljhoset.bowlingscoring.parser.model.Roll;
import com.eljhoset.bowlingscoring.parser.model.RollImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DefaultPlayerFramelMapper implements PlayerFramelMapper {

    @Override
    public List<PlayerFrames> map(List<String> line) {
        Objects.requireNonNull(line, "line cannot be null.");
        return line.stream()
                .map(String::trim)
                .filter(f -> f != null && !f.isEmpty())
                .map(e -> e.split(VALUE_SEPARATOR.get()))
                .collect(Collectors.groupingBy(e -> e[0], Collectors.mapping(e -> e[1], Collectors.toList())))
                .entrySet().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private PlayerFrames map(Map.Entry<String, List<String>> entry) {
        List<Roll> allRolls = entry.getValue()
                .stream()
                .map(RollImpl::new)
                .collect(Collectors.toList());

        Player player = new PlayerImpl(entry.getKey());
        List<Frame> frameList = new ArrayList<>(10);
        int index = 0;
        while (index < allRolls.size()) {
            FrameRolls fr = getFrameRoll(index, frameList.size(), allRolls);

            final Frame frame = new FrameImpl(fr);
            frameList.add(frame);
            index += fr.getRollsNumber();
        }
        FramesImpl frames = new FramesImpl(frameList);

        return new PlayerFramesImpl(player, frames);
    }

    private FrameRolls getFrameRoll(int index, int frameNumber, List<Roll> allRolls) {
        final List<Roll> rolls = new ArrayList<>(1);
        final Roll firstRoll = allRolls.get(index);
        rolls.add(firstRoll);
        int indexToFindSecondRoll = index + 1;
        if (10 - firstRoll.getValue() > 0 && indexToFindSecondRoll < allRolls.size()) {
            final Roll scondRoll = allRolls.get(indexToFindSecondRoll);
            rolls.add(scondRoll);
        }
        //if is the last frame add the rest of the rolls
        if (frameNumber == 9) {
            int startIndex = index + (rolls.size() - 1);
            int end = allRolls.size() - 1;
            IntStream.range(startIndex, end)
                    .forEach(i -> rolls.add(allRolls.get(i)));
        }
        return  new FrameRollsImpl(rolls);
    }

}
