package fr.polytech.model.shape;

import fr.polytech.model.Segment;
import fr.polytech.model.Tortue;

import java.util.List;

/**
 * Created by gorya on 12/04/2017.
 */
public interface Shape {

    List<Segment> draw(Tortue tortue);
}
