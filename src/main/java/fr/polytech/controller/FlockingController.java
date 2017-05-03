package fr.polytech.controller;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.agent.Agent;
import fr.polytech.model.agent.AgentFlocking;
import fr.polytech.model.agent.AgentRandom;
import fr.polytech.model.element.ToroidalTurtle;
import fr.polytech.model.element.Turtle;

import java.util.Observer;

/**
 * Created by gorya on 03/05/2017.
 */
public class FlockingController extends IAController implements Observer {

    private static int NBAGENT = 200;

    protected void startIA() {
        ((ToroidalDrawingSheet)this.drawingSheet).drawLimit();
        for (int i = 0; i < NBAGENT; i++){
            launchIa();
        }
    }

    protected void launchIa() {
        Turtle turtle = new ToroidalTurtle((ToroidalDrawingSheet) this.drawingSheet);
        turtle.leverCrayon();
        this.drawingSheet.addTortue(turtle);
        Agent agent = new AgentFlocking(this.drawingSheet,turtle);
        agent.addObserver(this);
        Thread thread = new Thread(agent);
        thread.start();
    }


}
