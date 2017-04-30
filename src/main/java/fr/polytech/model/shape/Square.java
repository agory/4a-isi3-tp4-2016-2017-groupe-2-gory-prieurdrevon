package fr.polytech.model.shape;


import fr.polytech.model.element.Segment;
import fr.polytech.model.element.Turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by gorya on 12/04/2017.
 */
public class Square implements Shape {

    @Override
    public List<Segment> draw(Turtle turtle) {
        List<Segment> segments = new ArrayList<>();
        for (int i=0;i<4;i++) {
            Optional<Segment> segment = turtle.avancer(100);
            segment.ifPresent(segment1 -> segments.add(segment1));
            turtle.droite(90);
        }
        return segments;
    }
}
