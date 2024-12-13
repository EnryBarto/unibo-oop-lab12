package it.unibo.es2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private final Logics logics;
    private final Map<JButton, Pair<Integer, Integer>> buttons;
    
    public GUI(int size) {
        this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);

        this.buttons = new HashMap<>(size * size);

        ActionListener al = (e)->{
            final JButton buttonClicked = (JButton)e.getSource();
            this.buttons.forEach((b, pos) -> {
                if (b.equals(buttonClicked)) {
                    buttonClicked.setText(this.logics.click(pos));
                }
            });
            if (this.logics.toQuit()) {
                System.exit(0);
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton button = new JButton();
                this.buttons.put(button, new Pair<>(j, i));
                panel.add(button);
                button.setVisible(true);
                button.addActionListener(al);
            }
        }
        this.setVisible(true);
    }   
}
