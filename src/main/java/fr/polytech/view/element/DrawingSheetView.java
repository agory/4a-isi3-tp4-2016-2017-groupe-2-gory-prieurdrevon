package fr.polytech.view.element;

import fr.polytech.model.DrawingSheet;
import fr.polytech.model.element.Element;
import fr.polytech.model.element.Segment;
import fr.polytech.model.element.Turtle;
import fr.polytech.view.element.ElementView;
import fr.polytech.view.element.SegmentView;
import fr.polytech.view.element.TurtleView;


import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by gorya on 12/04/2017.
 */
public class DrawingSheetView extends JPanel implements Observer,ElementView{
    private DrawingSheet drawingSheet;

    public DrawingSheetView(DrawingSheet drawingSheet) {
        this.drawingSheet = drawingSheet;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color c = g.getColor();
        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0,0,dim.width, dim.height);
        g.setColor(c);
        draw(g);
    }

    public void draw(Graphics g) {
        this.drawingSheet.getElements().forEach(element -> {
            if(element instanceof Turtle)
                new TurtleView((Turtle) element).draw(g);
            if(element instanceof Segment)
                new SegmentView((Segment) element).draw(g);
        });

    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
