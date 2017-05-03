package fr.polytech.model.element;

import fr.polytech.model.utils.ColorService;

import java.awt.*;
import java.util.Observable;
import java.util.Random;

/**
 * Created by gorya on 30/04/2017.
 */
public abstract class Element extends Observable {
    protected Color color;

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(int x,int y) {
        setOrigin(new Point(x,y));

    }

    public void setOrigin(Point origin) {
        this.origin = origin;
        this.setChanged();
    }

    protected Point origin;

    public Element() {
        ColorService colorService = ColorService.getInstance();

        this.setColor(colorService.decode((new Random()).nextInt(11)));
    }

    public void setColor(Color color) {
        this.color = color;
        this.setChanged();
    }

    public void setColor(int colorNumber) {
        ColorService colorService = ColorService.getInstance();
        this.setColor(colorService.decode(colorNumber));
    }

    public Color getColor() {
        return color;
    }

    public void couleurSuivante() {
        ColorService colorService = ColorService.getInstance();
        this.setColor(colorService.nextColor(this.getColor()));
    }


}
