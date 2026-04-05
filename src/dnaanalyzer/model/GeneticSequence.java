package dnaanalyzer.model;

import java.util.HashMap;
import java.util.Map;

import dnaanalyzer.exception.InvalidNucleotideException;

public abstract class GeneticSequence implements Analyzable {
    protected final String sequence;

    public GeneticSequence(String sequence) {
        this.sequence = normalize(sequence);
    }

    protected String normalize(String raw) {
        return raw == null ? "" : raw.trim().toUpperCase();
    }

    public String getSequence() {
        return sequence;
    }

    protected abstract boolean isValidNucleotide(char ch);

    public abstract void validate() throws InvalidNucleotideException;

    protected abstract String transcribeOrTransform();

    protected abstract String getType();

    protected Map<Character, Integer> countNucleotides(char[] validLetters) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : validLetters) {
            counts.put(c, 0);
        }
        for (int i = 0; i < sequence.length(); i++) {
            char current = sequence.charAt(i);
            counts.put(current, counts.get(current) + 1);
        }
        return counts;
    }

    protected double gcPercentage() {
        if (sequence.isEmpty()) {
            return 0.0;
        }
        int gcCount = 0;
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (c == 'G' || c == 'C') {
                gcCount++;
            }
        }
        return (gcCount * 100.0) / sequence.length();
    }

    @Override
    public AnalysisResult analyze() throws InvalidNucleotideException {
        validate();
        return new AnalysisResult(getType(), sequence, countNucleotideMap(), gcPercentage(), transcribeOrTransform());
    }

    protected abstract Map<Character, Integer> countNucleotideMap();
}
