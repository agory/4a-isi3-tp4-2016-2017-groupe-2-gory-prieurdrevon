package fr.polytech.model.agent;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.element.Element;
import fr.polytech.model.element.Obstacle;
import fr.polytech.model.element.Turtle;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static java.lang.Math.asin;
import static java.lang.Math.atan;

/**
 * Created by gorya on 5/3/17.
 */
public class MinkFields {
    private List<Turtle> turtles;
    private List<Obstacle> obstacles;
    private Turtle turtle;
    private ToroidalDrawingSheet sheet;
    private int angle;
    private int distanceMax;

    public MinkFields(ToroidalDrawingSheet sheet, Turtle turtle, int angle, int distanceMax) {
        this.turtle = turtle;
        this.turtles = sheet.getTurtles().stream().filter(turtle1 -> !(turtle == turtle1)).collect(Collectors.toList());
        this.obstacles = sheet.getObstacles();
        this.sheet = sheet;
        this.angle = angle;
        this.distanceMax = distanceMax;
    }

    public Map<Turtle, Double> getVisibleTurtles() {
        Map<Turtle, Double> turtles = new HashMap<>();

        this.turtles.forEach(turtle -> {
            OptionalDouble distOptional = computeDistanceEuclidienne(this.turtle, turtle).stream().mapToDouble((i) -> i).min();
            if (distOptional.isPresent())
                turtles.put(turtle, distOptional.getAsDouble());
        });

        return turtles.entrySet().stream()
                .filter(this::isVisibleTurtle)
//                .filter(this::checkAngleTurtle)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    private List<Double> computeDistanceEuclidienne(Element element1, Element element) {
        List<Double> dists = new ArrayList<>();
        // normal distance
        dists.add(Math.sqrt(Math.pow(element1.getX() - element.getOrigin().getX(), 2) + Math.pow(element1.getY() - element.getOrigin().getY(), 2)));

        // Toroidal distance axe X
        dists.add(Math.sqrt(Math.pow(element1.getX() - (element.getOrigin().getX() + this.sheet.getWidth()), 2) + Math.pow(element1.getY() - element.getOrigin().getY(), 2)));

        // Toroidal distance axe Y
        dists.add(Math.sqrt(Math.pow(element1.getX() - element.getOrigin().getX(), 2) + Math.pow(element1.getY() - (element.getOrigin().getY() + this.sheet.getHeight()), 2)));

        // Toroidal distance axe Y and X
        dists.add(Math.sqrt(Math.pow(element1.getX() - (element.getOrigin().getX() + this.sheet.getWidth()), 2) + Math.pow(element1.getY() - (element.getOrigin().getY() + this.sheet.getHeight()), 2)));
        return dists;
    }

    private boolean isVisibleTurtle(Map.Entry<Turtle, Double> entry) {
        return entry.getValue() < distanceMax;
    }



    private boolean checkAngleTurtle(Map.Entry<Turtle,Double> entry){
        return (abs(computeAngle(entry.getKey()) - this.turtle.getDir()) < this.angle/2);
    }
    /*
    * New Functionnalities Obstacle avoid
    *
    * */







    public Map<Obstacle, Double> getVisibleObstacles() {
        Map<Obstacle, Double> obstacles = new HashMap<>();

        this.obstacles.forEach(obstacle ->
                obstacles.put(obstacle, this.computeAngle(obstacle))
        );

        return obstacles.entrySet().stream()
                .filter(this::isVisibleObstacle)
                .filter(this::checkAngleObstacle)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    private boolean isVisibleObstacle(Map.Entry<Obstacle, Double> entry) {
        OptionalDouble distOptional = computeDistanceEuclidienne(this.turtle, turtle).stream().mapToDouble((i) -> i).min();
        if (distOptional.isPresent()) {
            boolean test = distOptional.getAsDouble() < distanceMax + entry.getKey().getDiameter() / 2;
            System.out.println(test);
            return test;
        }
        System.out.println("bug : " + false);
        return false;
    }


    private boolean checkAngleObstacle(Map.Entry<Obstacle,Double> entry) {
        return (abs(entry.getValue() - this.turtle.getDir()) < asin(entry.getKey().getDiameter() / 2 / (this.distanceMax + entry.getKey().getDiameter() / 2)));
    }

    private double computeAngle(Element obstacle) {
        double angle;
        Point turtlePoint = this.turtle.getOrigin();
        Point obstaclePoint = obstacle.getOrigin();
        Point normalisePoint = this.normalisePoint(turtlePoint, obstaclePoint);

        if (turtlePoint.getX() == normalisePoint.getX()) {
            if (turtlePoint.getY() < obstaclePoint.getY()) {
                angle = 90;
            } else {
                angle = -90;
            }
        } else {
            angle = 180 / Math.PI * atan((obstaclePoint.getY() - turtlePoint.getY()) / (obstaclePoint.getX() - turtlePoint.getY()));
        }

        return angle;
    }

    private Point normalisePoint(Point point1, Point point2) {
        int x = 0, y = 0;
        if (abs(point1.getX() - point2.getX()) > this.distanceMax) {
            if (point1.getX() < point2.getX()) {
                x = (int) point2.getX() - this.sheet.getWidth();
            } else {
                x = (int) point2.getX() + this.sheet.getWidth();
            }
        }
        if (abs(point1.getY() - point2.getY()) > this.distanceMax) {
            if (point1.getY() < point2.getY()) {
                y = (int) point2.getY() - this.sheet.getHeight();
            } else {
                y = (int) point2.getY() + this.sheet.getHeight();
            }
        }

        return new Point(x, y);
    }



}
