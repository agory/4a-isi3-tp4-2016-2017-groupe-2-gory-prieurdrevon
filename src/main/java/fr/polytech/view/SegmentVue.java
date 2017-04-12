package fr.polytech.view;

import fr.polytech.model.Segment;

import java.awt.*;

/**
 * Created by p1511160 on 12/04/2017.
 */

public class SegmentVue {
    public Segment segment;

    public SegmentVue(Segment segment) {
        this.segment = segment;
    }

    public void drawSegment(Graphics graph) {
        if (graph == null)
            return;

        graph.setColor(this.segment.getColor());
        graph.drawLine(this.segment.getPtStart().x,
                this.segment.getPtStart().y,
                this.segment.getPtEnd().x,
                this.segment.getPtEnd().y);
    }
}
