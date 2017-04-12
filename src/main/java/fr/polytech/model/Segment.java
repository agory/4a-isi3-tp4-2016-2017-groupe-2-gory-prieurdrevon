package fr.polytech.model;

import java.awt.*;

/**
 * Created by p1511160 on 12/04/2017.
 */
public class Segment {
    public Point ptStart, ptEnd;
    public Color color;

    public Segment() {
        ptStart = new Point(0,0);
        ptEnd = new Point(0,0);
    }

    public Segment(Point ptStart, Point ptEnd) {
        this.ptStart = ptStart;
        this.ptEnd = ptEnd;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getPtStart() {
        return ptStart;
    }

    public Point getPtEnd() {
        return ptEnd;
    }

    public Color getColor() {
        return color;
    }
}
