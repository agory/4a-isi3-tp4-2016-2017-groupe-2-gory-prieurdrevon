package fr.polytech.controller;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.agent.Agent;
import fr.polytech.model.agent.AgentRandom;
import fr.polytech.model.element.ToroidalTurtle;
import fr.polytech.model.element.Turtle;
import fr.polytech.model.utils.FPSManager;
import fr.polytech.view.IALayout;

import java.awt.event.ActionEvent;
import java.util.Observer;

/**
 * Created by gorya on 03/05/2017.
 */
public class IAController extends Controller implements Observer {

    private static int NBAGENT = 30;

    @Override
    protected void init() {
        ToroidalDrawingSheet toroidalDrawingSheet= new ToroidalDrawingSheet();
        toroidalDrawingSheet.setHeight(400);
        toroidalDrawingSheet.setWidth(600);
        this.drawingSheet = toroidalDrawingSheet;
        this.layout = new IALayout(this, this.drawingSheet);
        this.startIA();
        this.refreshActive();
    }

    private void refreshActive(){
        FPSManager fpsManager = new FPSManager(30);
        fpsManager.addObserver(this);
        Thread thread = new Thread(fpsManager);
        thread.start();
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

    protected void startIA() {
        ((ToroidalDrawingSheet)this.drawingSheet).drawLimit();
        for (int i = 0; i < NBAGENT; i++){
            launchIa();
        }
    }

    protected void launchIa() {
        Turtle turtle = new ToroidalTurtle((ToroidalDrawingSheet) this.drawingSheet);
        turtle.leverCrayon();
        turtle.droite(90);
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
