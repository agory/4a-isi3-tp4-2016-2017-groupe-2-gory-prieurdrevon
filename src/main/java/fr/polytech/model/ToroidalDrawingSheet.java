package fr.polytech.model;

import fr.polytech.model.DrawingSheet;
import fr.polytech.model.element.Segment;

import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

/**
 * Created by gorya on 03/05/2017.
 */
public class ToroidalDrawingSheet extends DrawingSheet {

    private int width;
    private int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    @Override
    public void addSegment(Segment segment) {
        if (segment.getDest().getX() < 0){
            Point orig = segment.getOrigin();
            Point dest = segment.getDest();
            int midY = (int) floor(orig.getY() + (orig.getY()-dest.getY())*orig.getX()/abs(dest.getX()-orig.getX()));
            addSegment(new Segment(orig, new Point(0, midY)));
            addSegment(new Segment(new Point(width, midY), new Point((int)dest.getX() % width, (int)dest.getY())));
        }
        else if (segment.getOrigin().getX() > this.width){
            Point orig = segment.getOrigin();
            Point dest = segment.getDest();
            int midY = (int) floor(orig.getY() + (orig.getY()-dest.getY())*orig.getX()/abs(dest.getX()-orig.getX()));
            addSegment(new Segment(orig, new Point(width, midY)));
            addSegment(new Segment(new Point(0, midY), new Point((int)dest.getX() % width, (int)dest.getY())));
        }
        else if (segment.getOrigin().getY() < 0){
            Point orig = segment.getOrigin();
            Point dest = segment.getDest();
            int midX = (int) floor(orig.getX() + (orig.getX()-dest.getX())*orig.getY()/abs(dest.getY()-orig.getY()));
            addSegment(new Segment(orig, new Point(midX, 0)));
            addSegment(new Segment(new Point(midX, height), new Point((int)dest.getX(), (int)dest.getY() % height)));
        }
        else if (segment.getOrigin().getY() > this.height){
            Point orig = segment.getOrigin();
            Point dest = segment.getDest();
            int midX = (int) floor(orig.getX() + (orig.getX()-dest.getX())*orig.getY()/abs(dest.getY()-orig.getY()));
            addSegment(new Segment(orig, new Point(midX, height)));
            addSegment(new Segment(new Point(midX, 0), new Point((int)dest.getX(), (int)dest.getY() % height)));
        }
        else{
            super.addSegment(segment);
        }

    }
}
