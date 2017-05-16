package fr.polytech.model;// package logo;

import fr.polytech.model.element.Element;
import fr.polytech.model.element.Obstacle;
import fr.polytech.model.element.Segment;
import fr.polytech.model.element.Turtle;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Turtle Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 *
 * @author J. Ferber
 * @version 2.0
 */

public class DrawingSheet extends Observable implements Observer {
    private List<Element> elements;
    private Turtle current;


    public DrawingSheet() {
        this.elements = new ArrayList<>();

    }

    public void addTortue(Turtle turtle) {
        this.addElement(turtle);
        turtle.addObserver(this);
    }

    public void reset() {
        elements.clear();
    }

    public void addElement(Element segment) {
        this.elements.add(segment);
        this.setChanged();
    }

    public void addElements(List<Element> segment) {
        this.elements.addAll(elements);
        this.setChanged();
    }

    public void addSegment(Segment segment) {
        this.addElement(segment);
    }

    public void addSegments(List<Segment> segments) {
        this.elements.addAll(segments);
        this.setChanged();
    }

    public List<Element> getElements() {
        return elements;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setChanged();
    }

    public Turtle getCurrentTurtle() {
        return current;
    }

    public void setCurrentTurtle(Turtle current) {
        this.current = current;
    }

    public List<Element> getElementsByOrigin(Point point) {
        return getElementsByOrigin(point, 0);
    }

    public List<Element> getElementsByOrigin(Point point, double diff) {
        return this.getElements().stream()
                .filter(element ->
                        (element.getOrigin().getX() < point.getX() + diff)
                                && (element.getOrigin().getX() > point.getX() - diff)
                                && (element.getOrigin().getY() < point.getY() + diff)
                                && (element.getOrigin().getY() > point.getY() - diff)
                )
                .collect(Collectors.toList());
    }

    public List<Turtle> getTurtles() {
        /*List<Turtle> turtles = new ArrayList<>();
        List<Element> elements = getElements();
        Element element;
        for (int i = 0; i < elements.size(); i++) {
            element = elements.get(i);
            if (element instanceof Turtle) {
                turtles.add((Turtle) element);
            }
        }
        return turtles;*/
        return elements.stream().filter(element -> (element instanceof Turtle)).map(element -> (Turtle) element).collect(Collectors.toList());
    }

    public List<Obstacle> getObstacles(){
        return elements.stream().filter(element -> element instanceof Obstacle).map(element -> (Obstacle) element).collect(Collectors.toList());
    }
}
