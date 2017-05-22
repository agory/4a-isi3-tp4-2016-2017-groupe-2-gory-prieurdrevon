package fr.polytech.model.agent.Minkfields;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.element.Obstacle;
import fr.polytech.model.element.Turtle;

import java.util.Map;

/**
 * Created by gorya on 22/05/2017.
 */
public class ObstacleMinkfiels extends MinkFields<Obstacle>{
    public ObstacleMinkfiels(ToroidalDrawingSheet sheet, Turtle turtle, int angle, int distanceMax) {
        super(sheet, turtle, angle, distanceMax);
    }

    @Override
    public Map<Obstacle, Double> compute() {
        return null;
    }
}
