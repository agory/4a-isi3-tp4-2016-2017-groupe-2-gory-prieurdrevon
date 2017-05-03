package fr.polytech.view;

import javax.swing.JOptionPane;

/**
 * Created by Silver on 03-May-17.
 */
public class SelectionMode {

    public int selection() {
        Object[] choices = {"contrôlé", "aléatoire"};
        int input = JOptionPane.showOptionDialog(null, "Mode d'exécution des tortues:",
                "Mode tortues", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,// Use
                choices, // Array of choices
                choices[0]); // Initial choice
        return  input;
    }

}
