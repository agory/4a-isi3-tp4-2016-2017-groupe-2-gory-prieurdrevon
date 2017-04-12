package fr.polytech.model.shape;


import fr.polytech.model.Segment;
import fr.polytech.model.Tortue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by gorya on 12/04/2017.
 */
public class Square implements Shape {

    @Override
    public List<Segment> draw(Tortue tortue) {
        List<Segment> segments = new ArrayList<>();
        for (int i=0;i<4;i++) {
            Optional<Segment> segment = tortue.avancer(100);
            segment.ifPresent(segment1 -> segments.add(segment1));
            tortue.droite(90);
        }
        return segments;
    }
}
