package fr.polytech.view.element;

import fr.polytech.model.element.Obstacle;

import java.awt.*;

/**
 * Created by p1511160 on 12/04/2017.
 */

public class ObstacleView implements ElementView {
    public Obstacle obstacle;

    public ObstacleView(Obstacle segment) {
        this.obstacle = segment;
    }

    public void draw(Graphics graph) {
        if (graph == null)
            return;

        graph.setColor(this.obstacle.getColor());
        graph.fillOval(this.obstacle.getOrigin().x,
                this.obstacle.getOrigin().y,
                this.obstacle.getDiameter(),
                this.obstacle.getDiameter());

    }
}
