package fr.polytech.model;



// package logo;

import fr.polytech.view.SegmentVue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;


/*************************************************************************

 Un petit Logo minimal qui devra etre ameliore par la suite

 Source originale : J. Ferber - 1999-2001

 Cours de DESS TNI - Montpellier II

 @version 2.0
 @date 25/09/2001

 **************************************************************************/


public class Tortue
{



    protected static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
    protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)



    protected int x, y;
    protected int dir;
    protected boolean crayon;
    protected int coul;

    public void setColor(int n) {coul = n;}
    public int getColor() {return coul;}

    public Tortue() {
        reset();
    }

    public void reset() {
        x = 0;
        y = 0;
        dir = -90;
        coul = 0;
        crayon = true;
    }

    public Optional<Segment> avancer(int dist) {
        int newX = (int) Math.round(x+dist*Math.cos(ratioDegRad*dir));
        int newY = (int) Math.round(y+dist*Math.sin(ratioDegRad*dir));
        Optional<Segment> segment = Optional.empty();
        if (this.isCrayon()) {
            Point origin = new Point(x,y);
            Point dest = new Point(newX,newY);
            Segment segment1 = new Segment(origin,dest);
            segment1.setColor(this.decodeColor(this.getColor()));
            segment= Optional.of(segment1);
        }

        this.x = newX;
        this.y = newY;
        return segment;
    }



    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public void droite(int ang) {
        dir = (dir + ang) % 360;
    }

    public void gauche(int ang) {
        dir = (dir - ang) % 360;
    }

    public void baisserCrayon() {
        crayon = true;
    }

    public void leverCrayon() {
        crayon = false;
    }

    public void couleur(int n) {
        coul = n % 12;
    }

    public void couleurSuivante() {
        couleur(coul+1);
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
        return x;
    }

    public int getY() {
        return y;
    }

    public static double getRatioDegRad() {
        return ratioDegRad;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Color decodeColor(int c) {
        switch(c) {
            case 0: return(Color.black);
            case 1: return(Color.blue);
            case 2: return(Color.cyan);
            case 3: return(Color.darkGray);
            case 4: return(Color.red);
            case 5: return(Color.green);
            case 6: return(Color.lightGray);
            case 7: return(Color.magenta);
            case 8: return(Color.orange);
            case 9: return(Color.gray);
            case 10: return(Color.pink);
            case 11: return(Color.yellow);
            default : return(Color.black);
        }
    }
}

