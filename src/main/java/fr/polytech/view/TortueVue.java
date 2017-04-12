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
    protected ArrayList<SegmentVue> listSegments; // Trace de la tortue

    public TortueVue(Tortue tortue) {

        listSegments = new ArrayList<SegmentVue>();
        this.tortue = tortue;
    }

    public void avancer(int dist) {
        int newX = (int) Math.round(tortue.getX()+dist*Math.cos(tortue.getRatioDegRad()*tortue.getDir()));
        int newY = (int) Math.round(tortue.getY()+dist*Math.sin(tortue.getRatioDegRad()*tortue.getDir()));

        if (tortue.isCrayon()) {
            SegmentVue seg = new SegmentVue();

            seg.ptStart.x = tortue.getX();
            seg.ptStart.y = tortue.getY();
            seg.ptEnd.x = newX;
            seg.ptEnd.y = newY;
            seg.color = decodeColor(tortue.getColor());

            listSegments.add(seg);
        }

        tortue.setX(newX);
        tortue.setY(newY);
    }

    /** quelques classiques */

    public void carre() {
        for (int i=0;i<4;i++) {
            avancer(100);
            tortue.droite(90);
        }
    }

    public void poly(int n, int a) {
        for (int j=0;j<a;j++) {
            avancer(n);
            tortue.droite(360/a);
        }
    }

    public void spiral(int n, int k, int a) {
        for (int i = 0; i < k; i++) {
            tortue.couleurSuivante();
            avancer(n);
            tortue.droite(360/a);
            n = n+1;
        }
    }

    protected Color decodeColor(int c) {
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

    public void drawTurtle (Graphics graph) {
        if (graph==null)
            return;

        // Dessine les segments
        for(Iterator it = listSegments.iterator(); it.hasNext();) {
            SegmentVue seg = (SegmentVue) it.next();
            seg.drawSegment(graph);
        }

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
