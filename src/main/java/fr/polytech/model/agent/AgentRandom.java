package fr.polytech.model.agent;

import fr.polytech.model.DrawingSheet;
import fr.polytech.model.agent.Action.Action;
import fr.polytech.model.agent.Action.MoveForward;
import fr.polytech.model.agent.Action.TurnLeft;
import fr.polytech.model.agent.Action.TurnRight;
import fr.polytech.model.element.Turtle;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gorya on 03/05/2017.
 */
public class AgentRandom extends Agent{
    public AgentRandom(DrawingSheet drawingSheet,Turtle turtle) {
        super(drawingSheet,turtle);
    }

    protected List<Action> compute() {
        Random random = new Random();
        List<Action> actions = new ArrayList<>();
        int number = random.nextInt(3);
        if(number > 3)
            number = 0;
        switch (number) {
            case 0:
                actions.add(new MoveForward(1));
                break;
            case 1:
                actions.add(new TurnLeft(3));
                break;
            case 2:
                actions.add(new TurnRight(3));
                break;
            default:
                actions.add(new MoveForward(2));
                break;
        }
        return actions;
    }
}
