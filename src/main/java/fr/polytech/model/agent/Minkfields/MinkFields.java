package fr.polytech.model.agent.Minkfields;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.element.Element;
import fr.polytech.model.element.Obstacle;
import fr.polytech.model.element.Turtle;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.*;

/**
 * Created by gorya on 5/3/17.
 */
public abstract class MinkFields<T extends Element> {
    protected Turtle turtle;
    private ToroidalDrawingSheet sheet;
    private int angle;
    private int distanceMax;

    public MinkFields(ToroidalDrawingSheet sheet, Turtle turtle, int angle, int distanceMax) {
        this.turtle = turtle;
        this.sheet = sheet;
        this.angle = angle;
        this.distanceMax = distanceMax;
    }

    protected List<Element> getElements(){
        return this.sheet.getElements();
    }

    public abstract Map<T,Double> compute();


    protected List<Double> computeDistanceEuclidienne(Element element1, Element element) {
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

    protected boolean isVisible(Turtle turtle, double dist) {
        return dist < distanceMax;
    }

    protected boolean isVisible(Obstacle obstacle,double dist) {
        OptionalDouble distOptional = computeDistanceEuclidienne(this.turtle, obstacle).stream().mapToDouble((i) -> i).min();
        if (distOptional.isPresent()) {
            boolean test = distOptional.getAsDouble() < distanceMax + obstacle.getDiameter() / 2;
            return test;
        }
        System.out.println("bug : " + false);
        return false;
    }

    protected boolean checkAngle(Obstacle obstacle, double dist) {
        return (abs(dist - this.turtle.getDir()) < asin(obstacle.getDiameter() / 2 / (this.distanceMax + obstacle.getDiameter() / 2)));
    }

    protected boolean checkAngle(Turtle turtle){
        return (abs(computeAngle(turtle) - this.turtle.getDir()) < this.angle/2);
    }

    protected double computeAngle(Element obstacle) {
        double angle;
        Point obstaclePoint = obstacle.getOrigin();
        Point turtlePoint = this.turtle.getOrigin();
        Point normalisePoint = this.normalisePoint(turtlePoint, obstaclePoint);

        if (turtlePoint.getX() == normalisePoint.getX()) {
            if (turtlePoint.getY() >= obstaclePoint.getY()) {
                angle = -90;
            } else {
                angle = 90;
            }
        } else {
            angle = 180 / Math.PI * atan((obstaclePoint.getY() - turtlePoint.getY()) / (obstaclePoint.getX() - turtlePoint.getY()));
        }

        return angle;
    }

    protected Point normalisePoint(Point point1, Point point2) {
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
