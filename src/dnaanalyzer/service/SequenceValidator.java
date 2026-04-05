package dnaanalyzer.service;

public class SequenceValidator {
    public boolean looksLikeRNA(String sequence) {
        if (sequence == null) {
            return false;
        }
        return sequence.toUpperCase().contains("U");
    }

    public String sanitize(String rawSequence) {
        return rawSequence == null ? "" : rawSequence.trim().toUpperCase();
    }

    public String sanitize(String rawSequence, boolean removeInternalSpaces) {
        String cleaned = sanitize(rawSequence);
        if (removeInternalSpaces) {
            return cleaned.replaceAll("\\s+", "");
        }
        return cleaned;
    }
}
