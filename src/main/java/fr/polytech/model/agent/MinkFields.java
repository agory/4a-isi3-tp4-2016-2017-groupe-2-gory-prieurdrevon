package fr.polytech.model.agent;

import fr.polytech.model.ToroidalDrawingSheet;
import fr.polytech.model.element.Turtle;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static java.lang.Math.atan;

/**
 * Created by gorya on 5/3/17.
 */
public class MinkFields {
    private List<Turtle> turtles;
    private Turtle turtle;
    private ToroidalDrawingSheet sheet;
    private int angle;
    private int distanceMax;

    public MinkFields(ToroidalDrawingSheet sheet, Turtle turtle, int angle, int distanceMax) {
        this.turtle = turtle;
        this.turtles = sheet.getTurtles().stream().filter(turtle1 -> !(turtle == turtle1)).collect(Collectors.toList());
        this.sheet = sheet;
        this.angle = angle;
        this.distanceMax = distanceMax;
    }

    public Map<Turtle,Double> getVisibleTurtles() {
        Map<Turtle,Double> turtles = new HashMap<>();

        this.turtles.forEach(turtle -> {
            OptionalDouble distOptional = computeDistanceEuclidienne(this.turtle, turtle).stream().mapToDouble((i) -> i).min();
            if(distOptional.isPresent())
                turtles.put(turtle,distOptional.getAsDouble());
        });

        return turtles.entrySet().stream()
                .filter(this::isVisibleTurtle)
//                .filter(this::checkAngle)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    private boolean isVisibleTurtle(Map.Entry<Turtle,Double> entry) {
        return entry.getValue() < distanceMax;
    }

    private boolean checkAngle(Map.Entry<Turtle,Double> entry) {
        int angle;
        int x1 = this.turtle.getX();
        int y1 = this.turtle.getY();
        int x2 = entry.getKey().getX();
        int y2 = entry.getKey().getY();
        if (abs(x1-x2) > entry.getValue()){
            if (x1 < x2){
                x2 = x2 - this.sheet.getWidth();
            }
            else{
                x2 = x2 + this.sheet.getWidth();
            }
        }
        if (abs(y1-y2) > entry.getValue()){
            if (y1 < y2){
                y2 = y2 - this.sheet.getHeight();
            }
            else{
                y2 = y2 + this.sheet.getHeight();
            }
        }
        if (x1 == x2){
            if (y1 < y2){
                angle = 90;
            }
            else{
                angle = -90;
            }
        }
        else{
            angle = (int) (180/Math.PI * atan((y2-y1)/(x2-x1)));
        }

        return (abs(angle - this.turtle.getDir()) < this.angle/2);
    }


    private List<Double> computeDistanceEuclidienne(Turtle turtle, Turtle turtle1) {
        List<Double> dists = new ArrayList<>();
        // normal distance
        dists.add(Math.sqrt(Math.pow(turtle.getX() - turtle1.getX(), 2) + Math.pow(turtle.getY() - turtle1.getY(), 2)));

        // Toroidal distance axe X
        dists.add(Math.sqrt(Math.pow(turtle.getX() - (turtle1.getX() + this.sheet.getWidth()), 2) + Math.pow(turtle.getY() - turtle1.getY(), 2)));

        // Toroidal distance axe Y
        dists.add(Math.sqrt(Math.pow(turtle.getX() - turtle1.getX(), 2) + Math.pow(turtle.getY() - (turtle1.getY() + this.sheet.getHeight()), 2)));

        // Toroidal distance axe Y and X
        dists.add(Math.sqrt(Math.pow(turtle.getX() - (turtle1.getX() + this.sheet.getWidth()), 2) + Math.pow(turtle.getY() - (turtle1.getY() + this.sheet.getHeight()), 2)));
        return dists;
    }





}
