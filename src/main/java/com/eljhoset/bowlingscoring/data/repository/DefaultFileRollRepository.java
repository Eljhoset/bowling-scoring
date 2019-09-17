package com.eljhoset.bowlingscoring.data.repository;

import com.eljhoset.bowlingscoring.data.exception.EmptyFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class DefaultFileRollRepository implements FileRollRepository {

    @Override
    public List<String> load(String path) throws IOException, EmptyFileException {
        Objects.requireNonNull(path, "file path must not be null");
        List<String> lines = Files.readAllLines(Paths.get(path));
        if (lines.isEmpty()) {
            throw new EmptyFileException(String.format("file in path[%s] is empty", path));
        }
        return lines;
    }

}
