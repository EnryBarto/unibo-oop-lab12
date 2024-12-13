package it.unibo.es2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private final Logics logics;
    private final List<JButton> buttons;
    
    public GUI(int size) {
        this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);

        this.buttons = new ArrayList<>(size * size);

        ActionListener al = (e)->{
            final JButton buttonClicked = (JButton)e.getSource();
            buttonClicked.setText(this.logics.click(this.buttons.indexOf(buttonClicked)) ? "*" : " ");
            if (this.logics.toQuit()) {
                System.exit(0);
            }
        };
        var enablings = this.logics.enablings();
        for (int i = 0; i < size * size; i++) {
            final JButton button = new JButton(enablings.get(i) ? "*" : " ");
            this.buttons.add(button);
            panel.add(button);
            button.setVisible(true);
            button.addActionListener(al);
        }
        this.setVisible(true);
    }   
}
