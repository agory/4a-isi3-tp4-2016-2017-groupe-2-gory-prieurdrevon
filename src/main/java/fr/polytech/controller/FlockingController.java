package fr.polytech.controller;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.agent.Agent;
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

    private static int NBAGENT = 10;
    private List<Agent> agents;


    protected void startIA() {
        Obstacle obstacle = new Obstacle(new Point(300,200),30);
        obstacle.setColor(Color.RED);
        this.drawingSheet.addElement(obstacle);
        ((ToroidalDrawingSheet)this.drawingSheet).drawLimit();
        this.agents = new ArrayList<>();
        for (int i = 0; i < NBAGENT; i++){
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
        turtle.leverCrayon();
        this.drawingSheet.addTortue(turtle);
        Agent agent = new FlockingAgent(this.drawingSheet,turtle);
        agent.addObserver(this);
        return agent;
    }


}
