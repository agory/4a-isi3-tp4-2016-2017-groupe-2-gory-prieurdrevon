package fr.polytech.model;// package logo;

import fr.polytech.model.Segment;
import fr.polytech.model.Tortue;
import fr.polytech.view.SegmentVue;
import fr.polytech.view.TortueVue;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 *
 * @author J. Ferber
 * @version 2.0
 */

public class FeuilleDessin extends Observable implements Observer {
    private List<Tortue> tortues; // la liste des tortues enregistrees
    private List<Segment> segments;

    public FeuilleDessin() {
        tortues = new ArrayList<>();
        segments = new ArrayList<>();
    }

    public void addTortue(Tortue o) {
        tortues.add(o);
        o.addObserver(this);
        this.setChanged();
    }

    public void reset() {
        tortues.clear();
        segments.clear();
    }

    public void addSegment(Segment segment) {
        this.segments.add(segment);
        this.setChanged();
    }

    public void addSegments(List<Segment> segments) {
        this.segments.addAll(segments);
        this.setChanged();
    }

    public List<Tortue> getTortues() {
        return tortues;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setChanged();
    }
}
