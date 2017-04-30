package fr.polytech.model.element;

import java.awt.*;

/**
 * Created by p1511160 on 12/04/2017.
 */
public class Segment extends Element {
    public Point dest;

    public Segment(Point origin, Point dest) {
        this.setOrigin(origin);
        this.dest = dest;
    }

    public Point getDest() {
        return dest;
    }

}
