package fr.polytech;

import fr.polytech.controller.Controller;

import javax.swing.*;

/**
 * Created by gorya on 12/04/2017.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                Controller controller = new Controller();
            }
        });
    }
}
