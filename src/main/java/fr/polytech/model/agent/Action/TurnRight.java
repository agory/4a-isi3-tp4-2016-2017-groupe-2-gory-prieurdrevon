package fr.polytech.model.agent.Action;

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
}