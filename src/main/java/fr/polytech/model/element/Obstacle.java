package fr.polytech.model.element;

import java.awt.*;

/**
 * Created by Silver on 16-May-17.
 */
public class Obstacle extends Element {
    protected int diameter;

    public Obstacle(Point origin, int diameter) {
        this.setOrigin(origin);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }
}
