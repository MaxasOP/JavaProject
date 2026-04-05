package dnaanalyzer.model;

import dnaanalyzer.exception.InvalidNucleotideException;

public interface Analyzable {
    AnalysisResult analyze() throws InvalidNucleotideException;
}
