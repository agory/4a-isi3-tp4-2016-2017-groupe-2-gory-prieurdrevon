package fr.polytech.view.element;

import fr.polytech.view.ComponentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by gorya on 03/05/2017.
 */
public class ToolBar extends JToolBar {
    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);

    private ActionListener listener;
    private JTextField inputValue;
    private JComboBox inputColor;

    public ToolBar(ActionListener listener) {
        this.listener = listener;
        init();
    }

    private void init() {
        ComponentService componentService = ComponentService.getInstance();

        componentService.addButton(this,"Effacer","Nouveau dessin","/icons/index.png",listener);

        this.add(Box.createRigidArea(HGAP));
        inputValue = new JTextField("45",5);
        this.add(inputValue);
        componentService.addButton(this, "Avancer", "Avancer 50", null,listener);
        componentService.addButton(this, "Droite", "Droite 45", null,listener);
        componentService.addButton(this, "Gauche", "Gauche 45", null,listener);
        componentService.addButton(this, "Lever", "Lever Crayon", null,listener);
        componentService.addButton(this, "Baisser", "Baisser Crayon", null,listener);
        componentService.addButton(this, "Add", "Ajouter Turtle", null,listener);

        String[] colorStrings = {"noir", "bleu", "cyan","gris fonce","rouge",
                "vert", "gris clair", "magenta", "orange",
                "gris", "rose", "jaune"};

        // Create the combo box
        this.add(Box.createRigidArea(HGAP));
        JLabel colorLabel = new JLabel("   Couleur: ");
        this.add(colorLabel);
        this.inputColor = new JComboBox(colorStrings);
        this.add(inputColor);

        this.inputColor.addActionListener(this.listener);
    }

    public String  getInputValue() {
        return inputValue.getText();
    }

    public int getInputColor() {
        return inputColor.getSelectedIndex();
    }
}
