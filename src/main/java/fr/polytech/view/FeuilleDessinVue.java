package fr.polytech.view;

import fr.polytech.model.FeuilleDessin;
import fr.polytech.model.Segment;
import fr.polytech.model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by gorya on 12/04/2017.
 */
public class FeuilleDessinVue extends JPanel{
    private FeuilleDessin feuilleDessin;

    public FeuilleDessinVue(FeuilleDessin feuilleDessin) {
        this.feuilleDessin = feuilleDessin;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color c = g.getColor();

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0,0,dim.width, dim.height);
        g.setColor(c);

        drawTurtles(g);
        drawSegment(g);
    }

    public void drawTurtles(Graphics g) {
        for(Iterator it = feuilleDessin.getTortues().iterator(); it.hasNext();) {
            Tortue tortue = (Tortue) it.next();
            new TortueVue(tortue).draw(g);
        }
    }

    public void drawSegment(Graphics g) {
        for(Iterator it = feuilleDessin.getSegments().iterator(); it.hasNext();) {
            Segment s = (Segment) it.next();
            new SegmentVue(s).draw(
                    g);
        }
    }
}
