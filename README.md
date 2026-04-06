# DNA Sequence Analyzer (Mini Project)


## Project Highlights

- Validates and analyzes DNA/RNA sequences.
- Uses OOP design with abstraction, inheritance, interfaces, and method overloading.
- Uses custom exception handling for invalid nucleotides.
- Provides a Swing GUI for input, file loading, analysis, and history viewing.
- Uses Java Collections (`ArrayList`) to store analysis history.

## Syllabus Coverage Mapping

- Unit 1 & 2 (Datatypes, Strings, Operators, Control Statements)
  - Sequence parsing and validation logic.
  - GC-content and nucleotide count calculations.
  - Input checks with `if-else`, loops, and string operations.

- Unit 3 (Classes, Objects, Constructors, Method Overloading)
  - Classes: `AnalysisResult`, `SequenceAnalyzerService`, etc.
  - Constructors in model and result classes.
  - Overloaded methods in service and validator.

- Unit 4 (Inheritance, Abstraction, Interfaces, Overriding)
  - Abstract class: `GeneticSequence`.
  - Subclasses: `DNASequence`, `RNASequence`.
  - Interface: `Analyzable`.
  - Overridden methods: `analyze()`, `validate()`, `transcribeOrTransform()`.

- Unit 5 (Exception Handling)
  - Custom exception: `InvalidNucleotideException`.
  - `try-catch-throw` flow in service and UI.

- Unit 6 (GUI)
  - Swing UI with `JFrame`, `JPanel`, input/output areas, and button event handling.

- Unit 7 (Collection Framework)
  - `ArrayList<AnalysisResult>` for history.
  - Iteration with enhanced `for` loop.

## Folder Structure

```
src/
  dnaanalyzer/
    Main.java
    exception/
      InvalidNucleotideException.java
    model/
      Analyzable.java
      GeneticSequence.java
      DNASequence.java
      RNASequence.java
      AnalysisResult.java
    service/
      SequenceValidator.java
      SequenceAnalyzerService.java
    repository/
      AnalysisHistory.java
    ui/
      MainFrame.java

## Compile and Run (Windows PowerShell)

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
java -cp out dnaanalyzer.Main
```

## Demo Checklist 

- Show invalid input handling (`InvalidNucleotideException`).
- Show DNA analysis (counts, GC%, complement, RNA transcription).
- Show RNA analysis (counts, GC%, DNA back-transform).
- Load sequence from file and analyze.
- Show history list populated using collections.
