package fr.polytech.model.element;

import java.awt.*;
import java.util.Optional;


/*************************************************************************

 Un petit Logo minimal qui devra etre ameliore par la suite

 Source originale : J. Ferber - 1999-2001

 Cours de DESS TNI - Montpellier II

 @version 2.0
 @date 25/09/2001

 **************************************************************************/


public class Turtle extends Element {
    protected static final int rp = 10, rb = 5; // Taille de la pointe et de la base de la fleche
    protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
    protected int dir;
    protected boolean crayon;


    public Turtle() {
        reset();
    }

    public void reset() {
        this.setOrigin(0,0);
        dir = -90;
        crayon = true;
    }

    public Optional<Segment> avancer(int dist) {
        int newX = (int) Math.round(this.getX() + dist * Math.cos(ratioDegRad * dir));
        int newY = (int) Math.round(this.getY() + dist * Math.sin(ratioDegRad * dir));
        Optional<Segment> segment = Optional.empty();
        if (this.isCrayon()) {
            Point origin = new Point(this.getX(), this.getY());
            Point dest = new Point(newX, newY);
            Segment segment1 = new Segment(origin, dest);
            segment1.setColor(this.getColor());
            segment = Optional.of(segment1);
        }
        this.setOrigin(newX,newY);
        return segment;
    }


    public void setPosition(int newX, int newY) {
        this.setOrigin(newX,newY);
    }

    public void setDir(int dir) {
        this.dir = dir;
        this.setChanged();
    }

    public void droite(int ang) {
        this.setDir((dir + ang) % 360);
    }

    public void gauche(int ang) {
        this.setDir((dir - ang) % 360);
    }

    public void baisserCrayon() {
        crayon = true;
    }

    public void leverCrayon() {
        crayon = false;
    }


    public static int getRp() {
        return rp;
    }

    public static int getRb() {
        return rb;
    }

    public int getDir() {
        return dir;
    }

    public boolean isCrayon() {
        return crayon;
    }

    public int getX() {
        return (int)this.getOrigin().getX();
    }

    public int getY() {
        return (int)this.getOrigin().getY();
    }

    public static double getRatioDegRad() {
        return ratioDegRad;
    }

}

