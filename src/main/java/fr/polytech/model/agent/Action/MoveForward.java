package fr.polytech.model.agent.Action;

import fr.polytech.model.element.Segment;
import fr.polytech.model.element.Turtle;

import java.util.Optional;

/**
 * Created by gorya on 03/05/2017.
 */
public class MoveForward extends Action{
    private int distance;

    public MoveForward(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public Optional<Segment> run(Turtle turtle) {
        return turtle.avancer(distance);
    }
}
