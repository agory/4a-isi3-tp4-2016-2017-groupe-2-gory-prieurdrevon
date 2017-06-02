package fr.polytech.model.agent;

import fr.polytech.model.DrawingSheet;
import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.agent.Action.Action;
import fr.polytech.model.agent.Action.MoveForward;
import fr.polytech.model.agent.Action.TurnLeft;
import fr.polytech.model.agent.Action.TurnRight;
import fr.polytech.model.agent.Minkfields.TurtleMinkfields;
import fr.polytech.model.element.Obstacle;
import fr.polytech.model.element.Turtle;

import java.util.*;

/**
 * Created by gorya on 5/3/17.
 */
public class FlockingAgent extends RandomAgent {
    protected static final int ANGLE = 90;
    protected static final int DIST_MAX = 40;
    protected static final int DIST_MIN = 10;

    private TurtleMinkfields minkFields;

    public FlockingAgent(DrawingSheet drawingSheet, Turtle turtle) {
        super(drawingSheet, turtle);
        this.minkFields = new TurtleMinkfields((ToroidalDrawingSheet) drawingSheet, turtle, ANGLE, DIST_MAX);
    }

    protected List<Action> compute() {
        Map<Turtle, Double> turtles = minkFields.compute();
        if (turtles.size() > 0) {
            return flocking(turtles);
        }
        return super.compute();
    }

    private List<Action> flocking(Map<Turtle, Double> turtles) {
        boolean slow = false;
        List<Action> actions = new ArrayList<>();
        Turtle turtle;
        int dir = this.turtle.getDir();
        for (Map.Entry<Turtle, Double> entry : turtles.entrySet()) {
            turtle = entry.getKey();
            dir += turtle.getDir();
            if (entry.getValue() < DIST_MIN) {
                slow = true;
            }
        }

        dir = dir / (turtles.size() + 1);
        int diff = dir - this.turtle.getDir();
        if (diff > 0)
            actions.add(new TurnRight(diff));
        else
            actions.add(new TurnLeft(-diff));

        actions.add(new MoveForward((slow) ? 1 : 3));

        return actions;
    }
}
