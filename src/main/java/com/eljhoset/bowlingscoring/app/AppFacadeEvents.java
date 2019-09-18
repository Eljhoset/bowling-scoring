package com.eljhoset.bowlingscoring.app;

import com.eljhoset.bowlingscoring.formatter.model.GameScore;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.processor.model.PlayerScore;
import java.util.List;

/**
 *
 * @author jd-jd
 */
public interface AppFacadeEvents {

    default void onBeforeDataIsLoaded(String filePath) {

    }

    default void onAfterDataIsLoaded(String filePath, List<String> data) {

    }

    default void onBeforeParse(List<String> data) {

    }

    default void onAfterParse(List<String> data, List<PlayerFrames> dataParsed) {

    }

    default void onBeforeProcess(List<PlayerFrames> dataParsed) {

    }

    default void onAfterProcess(List<PlayerFrames> dataParsed, List<PlayerScore> dataProcessed) {

    }

    default void onBeforeFormat(List<PlayerScore> dataProcessed) {

    }

    default void onAfterFormat(List<PlayerScore> dataProcessed, GameScore dataFormatted) {

    }
}
