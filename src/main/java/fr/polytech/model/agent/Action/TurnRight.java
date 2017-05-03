package fr.polytech.model.agent.Action;

import fr.polytech.model.element.Segment;
import fr.polytech.model.element.Turtle;

import java.util.Optional;

/**
 * Created by Silver on 03-May-17.
 */
public class TurnRight extends Action{
    private int angle;

    public TurnRight(int angle) {
        this.angle = angle;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    @Override
    public Optional<Segment> run(Turtle turtle) {
        turtle.droite(angle);
        return Optional.empty();
    }
}