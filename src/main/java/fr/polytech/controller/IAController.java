package fr.polytech.controller;

import fr.polytech.model.DrawingSheet;
import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.agent.Action.Action;
import fr.polytech.model.agent.Agent;
import fr.polytech.model.agent.AgentRandom;
import fr.polytech.model.element.ToroidalTurtle;
import fr.polytech.model.element.Turtle;
import fr.polytech.view.LayoutIA;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.awt.event.ActionEvent;
import java.util.Observer;

/**
 * Created by gorya on 03/05/2017.
 */
public class IAController extends Controller implements Observer {
    @Override
    void init() {
        ToroidalDrawingSheet toroidalDrawingSheet= new ToroidalDrawingSheet();
        toroidalDrawingSheet.setHeight(400);
        toroidalDrawingSheet.setWidth(600);
        this.drawingSheet = toroidalDrawingSheet;
        this.layout = new LayoutIA(this, this.drawingSheet);
        this.startIA();
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

    private void startIA() {
        ((ToroidalDrawingSheet)this.drawingSheet).drawLimit();
        for (int i = 0; i < 1; i++){
            launchIa();
        }
    }

    private void launchIa() {
        Turtle turtle = new ToroidalTurtle((ToroidalDrawingSheet) this.drawingSheet);
        this.drawingSheet.addTortue(turtle);
        Agent agent = new AgentRandom(this.drawingSheet,turtle);
        agent.addObserver(this);
        Thread thread = new Thread(agent);
        thread.start();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        this.layout.repaint();
    }
}
