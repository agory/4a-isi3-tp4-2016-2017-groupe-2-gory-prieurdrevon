package fr.polytech.view;

import fr.polytech.controller.IAController;
import fr.polytech.controller.UserController;
import fr.polytech.model.DrawingSheet;
import fr.polytech.view.element.TopMenuBarUser;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gorya on 03/05/2017.
 */
public class LayoutIA extends Layout {
    public LayoutIA(IAController iaController, DrawingSheet drawingSheet) {
        super(iaController, drawingSheet);
    }

    @Override
    public void init() {
        getContentPane().setLayout(new BorderLayout(10,10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.createMenu();
        this.createFeuille();


        pack();
        setVisible(true);
    }

    private void createMenu() {
        JMenuBar menubar=new TopMenuBarUser(controller);
        setJMenuBar(menubar);	// on installe le menu bar

    }


}
