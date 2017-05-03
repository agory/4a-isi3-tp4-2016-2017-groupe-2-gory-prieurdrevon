package fr.polytech.model;

import fr.polytech.model.DrawingSheet;

/**
 * Created by gorya on 03/05/2017.
 */
public class ToroidalDrawingSheet extends DrawingSheet {

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

    private int width;
    private int height;

}
