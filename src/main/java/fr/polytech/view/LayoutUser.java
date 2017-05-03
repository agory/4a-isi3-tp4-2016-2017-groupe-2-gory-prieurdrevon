package fr.polytech.view;

import fr.polytech.controller.UserController;
import fr.polytech.model.DrawingSheet;
import fr.polytech.view.element.ShapeBar;
import fr.polytech.view.element.ToolBar;
import fr.polytech.view.element.TopMenuBarUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by gorya on 12/04/2017.
 */
public class LayoutUser extends Layout {
    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);

    private ToolBar toolBar;

    public LayoutUser(UserController userController, DrawingSheet drawingSheet) {
        super(userController,drawingSheet);
    }

    public void init() {
        getContentPane().setLayout(new BorderLayout(10,10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.feuille.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ((UserController)controller).mouseClickPerformed(e);
            }
        });


        this.createToolBar();
        this.createMenu();
        this.createButton();
        this.createFeuille();

        pack();
        setVisible(true);
    }

    private void createMenu() {
        JMenuBar menubar=new TopMenuBarUser(controller);
        setJMenuBar(menubar);	// on installe le menu bar

    }

    private void createButton() {
        // les boutons du bas
        JPanel p2 = new ShapeBar(controller);
        getContentPane().add(p2,"South");
    }

    private void createToolBar(){
        // Boutons
        JPanel buttonPanel = new JPanel();
        toolBar = new ToolBar(this.controller);
        buttonPanel.add(toolBar);
        getContentPane().add(buttonPanel,"North");
    }


    public String getInputValue(){
        return this.toolBar.getInputValue();
    }

    public Integer getInputColor(){
        return this.toolBar.getInputColor();
    }



}
