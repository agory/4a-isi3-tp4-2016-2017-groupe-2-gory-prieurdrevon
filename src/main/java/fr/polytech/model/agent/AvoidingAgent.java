package fr.polytech.model.agent;

import fr.polytech.model.DrawingSheet;
import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.agent.Action.Action;
import fr.polytech.model.agent.Action.MoveForward;
import fr.polytech.model.agent.Action.TurnLeft;
import fr.polytech.model.agent.Action.TurnRight;
import fr.polytech.model.agent.Minkfields.ObstacleMinkfiels;
import fr.polytech.model.element.Obstacle;
import fr.polytech.model.element.Turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gorya on 22/05/2017.
 */
public class AvoidingAgent extends FlockingAgent {

    private ObstacleMinkfiels obstacleMinkfiels;


    public AvoidingAgent(DrawingSheet drawingSheet, Turtle turtle) {
        super(drawingSheet, turtle);
        this.obstacleMinkfiels = new ObstacleMinkfiels((ToroidalDrawingSheet) drawingSheet, turtle, ANGLE, DIST_MAX);
    }

    @Override
    protected List<Action> compute() {
        Map<Obstacle, Double> obstacleDoubleMap = this.obstacleMinkfiels.compute();
        if (obstacleDoubleMap.size() > 0) {
            System.out.println("Obstacle");
            return obstacleAvoid(obstacleDoubleMap);
        }
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
}
