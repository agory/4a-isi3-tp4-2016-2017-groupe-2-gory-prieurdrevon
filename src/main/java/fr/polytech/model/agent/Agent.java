package fr.polytech.model.agent;

import fr.polytech.controller.IAController;
import fr.polytech.model.agent.Action.Action;

import java.awt.event.ActionListener;

/**
 * Created by gorya on 03/05/2017.
 */
public abstract class Agent implements Runnable {
    private ActionListener listener;
    private boolean running = false;

    public Agent(ActionListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            listener.IAAction(compute());
        }

    }

    abstract Action compute();
}
