package fr.polytech.model.agent;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.element.Turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gorya on 5/3/17.
 */
public class MinkFields {
    private List<Turtle> turtles;
    private Turtle turtle;
    private ToroidalDrawingSheet sheet;
    private int angle;
    private int distanceMin;
    private int distanceMax;

    public MinkFields(ToroidalDrawingSheet sheet, Turtle turtle,  int angle, int distanceMin,int distanceMax) {
        this.turtle = turtle;
        this.turtles = sheet.getTurtles().stream().filter(turtle1 -> !(turtle == turtle1)).collect(Collectors.toList());
        this.sheet = sheet;
        this.angle = angle;
        this.distanceMin = distanceMin;
        this.distanceMax = distanceMax;
    }

    public List getVisibleTurtles() {
        return this.turtles.stream().filter(this::isVisibleTurtles).collect(Collectors.toList());
    }

    private boolean isVisibleTurtles(Turtle turtle) {
        double dist = Math.sqrt(Math.pow(this.turtle.getX() - turtle.getX(), 2) + Math.pow(this.turtle.getY() - turtle.getY(), 2));
        return dist < distanceMax && dist > distanceMin;
    }


}
