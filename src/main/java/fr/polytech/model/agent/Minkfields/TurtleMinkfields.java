package fr.polytech.model.agent.Minkfields;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.element.Turtle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Created by gorya on 22/05/2017.
 */
public class TurtleMinkfields extends MinkFields<Turtle> {
    private List<Turtle> turtles;

    public TurtleMinkfields(ToroidalDrawingSheet sheet, Turtle turtle, int angle, int distanceMax) {
        super(sheet, turtle, angle, distanceMax);
        this.turtles = this.getElements().stream().filter(element -> (element instanceof Turtle)).map(element -> (Turtle) element).collect(Collectors.toList());
    }

    private List<Turtle> getTurtles(){
        return this.turtles;
    }

    @Override
    public Map<Turtle,Double> compute() {
        Map<Turtle, Double> turtles = new HashMap<>();

        this.getTurtles().forEach(turtle -> {
            OptionalDouble distOptional = computeDistanceEuclidienne(this.turtle, turtle).stream().mapToDouble((i) -> i).min();
            if (distOptional.isPresent())
                turtles.put(turtle, distOptional.getAsDouble());
        });

        return turtles.entrySet().stream()
                .filter(entry -> this.isVisible(entry.getKey(),entry.getValue()))
//                .filter(this::checkAngleTurtle)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
