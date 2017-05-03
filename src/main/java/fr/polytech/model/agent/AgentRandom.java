package fr.polytech.model.agent;

import fr.polytech.model.DrawingSheet;
import fr.polytech.model.agent.Action.Action;
import fr.polytech.model.agent.Action.MoveForward;
import fr.polytech.model.agent.Action.TurnLeft;
import fr.polytech.model.agent.Action.TurnRight;
import fr.polytech.model.element.Turtle;

import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by gorya on 03/05/2017.
 */
public class AgentRandom extends Agent{
    public AgentRandom(DrawingSheet drawingSheet,Turtle turtle) {
        super(drawingSheet,turtle);
    }

    protected Action compute() {
        Random random = new Random();
        int number = random.nextInt(100);
        if(number > 3)
            number = 0;
        switch (number) {
            case 0:
                return new MoveForward(1/*random.nextInt(360)*/);
            case 1:
                return new TurnLeft(random.nextInt(360));
            case 2:
                return new TurnRight(random.nextInt(360));
        }
        return new MoveForward(random.nextInt(360));
    }
}
