package fr.polytech.model.agent.Action;

import fr.polytech.model.element.Segment;
import fr.polytech.model.element.Turtle;

import java.util.Optional;

/**
 * Created by gorya on 03/05/2017.
 */
public abstract class Action {

    public abstract Optional<Segment> run(Turtle turtle);
}
