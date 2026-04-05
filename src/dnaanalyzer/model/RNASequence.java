package dnaanalyzer.model;

import java.util.Map;

import dnaanalyzer.exception.InvalidNucleotideException;

public class RNASequence extends GeneticSequence {
    public RNASequence(String sequence) {
        super(sequence);
    }

    @Override
    protected boolean isValidNucleotide(char ch) {
        return ch == 'A' || ch == 'C' || ch == 'G' || ch == 'U';
    }

    @Override
    public void validate() throws InvalidNucleotideException {
        if (sequence.isBlank()) {
            throw new InvalidNucleotideException("RNA sequence cannot be empty.");
        }
        for (int i = 0; i < sequence.length(); i++) {
            char current = sequence.charAt(i);
            if (!isValidNucleotide(current)) {
                throw new InvalidNucleotideException("Invalid RNA nucleotide '" + current + "' at position " + (i + 1));
            }
        }
    }

    @Override
    protected String transcribeOrTransform() {
        StringBuilder dna = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (c == 'U') {
                dna.append('T');
            } else {
                dna.append(c);
            }
        }
        return "DNA Back-Transform: " + dna;
    }

    @Override
    protected String getType() {
        return "RNA";
    }

    @Override
    protected Map<Character, Integer> countNucleotideMap() {
        return countNucleotides(new char[] {'A', 'C', 'G', 'U'});
    }
}
