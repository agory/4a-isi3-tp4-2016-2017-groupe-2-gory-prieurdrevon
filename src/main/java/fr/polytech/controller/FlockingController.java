package fr.polytech.controller;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.agent.Agent;
import fr.polytech.model.agent.AvoidingAgent;
import fr.polytech.model.agent.FlockingAgent;
import fr.polytech.model.element.Obstacle;
import fr.polytech.model.element.ToroidalTurtle;
import fr.polytech.model.element.Turtle;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by gorya on 03/05/2017.
 */
public class FlockingController extends IAController implements Observer {

    private static int NBAGENT = 50;
    private List<Agent> agents;


    protected void startIA() {
        Obstacle obstacle = null;
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            obstacle = new Obstacle(
                    new Point(rand.nextInt(((ToroidalDrawingSheet) this.drawingSheet).getWidth()),
                            rand.nextInt(((ToroidalDrawingSheet) this.drawingSheet).getHeight())),
                    40);
            obstacle.setColor(Color.RED);
            this.drawingSheet.addElement(obstacle);
        }
        ((ToroidalDrawingSheet) this.drawingSheet).drawLimit();
        this.agents = new ArrayList<>();
        for (int i = 0; i < NBAGENT; i++) {
            agents.add(createIa());
        }
        agents.forEach(agent -> {
            Thread thread = new Thread(agent);
            thread.start();
        });
    }

    @Override
    protected void init() {
        super.init();
    }

    private Agent createIa() {
        Turtle turtle = new ToroidalTurtle((ToroidalDrawingSheet) this.drawingSheet);
        Random rand = new Random();
        turtle.droite(rand.nextInt(360));
        turtle.leverCrayon();
        this.drawingSheet.addTortue(turtle);
        Agent agent = new AvoidingAgent(this.drawingSheet, turtle);
        agent.addObserver(this);
        return agent;
    }


}
