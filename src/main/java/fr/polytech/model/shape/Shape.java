package fr.polytech.model.shape;

import fr.polytech.model.element.Segment;
import fr.polytech.model.element.Turtle;

import java.util.List;

/**
 * Created by gorya on 12/04/2017.
 */
public interface Shape {

    List<Segment> draw(Turtle turtle);
}
