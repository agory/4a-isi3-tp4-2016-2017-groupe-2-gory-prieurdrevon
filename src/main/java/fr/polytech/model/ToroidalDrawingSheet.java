package fr.polytech.model;

import com.sun.org.apache.xpath.internal.SourceTree;
import fr.polytech.model.DrawingSheet;
import fr.polytech.model.element.Segment;
import fr.polytech.model.utils.MathTools;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

/**
 * Created by gorya on 03/05/2017.
 */
public class ToroidalDrawingSheet extends DrawingSheet {
    public ToroidalDrawingSheet() {
        super();
    }

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

    // Pas trés fonctionnel
    private int computeMiddleSegmentY(Point origin, Point dest) {
        return (int)floor(origin.getY() + (origin.getY()-dest.getY()) * origin.getX()/(1 +abs(  dest.getX()-origin.getX())));
    }

    // Pas trés fonctionnel
    private int computeMiddleSegmentX(Point origin, Point dest) {
        return (int) floor(origin.getX() + (origin.getX()-dest.getX())*origin.getY()/(1 +abs(  dest.getY()-origin.getY())));
    }


    private void checkSegment(Segment segment) {
        Point orig = segment.getOrigin();
        Point dest = segment.getDest();
        if (dest.getX() < 0){
            int midY = computeMiddleSegmentY(orig,dest) ;
            addSegment(new Segment(orig, new Point(0, midY)));
            addSegment(new Segment(new Point(getWidth(), midY), new Point(MathTools.modulo((int)dest.getX(),getWidth()), (int)dest.getY())));
        }
        else if (dest.getX() > getWidth()){
            int midY = computeMiddleSegmentY(orig,dest) ;
            addSegment(new Segment(orig, new Point(getHeight(), midY)));
            addSegment(new Segment(new Point(0, midY), new Point(MathTools.modulo((int)dest.getX(),getWidth()), (int)dest.getY())));
        }
        else if (dest.getY() < 0){
            int midX = computeMiddleSegmentX(orig,dest);
            addSegment(new Segment(orig, new Point(midX, 0)));
            addSegment(new Segment(new Point(midX, getHeight()), new Point((int)dest.getX(), MathTools.modulo((int)dest.getY() , getHeight()))));
        }
        else if (dest.getY() > getHeight()){
            int midX = computeMiddleSegmentX(orig,dest);
            addSegment(new Segment(orig, new Point(midX, getWidth())));
            addSegment(new Segment(new Point(midX, 0), new Point((int)dest.getX(), MathTools.modulo((int)dest.getY() , getHeight()))));
        }
        else{
            super.addSegment(segment);
        }
    }

    public void drawLimit(){
        Point point1, point2, point3, point4;
        point1 = new Point(0,0);
        point2 = new Point(0,getHeight());
        point3 = new Point(getWidth(),0);
        point4 = new Point(getWidth(),getHeight());

        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(point1,point2));
        segments.add(new Segment(point1,point3));
        segments.add(new Segment(point3,point4));
        segments.add(new Segment(point2,point4));
        segments = segments.stream().map(segment -> {segment.setColor(Color.RED);return segment;}).collect(Collectors.toList());
        this.addSegments(segments);
    }



    @Override
    public void addSegment(Segment segment) {
        this.checkSegment(segment);
    }
}
