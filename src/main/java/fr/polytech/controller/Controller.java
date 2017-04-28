package fr.polytech.controller;

import fr.polytech.model.FeuilleDessin;
import fr.polytech.model.Segment;
import fr.polytech.model.Tortue;
import fr.polytech.model.shape.Octagon;
import fr.polytech.model.shape.Shape;
import fr.polytech.model.shape.Spiral;
import fr.polytech.model.shape.Square;
import fr.polytech.view.Layout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by gorya on 12/04/2017.
 */
public class Controller implements ActionListener {

    private Layout layout;
    private Tortue current;
    private FeuilleDessin feuilleDessin;

    public Controller() {
        this.feuilleDessin = new FeuilleDessin();
        this.layout = new Layout(this, this.feuilleDessin);
        this.layout.setVisible(true);
        this.createTurtle();
    }

    /**
     * la gestion des actions des boutons
     */
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        // actions des boutons du haut
        if (c.equals("Avancer"))
            this.avancerAction();
        else if (c.equals("Droite"))
            this.droiteAction();
        else if (c.equals("Gauche"))
            this.gaucheAction();
        else if (c.equals("Lever"))
            current.leverCrayon();
        else if (c.equals("comboBoxChanged"))
            this.colorChangeAction();
        else if (c.equals("Baisser"))
            current.baisserCrayon();
            // actions des boutons du bas

        else if (c.equals("Proc1"))
            this.drawShape(new Square());
        else if (c.equals("Proc2"))
            this.drawShape(new Octagon(this.getValueBox(),8));
        else if (c.equals("Proc3"))
            this.drawShape(new Spiral(this.getValueBox(),40 , 6));


        else if (c.equals("Effacer")) {
            this.feuilleDessin.reset();
            this.createTurtle();
        } else if (c.equals("Quitter"))
            System.exit(0);
        this.layout.repaint();
    }

    private int getValueBox() {
        String value = this.layout.getInputValue();
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + value);
            return 0;
        }
    }


    private void avancerAction() {
        System.out.println("command avancer");
        String value = this.layout.getInputValue();
        try {
            final int v = Integer.parseInt(value);
            Optional<Segment> segment = current.avancer(v);
            segment.ifPresent(segment1 -> {
                feuilleDessin.addSegment(segment1);
            });
        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + value);
        }
    }

    private void droiteAction() {
        String value = this.layout.getInputValue();
        try {
            int v = Integer.parseInt(value);
            current.droite(v);
        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + value);
        }
    }

    private void gaucheAction() {
        String value = this.layout.getInputValue();
        try {
            int v = Integer.parseInt(value);
            current.gauche(v);
        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + value);
        }
    }

    private void colorChangeAction() {
        current.setColor(this.layout.getInputColor());
    }

    private void drawShape(Shape shape) {
        final Tortue tortue = this.current;
        this.feuilleDessin.addSegments(shape.draw(this.current));
    }

    public void createTurtle() {
        Tortue tortue = new Tortue();

        // Deplacement de la tortue au centre de la feuille
        tortue.setPosition(500 / 2, 400 / 2);

        this.current = tortue;
        this.feuilleDessin.addTortue(tortue);
    }

}
