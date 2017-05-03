package fr.polytech.view;

import javax.swing.JOptionPane;

/**
 * Created by Silver on 03-May-17.
 */
public class SelectionMode {

    public static void main(String[] arg) {
        selection();
    }

    public static void selection() {
        Object[] choices = {"contrôlé", "aléatoire", "flocking"};
        int input = JOptionPane.showOptionDialog(null, "Mode d'exécution des tortues:",
                "Mode tortues", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,// Use
                choices, // Array of choices
                choices[0]); // Initial choice
        System.out.println(input);
    }

}
