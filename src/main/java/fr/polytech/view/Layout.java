package fr.polytech.view;

import fr.polytech.controller.Controller;
import fr.polytech.controller.UserController;
import fr.polytech.model.DrawingSheet;
import fr.polytech.view.element.DrawingSheetView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by gorya on 12/04/2017.
 */
public abstract class Layout extends JFrame {
    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);



    protected DrawingSheetView feuille;
    protected Controller controller;

    public Layout(Controller controller, DrawingSheet drawingSheet) {
        super("un logo tout simple");
        this.controller = controller;
        this.feuille = new DrawingSheetView(drawingSheet);

        getContentPane().setLayout(new BorderLayout(10,10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        init();


        pack();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }

    public abstract void init();

    protected void createFeuille() {
        feuille.setBackground(Color.white);
        feuille.setSize(new Dimension(600,400));
        feuille.setPreferredSize(new Dimension(600,400));

        getContentPane().add(feuille,"Center");
    }




}
