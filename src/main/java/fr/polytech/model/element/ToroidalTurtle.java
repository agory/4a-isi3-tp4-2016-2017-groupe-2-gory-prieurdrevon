package fr.polytech.model.element;

import fr.polytech.model.ToroidalDrawingSheet;

import java.awt.*;
import java.util.Optional;

/**
 * Created by Silver on 03-May-17.
 */
public class ToroidalTurtle extends Turtle {
    private ToroidalDrawingSheet drawingSheet;

    public ToroidalTurtle(ToroidalDrawingSheet drawingSheet) {
        super();
        this.drawingSheet = drawingSheet;
    }

    @Override
    public Optional<Segment> avancer(int dist) {
        int newX = (int) Math.round(this.getX() + dist * Math.cos(ratioDegRad * dir));
        int newY = (int) Math.round(this.getY() + dist * Math.sin(ratioDegRad * dir));
        Optional<Segment> segment = Optional.empty();
        if (this.isCrayon()) {
            Point origin = new Point(this.getX(), this.getY());
            Point dest = new Point(newX, newY);
            Segment segment1 = new Segment(origin, dest);
            segment1.setColor(this.getColor());
            segment = Optional.of(segment1);
        }
        this.setOrigin(newX  % drawingSheet.getWidth(),newY  % drawingSheet.getHeight());
        return segment;
    }
}
