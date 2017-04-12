package fr.polytech.view;

import fr.polytech.model.Tortue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by p1511160 on 12/04/2017.
 */
public class TortueVue {

    private Tortue tortue;

    public TortueVue(Tortue tortue) {

        this.tortue = tortue;
    }

    public void draw (Graphics graph) {
        if (graph==null)
            return;


        //Calcule les 3 coins du triangle a partir de
        // la position de la tortue p
        Point p = new Point(tortue.getX(),tortue.getY());
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta = tortue.getRatioDegRad()*(-tortue.getDir());
        //Demi angle au sommet du triangle
        double alpha=Math.atan( (float) Tortue.getRb()/ (float) Tortue.getRp() );
        //Rayon de la fleche
        double r=Math.sqrt( Tortue.getRp()*Tortue.getRp() + Tortue.getRb()*Tortue.getRb() );
        //Sens de la fleche

        //Pointe
        Point p2=new Point((int) Math.round(p.x+r*Math.cos(theta)),
                (int) Math.round(p.y-r*Math.sin(theta)));
        arrow.addPoint(p2.x,p2.y);
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

        //Base2
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

        arrow.addPoint(p2.x,p2.y);
        graph.setColor(Color.green);
        graph.fillPolygon(arrow);
    }


}
