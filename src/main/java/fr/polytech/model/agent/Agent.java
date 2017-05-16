package fr.polytech.model.agent;

import fr.polytech.Main;
import fr.polytech.controller.IAController;
import fr.polytech.model.DrawingSheet;
import fr.polytech.model.agent.Action.Action;
import fr.polytech.model.element.Segment;
import fr.polytech.model.element.Turtle;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

/**
 * Created by gorya on 03/05/2017.
 */
public abstract class Agent extends Observable implements Runnable {
    protected Turtle turtle;
    protected DrawingSheet drawingSheet;
    protected boolean running = false;

    public Agent(DrawingSheet drawingSheet,Turtle turtle) {
        this.turtle = turtle;
        this.drawingSheet = drawingSheet;
    }

    abstract List<Action> compute();

    @Override
    public void run() {
        running = true;
        while (running) {
            List<Action> actions = this.compute();
            actions.forEach(action ->
                    action.run(turtle).ifPresent(segment -> drawingSheet.addSegment(segment))
            );

            this.setChanged();
            //this.notifyObservers();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
