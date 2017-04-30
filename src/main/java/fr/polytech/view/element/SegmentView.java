package fr.polytech.view.element;

import fr.polytech.model.element.Segment;

import java.awt.*;

/**
 * Created by p1511160 on 12/04/2017.
 */

public class SegmentView implements ElementView {
    public Segment segment;

    public SegmentView(Segment segment) {
        this.segment = segment;
    }

    public void draw(Graphics graph) {
        if (graph == null)
            return;

        graph.setColor(this.segment.getColor());
        graph.drawLine(this.segment.getOrigin().x,
                this.segment.getOrigin().y,
                this.segment.getDest().x,
                this.segment.getDest().y);
    }
}
