package fr.polytech.controller;

import fr.polytech.model.DrawingSheet;
import fr.polytech.view.Layout;
import fr.polytech.view.LayoutUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by gorya on 03/05/2017.
 */
public abstract class Controller implements ActionListener {
    protected Layout layout;
    protected DrawingSheet drawingSheet;

    public Controller() {
        init();
        this.layout.setVisible(true);
    }

    abstract void init();

}
