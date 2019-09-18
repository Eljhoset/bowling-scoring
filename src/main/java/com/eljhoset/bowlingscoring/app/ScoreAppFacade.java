package com.eljhoset.bowlingscoring.app;

import com.eljhoset.bowlingscoring.data.repository.DefaultFileRollRepository;
import com.eljhoset.bowlingscoring.data.repository.FileRollRepository;
import com.eljhoset.bowlingscoring.formatter.DefaultPlayerScoreFormatter;
import com.eljhoset.bowlingscoring.formatter.PlayerScoreFormatter;
import com.eljhoset.bowlingscoring.formatter.model.GameScore;
import com.eljhoset.bowlingscoring.parser.RollParser;
import com.eljhoset.bowlingscoring.parser.factory.DefaultRollParserFactory;
import com.eljhoset.bowlingscoring.parser.factory.RollParserType;
import com.eljhoset.bowlingscoring.parser.model.Player;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.processor.DefaultFrameScoreProcessor;
import com.eljhoset.bowlingscoring.processor.FrameScoreProcessor;
import com.eljhoset.bowlingscoring.processor.model.PlayerScore;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ScoreAppFacade extends ScoreAppAbstractFacade {

    private static final Logger LOG = Logger.getLogger(ScoreAppFacade.class.getName());

    @Override
    protected FileRollRepository getRepository() {
        return new DefaultFileRollRepository();
    }

    @Override
    protected RollParser getParser() {
        return DefaultRollParserFactory.instance().get(RollParserType.DEFAULT);
    }

    @Override
    protected FrameScoreProcessor getProcessor() {
        return new DefaultFrameScoreProcessor();
    }

    @Override
    protected PlayerScoreFormatter getFormatter() {
        return new DefaultPlayerScoreFormatter();
    }

    @Override
    public void onAfterFormat(List<PlayerScore> dataProcessed, GameScore dataFormatted) {
        LOG.finer("scores formated");
    }

    @Override
    public void onBeforeFormat(List<PlayerScore> dataProcessed) {
        LOG.finer("formating scores");
    }

    @Override
    public void onAfterProcess(List<PlayerFrames> dataParsed, List<PlayerScore> dataProcessed) {
        LOG.finer("playes scores processed");
    }

    @Override
    public void onBeforeProcess(List<PlayerFrames> dataParsed) {
        LOG.finer("processing playes scores");
    }

    @Override
    public void onAfterParse(List<String> data, List<PlayerFrames> dataParsed) {
        String players = dataParsed.stream().map(PlayerFrames::getPlayer).map(Player::getName)
                .collect(Collectors.joining(", "));
        LOG.log(Level.FINER, () -> String.format("%d lines parsed into [%s] scores", data.size(), players));
    }

    @Override
    public void onBeforeParse(List<String> data) {
        LOG.log(Level.FINER, () -> String.format("parsing %d lines", data.size()));
    }

    @Override
    public void onAfterDataIsLoaded(String filePath, List<String> data) {
        LOG.log(Level.FINER, () -> String.format("%d lines loaded from file[%s]", data.size(), filePath));
    }

    @Override
    public void onBeforeDataIsLoaded(String filePath) {
        LOG.log(Level.FINER, () -> String.format("loading file[%s]", filePath));
    }

}
