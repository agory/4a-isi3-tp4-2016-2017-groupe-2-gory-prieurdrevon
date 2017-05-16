package fr.polytech.controller;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.agent.Agent;
import fr.polytech.model.agent.AgentFlocking;
import fr.polytech.model.agent.AgentRandom;
import fr.polytech.model.element.Obstacle;
import fr.polytech.model.element.ToroidalTurtle;
import fr.polytech.model.element.Turtle;

import java.awt.*;
import java.util.Observer;

/**
 * Created by gorya on 03/05/2017.
 */
public class FlockingController extends IAController implements Observer {

    private static int NBAGENT = 100;

    protected void startIA() {
        Obstacle obstacle = new Obstacle(new Point(300,200),30);
        obstacle.setColor(Color.RED);
        this.drawingSheet.addElement(obstacle);
        ((ToroidalDrawingSheet)this.drawingSheet).drawLimit();
        for (int i = 0; i < NBAGENT; i++){
            launchIa();
        }
    }

    @Override
    protected void init() {
        super.init();

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
