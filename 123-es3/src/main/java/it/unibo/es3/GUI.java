package it.unibo.es3;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GUI extends JFrame {
    
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int width) {
        this.logic = new LogicImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*width, 70*width);
        JPanel panel = new JPanel(new BorderLayout());
        this.getContentPane().add(panel);
        JPanel buttons = new JPanel(new GridLayout(width, width));
        panel.add(buttons, BorderLayout.CENTER);

        JButton advance = new JButton(">");
        panel.add(advance, BorderLayout.SOUTH);
        advance.addActionListener(e -> {
            final List<Pair<Integer, Integer>> newPos = this.logic.advance();
            this.applyLabel(newPos, ".");
            if (this.logic.toQuit()) {
                System.exit(0);
            }
        });
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton();
                this.cells.put(jb, pos);
                buttons.add(jb);
            }
        }
        this.applyLabel(this.logic.getInitialPositions(), "*");
        this.setVisible(true);
    }

    private void applyLabel(final List<Pair<Integer, Integer>> positions, final String label) {
        this.cells.forEach((jb, pos) -> {
            if (positions.contains(pos)) {
                jb.setText(label);
            }
        });
    }
    
}