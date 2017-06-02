package fr.polytech.model.agent.Minkfields;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.element.Obstacle;
import fr.polytech.model.element.Turtle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Created by gorya on 22/05/2017.
 */
public class ObstacleMinkfiels extends MinkFields<Obstacle> {
    public ObstacleMinkfiels(ToroidalDrawingSheet sheet, Turtle turtle, int angle, int distanceMax) {
        super(sheet, turtle, angle, distanceMax);
    }

    private List<Obstacle> getObstacles() {
        return this.getElements().stream().filter(element -> (element instanceof Obstacle)).map(element -> (Obstacle) element).collect(Collectors.toList());
    }

    @Override
    public Map<Obstacle, Double> compute() {
        Map<Obstacle, Double> obstacles = new HashMap<>();

        this.getObstacles().forEach(obstacle -> {
            OptionalDouble distOptional = computeDistanceEuclidienne(this.turtle, obstacle).stream().mapToDouble((i) -> i).min();
            if (distOptional.isPresent())
                obstacles.put(obstacle, distOptional.getAsDouble());
        });

        return obstacles.entrySet().stream()
                .filter(entry -> this.isVisible(entry.getKey(), entry.getValue()))
                //.filter(entry -> this.checkAngle(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    protected boolean isVisible(Obstacle obstacle, double dist) {
        return dist < distanceMax + obstacle.getDiameter() / 2;
    }

}
