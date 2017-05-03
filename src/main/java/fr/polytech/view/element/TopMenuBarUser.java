package fr.polytech.view.element;

import fr.polytech.view.ComponentService;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by gorya on 03/05/2017.
 */
public class TopMenuBarUser extends TopMenuBar {

    public TopMenuBarUser(ActionListener listener) {
        super(listener);
    }

    protected void init(){
        super.init();
        ComponentService componentService = ComponentService.getInstance();

        JMenu menuCommandes=new JMenu("Commandes"); // on installe le premier menu
        this.add(menuCommandes);

        componentService.addMenuItem(menuCommandes, "Avancer", "Avancer", -1,listener);
        componentService.addMenuItem(menuCommandes, "Droite", "Droite", -1,listener);
        componentService.addMenuItem(menuCommandes, "Gauche", "Gauche", -1,listener);
        componentService.addMenuItem(menuCommandes, "Lever Crayon", "Lever", -1,listener);
        componentService.addMenuItem(menuCommandes, "Baisser Crayon", "Baisser", -1,listener);



    }
}
