package fr.polytech.model.agent.Action;

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
}
