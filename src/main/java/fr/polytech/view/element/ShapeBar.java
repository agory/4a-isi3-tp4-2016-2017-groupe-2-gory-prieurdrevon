package fr.polytech.view.element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by gorya on 03/05/2017.
 */
public class ShapeBar extends JPanel {

    private ActionListener listener;

    public ShapeBar(ActionListener listener) {
        super(new GridLayout());
        this.listener = listener;
        init();
    }

    private void init() {
        JButton b20 = new JButton("Proc1");
        this.add(b20);
        b20.addActionListener(listener);
        JButton b21 = new JButton("Proc2");
        this.add(b21);
        b21.addActionListener(listener);
        JButton b22 = new JButton("Proc3");
        this.add(b22);
        b22.addActionListener(listener);
    }
}
