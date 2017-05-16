package fr.polytech.model.agent;

import fr.polytech.model.DrawingSheet;
import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.agent.Action.Action;
import fr.polytech.model.agent.Action.MoveForward;
import fr.polytech.model.agent.Action.TurnLeft;
import fr.polytech.model.agent.Action.TurnRight;
import fr.polytech.model.element.Obstacle;
import fr.polytech.model.element.Turtle;

import java.util.*;

/**
 * Created by gorya on 5/3/17.
 */
public class AgentFlocking extends AgentRandom {
    private static final int ANGLE = 90;
    private static final int DIST_MAX = 100;
    private static final int DIST_MIN = 10;

    private MinkFields minkFields;

    public AgentFlocking(DrawingSheet drawingSheet, Turtle turtle) {
        super(drawingSheet, turtle);
        this.minkFields = new MinkFields((ToroidalDrawingSheet) drawingSheet, turtle, ANGLE, DIST_MAX);
    }

    protected List<Action> compute() {

        Map<Obstacle, Double> obstacleDoubleMap = this.minkFields.getVisibleObstacles();
        if (obstacleDoubleMap.size() > 0) {
            System.out.println("Obstacle");
            return obstacleAvoid(obstacleDoubleMap);
        }
        Map<Turtle, Double> turtles = minkFields.getVisibleTurtles();
        if (turtles.size() > 0) {
            return flocking(turtles);
        } else
            return super.compute();
    }

    protected List<Action> obstacleAvoid(Map<Obstacle, Double> obstacleMap) {
        List<Action> actions = new ArrayList<>();
        // Test
        Map.Entry entry = obstacleMap.entrySet().iterator().next();
        int diff = this.turtle.getDir() - (int)entry.getValue();
        if(diff < 0){
          actions.add(new TurnLeft(90));
        } else {
            actions.add(new TurnRight(90));
        }
        actions.add(new MoveForward(1));
        return actions;
    }


    protected List<Action> flocking(Map<Turtle, Double> turtles) {
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
        actions.add(new MoveForward((slow) ? 1 : 2));

        return actions;
    }
}
