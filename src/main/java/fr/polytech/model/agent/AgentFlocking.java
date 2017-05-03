package fr.polytech.model.agent;

import fr.polytech.model.DrawingSheet;
import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.agent.Action.Action;
import fr.polytech.model.agent.Action.MoveForward;
import fr.polytech.model.agent.Action.TurnLeft;
import fr.polytech.model.agent.Action.TurnRight;
import fr.polytech.model.element.Turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gorya on 5/3/17.
 */
public class AgentFlocking extends AgentRandom {

    private MinkFields minkFields;

    public AgentFlocking(DrawingSheet drawingSheet, Turtle turtle) {
        super(drawingSheet, turtle);
        this.minkFields = new MinkFields((ToroidalDrawingSheet)drawingSheet,turtle,0,20,50);
    }

    protected List<Action> compute() {
        List<Turtle> turtles = minkFields.getVisibleTurtles();
        if(turtles.size() > 0) {
            return flocking(turtles);
        } else
            return super.compute();
    }


    protected List<Action> flocking(List<Turtle> turtles) {
        List<Action> actions = new ArrayList<>();
        Turtle turtle;
        int dir = this.turtle.getDir();
        for (int i = 0; i < turtles.size();i++) {
            turtle = turtles.get(i);
            dir += turtle.getDir();
        }

        dir = dir / (turtles.size() +1);
        int diff = dir - this.turtle.getDir();
        if(diff > 0)
            actions.add(new TurnRight(diff));
        else
            actions.add(new TurnLeft(-diff));
        actions.add(new MoveForward(1));

        return actions;
    }
}
