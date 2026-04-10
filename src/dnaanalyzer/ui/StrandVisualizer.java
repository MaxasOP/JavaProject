package dnaanalyzer.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class StrandVisualizer extends JPanel {
    private String sequence = "";
    private double gcPercentage = 0.0;
    private int offset = 0;
    private static final Color A_COLOR = new Color(211, 84, 0);
    private static final Color C_COLOR = new Color(0, 114, 178);
    private static final Color G_COLOR = new Color(34, 139, 34);
    private static final Color T_COLOR = new Color(113, 80, 150);
    private static final Color U_COLOR = new Color(214, 39, 40);
    private static final Color BG_COLOR = new Color(248, 250, 252);

    public StrandVisualizer() {
        Timer timer = new Timer(50, e -> {
            offset = (offset + 5) % 100;
            repaint();
        });
        timer.start();
        setPreferredSize(new Dimension(500, 300));
    }

    public void updateSequence(String seq, double gc) {
        this.sequence = seq.toUpperCase();
        this.gcPercentage = gc;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setBackground(BG_COLOR);
        g2d.clearRect(0, 0, getWidth(), getHeight());

        // Helix backbone
        g2d.setColor(Color.LIGHT_GRAY);
        int centerY = getHeight() / 2;
        int strandHeight = 40;
        for (int i = 0; i < getWidth(); i += 20) {
            g2d.drawLine(i, centerY - strandHeight / 2, i, centerY + strandHeight / 2);
            int helixX = (i + 10) % 40 < 20 ? centerY - strandHeight / 2 : centerY + strandHeight / 2;
            g2d.fillOval(i + 5, helixX - 5, 10, 10);
        }

        // Scrolling nucleotides
        g2d.setFont(new Font("Consolas", Font.BOLD, 24));
        FontMetrics fm = g2d.getFontMetrics();
        int charWidth = fm.charWidth('A');
        int startX = -offset;
        for (int i = 0; i < sequence.length() && startX < getWidth(); i++) {
            char nuc = sequence.charAt(i);
            Color col = getNucleotideColor(nuc);
            g2d.setColor(col);
            String label = String.valueOf(nuc);
            Rectangle2D bounds = fm.getStringBounds(label, g2d);
            g2d.fillRoundRect(startX, (int) (centerY - bounds.getHeight() / 2 - 10), (int) bounds.getWidth() + 8, (int) bounds.getHeight() + 20, 12, 12);
            g2d.setColor(Color.WHITE);
            g2d.drawString(label, startX + 4, centerY + 5);
            startX += charWidth + 15;
        }

        // GC% arc
        g2d.setColor(G_COLOR);
        Arc2D arc = new Arc2D.Double(getWidth() - 100, getHeight() - 80, 60, 60, 90, gcPercentage * 3.6, Arc2D.PIE);
        g2d.fill(arc);
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.format("%.1f%% GC", gcPercentage), getWidth() - 90, getHeight() - 45);

        g2d.dispose();
    }

    private Color getNucleotideColor(char nuc) {
        switch (nuc) {
            case 'A': return A_COLOR;
            case 'C': return C_COLOR;
            case 'G': return G_COLOR;
            case 'T': return T_COLOR;
            case 'U': return U_COLOR;
            default: return Color.GRAY;
        }
    }
}
