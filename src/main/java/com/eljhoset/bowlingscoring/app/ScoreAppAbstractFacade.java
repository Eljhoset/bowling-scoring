package com.eljhoset.bowlingscoring.app;

import com.eljhoset.bowlingscoring.data.exception.EmptyFileException;
import com.eljhoset.bowlingscoring.data.repository.FileRollRepository;
import com.eljhoset.bowlingscoring.formatter.PlayerScoreFormatter;
import com.eljhoset.bowlingscoring.formatter.model.GameScore;
import com.eljhoset.bowlingscoring.parser.RollParser;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.processor.FrameScoreProcessor;
import com.eljhoset.bowlingscoring.processor.model.PlayerScore;
import java.io.IOException;
import java.util.List;

/**
 * Provides a abstraction of all the phases of the processing process
 *
 * @author jd-jd
 */
public abstract class ScoreAppAbstractFacade implements AppFacadeEvents {

    public void getScore(String filePath, ScoreAppConsumer consumer) throws IOException, EmptyFileException {

        FileRollRepository repository = getRepository();
        RollParser parser = getParser();
        FrameScoreProcessor processor = getProcessor();
        PlayerScoreFormatter formatter = getFormatter();

        List<String> data = loadData(filePath, repository);
        List<PlayerFrames> dataParsed = parceData(data, parser);
        List<PlayerScore> dataProcessed = process(dataParsed, processor);
        GameScore dataFormatted = format(dataProcessed, formatter);
        consumer.consume(dataFormatted);
    }

    private GameScore format(List<PlayerScore> dataProcessed, PlayerScoreFormatter formatter) {
        onBeforeFormat(dataProcessed);
        GameScore dataFormatted = formatter.format(dataProcessed);
        onAfterFormat(dataProcessed, dataFormatted);
        return dataFormatted;
    }

    private List<PlayerScore> process(List<PlayerFrames> dataParsed, FrameScoreProcessor processor) {
        onBeforeProcess(dataParsed);
        List<PlayerScore> dataProcessed = processor.processAll(dataParsed);
        onAfterProcess(dataParsed, dataProcessed);
        return dataProcessed;
    }

    private List<PlayerFrames> parceData(List<String> data, RollParser parser) {
        onBeforeParse(data);
        List<PlayerFrames> dataParsed = parser.parse(data);
        onAfterParse(data, dataParsed);
        return dataParsed;
    }

    private List<String> loadData(String filePath, FileRollRepository repository) throws IOException, EmptyFileException {
        onBeforeDataIsLoaded(filePath);
        List<String> data = repository.load(filePath);
        onAfterDataIsLoaded(filePath, data);
        return data;
    }

    protected abstract FileRollRepository getRepository();

    protected abstract RollParser getParser();

    protected abstract FrameScoreProcessor getProcessor();

    protected abstract PlayerScoreFormatter getFormatter();

}
