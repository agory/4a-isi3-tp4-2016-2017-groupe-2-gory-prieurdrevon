package fr.polytech.controller;

import fr.polytech.model.DrawingSheet;
import fr.polytech.model.element.Element;
import fr.polytech.model.element.Segment;
import fr.polytech.model.element.Turtle;
import fr.polytech.model.shape.Octagon;
import fr.polytech.model.shape.Shape;
import fr.polytech.model.shape.Spiral;
import fr.polytech.model.shape.Square;
import fr.polytech.model.utils.ColorService;
import fr.polytech.view.Layout;
import fr.polytech.view.LayoutUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by gorya on 12/04/2017.
 */
public class UserController extends Controller {
    @Override
    void init() {
        this.drawingSheet = new DrawingSheet();
        this.layout = new LayoutUser(this, this.drawingSheet);
        this.createTurtle();
    }

    /**
     * la gestion des actions des boutons
     */
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        System.out.println(c);
        // actions des boutons du haut
        if (c.equals("Avancer"))
            this.avancerAction();
        else if (c.equals("Droite"))
            this.droiteAction();
        else if (c.equals("Gauche"))
            this.gaucheAction();
        else if (c.equals("Lever"))
            this.drawingSheet.getCurrentTurtle().leverCrayon();
        else if (c.equals("comboBoxChanged"))
            this.colorChangeAction();
        else if (c.equals("Baisser"))
            this.drawingSheet.getCurrentTurtle().baisserCrayon();
        else if (c.equals("Add"))
            this.addTurtle();
        else if (c.equals("Proc1"))
            this.drawShape(new Square());
        else if (c.equals("Proc2"))
            this.drawShape(new Octagon(this.getValueBox(),8));
        else if (c.equals("Proc3"))
            this.drawShape(new Spiral(this.getValueBox(),40 , 6));
        else if (c.equals("Effacer")) {
            this.drawingSheet.reset();
            this.createTurtle();
        } else if (c.equals("Quitter"))
            System.exit(0);
        this.layout.repaint();
    }

    public void mouseClickPerformed(MouseEvent mouseEvent) {
        List<Element> elements = this.drawingSheet.getElementsByOrigin(mouseEvent.getPoint(),10);
        Iterator it = elements.iterator();
        boolean found = false;
        // recuperation de la premier turtle
        while (it.hasNext() && !found ) {
            Element element = (Element) it.next();
            if(element instanceof Turtle) {
                found = true;
                this.drawingSheet.setCurrentTurtle((Turtle) element);
            }
        }
    }

    private int getValueBox() {
        String value = this.getLayout().getInputValue();
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + value);
            return 0;
        }
    }

    private void addTurtle() {
        this.createTurtle();
    }


    private void avancerAction() {
        String value = this.getLayout().getInputValue();
        try {
            final int v = Integer.parseInt(value);
            Optional<Segment> segment = this.drawingSheet.getCurrentTurtle().avancer(v);
            segment.ifPresent(segment1 -> {
                drawingSheet.addSegment(segment1);
            });
        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + value);
        }
    }

    private void droiteAction() {
        String value = this.getLayout().getInputValue();
        try {
            int v = Integer.parseInt(value);
            this.drawingSheet.getCurrentTurtle().droite(v);
        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + value);
        }
    }

    private void gaucheAction() {
        String value = this.getLayout().getInputValue();
        try {
            int v = Integer.parseInt(value);
            this.drawingSheet.getCurrentTurtle().gauche(v);
        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + value);
        }
    }

    private void colorChangeAction() {
        ColorService colorService = ColorService.getInstance();
        this.drawingSheet.getCurrentTurtle().setColor(colorService.decode(this.getLayout().getInputColor()));
    }

    private void drawShape(Shape shape) {
        this.drawingSheet.addSegments(shape.draw(this.drawingSheet.getCurrentTurtle()));
    }

    public void createTurtle() {
        Turtle turtle = new Turtle();
        // Deplacement de la turtle au centre de la feuille
        turtle.setPosition(500 / 2, 400 / 2);
        this.drawingSheet.setCurrentTurtle(turtle);
        this.drawingSheet.addTortue(turtle);
    }

    public LayoutUser getLayout() {
        return (LayoutUser) this.layout;
    }

}
