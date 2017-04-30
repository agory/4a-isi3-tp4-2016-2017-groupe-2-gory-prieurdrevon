package fr.polytech.view.element;

import fr.polytech.model.element.Turtle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by p1511160 on 12/04/2017.
 */
public class TurtleView extends JComponent implements ElementView, MouseListener {

    private Turtle turtle;

    public TurtleView(Turtle turtle) {
        this.turtle = turtle;
    }

    public void draw (Graphics graph) {
        if (graph==null)
            return;


        //Calcule les 3 coins du triangle a partir de
        // la position de la turtle p
        Point p = new Point(turtle.getX(), turtle.getY());
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta = turtle.getRatioDegRad()*(-turtle.getDir());
        //Demi angle au sommet du triangle
        double alpha=Math.atan( (float) Turtle.getRb()/ (float) Turtle.getRp() );
        //Rayon de la fleche
        double r=Math.sqrt( Turtle.getRp()* Turtle.getRp() + Turtle.getRb()* Turtle.getRb() );
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
        graph.setColor(turtle.getColor());
        graph.fillPolygon(arrow);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX()+ e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getX()+ e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println(e.getX()+ e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println(e.getX()+ e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println(e.getX()+ e.getY());
    }
}
