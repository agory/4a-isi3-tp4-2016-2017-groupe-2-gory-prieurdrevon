package fr.polytech;

import fr.polytech.controller.Controller;
import fr.polytech.controller.FlockingController;
import fr.polytech.controller.IAController;
import fr.polytech.controller.UserController;
import fr.polytech.view.SelectionMode;

import javax.swing.*;

/**
 * Created by gorya on 12/04/2017.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                SelectionMode selectionMode = new SelectionMode();
                int mode = selectionMode.selection();
                Controller controller;
                switch (mode) {
                    case 0 :
                        controller = new UserController();
                        break;
                    case 1 :
                        controller = new IAController();
                        break;
                    case 2 :
                        controller = new FlockingController();
                        break;
                }
            }
        });
    }
}
