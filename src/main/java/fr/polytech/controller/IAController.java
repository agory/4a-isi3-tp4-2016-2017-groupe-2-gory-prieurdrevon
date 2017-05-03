package fr.polytech.controller;

import fr.polytech.model.DrawingSheet;
import fr.polytech.view.LayoutIA;

import java.awt.event.ActionEvent;

/**
 * Created by gorya on 03/05/2017.
 */
public class IAController extends Controller {
    @Override
    void init() {
        this.drawingSheet = new DrawingSheet();
        this.layout = new LayoutIA(this, this.drawingSheet);
    }

    /**
     * la gestion des actions des boutons
     */
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        System.out.println(c);
        // actions des boutons du haut

        if (c.equals("Effacer")) {
            this.drawingSheet.reset();
        } else if (c.equals("Quitter"))
            System.exit(0);

        this.layout.repaint();
    }
}
