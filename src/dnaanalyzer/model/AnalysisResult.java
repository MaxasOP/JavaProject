package dnaanalyzer.model;

import java.time.LocalDateTime;
import java.util.Map;

public class AnalysisResult {
    private final String sequenceType;
    private final String inputSequence;
    private final Map<Character, Integer> nucleotideCount;
    private final double gcPercentage;
    private final String transformedSequence;
    private final LocalDateTime analyzedAt;

    public AnalysisResult(
            String sequenceType,
            String inputSequence,
            Map<Character, Integer> nucleotideCount,
            double gcPercentage,
            String transformedSequence) {
        this.sequenceType = sequenceType;
        this.inputSequence = inputSequence;
        this.nucleotideCount = nucleotideCount;
        this.gcPercentage = gcPercentage;
        this.transformedSequence = transformedSequence;
        this.analyzedAt = LocalDateTime.now();
    }

    public String getSequenceType() {
        return sequenceType;
    }

    public String getInputSequence() {
        return inputSequence;
    }

    public Map<Character, Integer> getNucleotideCount() {
        return nucleotideCount;
    }

    public double getGcPercentage() {
        return gcPercentage;
    }

    public String getTransformedSequence() {
        return transformedSequence;
    }

    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }

    public String toDisplayText() {
        return "Type: " + sequenceType + System.lineSeparator()
                + "Input: " + inputSequence + System.lineSeparator()
                + "Counts: " + nucleotideCount + System.lineSeparator()
                + "GC%: " + String.format("%.2f", gcPercentage) + System.lineSeparator()
                + "Transform: " + transformedSequence + System.lineSeparator()
                + "Timestamp: " + analyzedAt;
    }
}
