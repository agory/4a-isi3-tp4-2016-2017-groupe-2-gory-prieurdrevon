package fr.polytech.view.element;

import fr.polytech.view.ComponentService;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by gorya on 03/05/2017.
 */
public class TopMenuBar extends JMenuBar {
    protected ActionListener listener;

    public TopMenuBar(ActionListener listener) {
        this.listener = listener;
        init();
    }

    protected void init(){

        ComponentService componentService = ComponentService.getInstance();

        JMenu menuFile=new JMenu("File"); // on installe le premier menu
        this.add(menuFile);

        componentService.addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N,listener);
        componentService.addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q,listener);

        JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
        this.add(menuHelp);
        componentService.addMenuItem(menuHelp, "Aide", "Help", -1,listener);
        componentService.addMenuItem(menuHelp, "A propos", "About", -1,listener);
    }
}
