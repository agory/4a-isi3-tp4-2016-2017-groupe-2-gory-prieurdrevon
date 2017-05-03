package fr.polytech.model.agent;

import fr.polytech.model.agent.Action.Action;
import fr.polytech.model.agent.Action.MoveForward;

import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by gorya on 03/05/2017.
 */
public class AgentRandom extends Agent{

    private Action compute() {
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0:
                return new MoveForward();
        }
    }
}
