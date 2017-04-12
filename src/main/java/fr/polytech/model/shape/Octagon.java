package fr.polytech.model.shape;

import fr.polytech.model.Segment;
import fr.polytech.model.Tortue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by gorya on 12/04/2017.
 */
public class Octagon implements Shape {
    private int n;
    private int a;

    public Octagon(int n, int a) {
        this.n = n;
        this.a = a;
    }

    @Override
    public List<Segment> draw(Tortue tortue) {
        List<Segment> segments = new ArrayList<>();
        for (int j=0;j<a;j++) {
            Optional<Segment> segment = tortue.avancer(n);
            segment.ifPresent(segment1 -> segments.add(segment1));
            tortue.droite(360/a);
        }
        return segments;
    }
}
