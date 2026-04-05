package dnaanalyzer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import dnaanalyzer.exception.InvalidNucleotideException;
import dnaanalyzer.model.AnalysisResult;
import dnaanalyzer.model.DNASequence;
import dnaanalyzer.model.GeneticSequence;
import dnaanalyzer.model.RNASequence;
import dnaanalyzer.repository.AnalysisHistory;

public class SequenceAnalyzerService {
    private final SequenceValidator validator;
    private final AnalysisHistory history;

    public SequenceAnalyzerService(SequenceValidator validator, AnalysisHistory history) {
        this.validator = validator;
        this.history = history;
    }

    public AnalysisResult analyzeSequence(String sequence) throws InvalidNucleotideException {
        return analyzeSequence(sequence, true);
    }

    public AnalysisResult analyzeSequence(String sequence, boolean removeInternalSpaces) throws InvalidNucleotideException {
        String clean = validator.sanitize(sequence, removeInternalSpaces);
        GeneticSequence geneticSequence = validator.looksLikeRNA(clean) ? new RNASequence(clean) : new DNASequence(clean);
        AnalysisResult result = geneticSequence.analyze();
        history.add(result);
        return result;
    }

    public AnalysisResult analyzeSequence(Path filePath) throws InvalidNucleotideException, IOException {
        String fileContent = Files.readString(filePath);
        return analyzeSequence(fileContent, true);
    }

    public AnalysisHistory getHistory() {
        return history;
    }
}
