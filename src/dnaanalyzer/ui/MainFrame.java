package dnaanalyzer.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dnaanalyzer.exception.InvalidNucleotideException;
import dnaanalyzer.model.AnalysisResult;
import dnaanalyzer.repository.AnalysisHistory;
import dnaanalyzer.service.SequenceAnalyzerService;
import dnaanalyzer.service.SequenceValidator;

public class MainFrame extends JFrame {
    private final JTextArea inputArea;
    private final JTextArea outputArea;
    private final SequenceAnalyzerService analyzerService;

    public MainFrame() {
        super("DNA Sequence Analyzer");

        this.analyzerService = new SequenceAnalyzerService(new SequenceValidator(), new AnalysisHistory());
        this.inputArea = new JTextArea(6, 40);
        this.outputArea = new JTextArea(15, 40);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(760, 560));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildCenterPanel(), BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel buildTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Enter DNA/RNA Sequence:"), BorderLayout.NORTH);
        topPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        return topPanel;
    }

    private JScrollPane buildCenterPanel() {
        outputArea.setEditable(false);
        return new JScrollPane(outputArea);
    }

    private JPanel buildButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));

        JButton analyzeBtn = new JButton("Analyze Sequence");
        JButton loadBtn = new JButton("Load From File");
        JButton historyBtn = new JButton("Show History");
        JButton clearBtn = new JButton("Clear");

        analyzeBtn.addActionListener(e -> handleAnalyze());
        loadBtn.addActionListener(e -> handleLoadAndAnalyze());
        historyBtn.addActionListener(e -> outputArea.setText(analyzerService.getHistory().toMultilineText()));
        clearBtn.addActionListener(e -> {
            inputArea.setText("");
            outputArea.setText("");
            analyzerService.getHistory().clear();
        });

        buttonPanel.add(analyzeBtn);
        buttonPanel.add(loadBtn);
        buttonPanel.add(historyBtn);
        buttonPanel.add(clearBtn);
        return buttonPanel;
    }

    private void handleAnalyze() {
        try {
            AnalysisResult result = analyzerService.analyzeSequence(inputArea.getText());
            outputArea.setText(result.toDisplayText());
        } catch (InvalidNucleotideException ex) {
            showError(ex.getMessage());
        } catch (Exception ex) {
            showError("Unexpected error: " + ex.getMessage());
        }
    }

    private void handleLoadAndAnalyze() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File selected = chooser.getSelectedFile();
        try {
            AnalysisResult analysis = analyzerService.analyzeSequence(selected.toPath());
            inputArea.setText(analysis.getInputSequence());
            outputArea.setText(analysis.toDisplayText());
        } catch (InvalidNucleotideException | IOException ex) {
            showError(ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
}
