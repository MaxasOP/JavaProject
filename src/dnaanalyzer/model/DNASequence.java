package dnaanalyzer.model;

import java.util.Map;

import dnaanalyzer.exception.InvalidNucleotideException;

public class DNASequence extends GeneticSequence {
    public DNASequence(String sequence) {
        super(sequence);
    }

    @Override
    protected boolean isValidNucleotide(char ch) {
        return ch == 'A' || ch == 'C' || ch == 'G' || ch == 'T';
    }

    @Override
    public void validate() throws InvalidNucleotideException {
        if (sequence.isBlank()) {
            throw new InvalidNucleotideException("DNA sequence cannot be empty.");
        }
        for (int i = 0; i < sequence.length(); i++) {
            char current = sequence.charAt(i);
            if (!isValidNucleotide(current)) {
                throw new InvalidNucleotideException("Invalid DNA nucleotide '" + current + "' at position " + (i + 1));
            }
        }
    }

    @Override
    protected String transcribeOrTransform() {
        StringBuilder rna = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (c == 'T') {
                rna.append('U');
            } else {
                rna.append(c);
            }
        }
        return "RNA Transcript: " + rna + " | Complement: " + complement();
    }

    private String complement() {
        StringBuilder comp = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            switch (c) {
                case 'A':
                    comp.append('T');
                    break;
                case 'T':
                    comp.append('A');
                    break;
                case 'C':
                    comp.append('G');
                    break;
                case 'G':
                    comp.append('C');
                    break;
                default:
                    comp.append('?');
            }
        }
        return comp.toString();
    }

    @Override
    protected String getType() {
        return "DNA";
    }

    @Override
    protected Map<Character, Integer> countNucleotideMap() {
        return countNucleotides(new char[] {'A', 'C', 'G', 'T'});
    }
}
