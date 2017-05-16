package fr.polytech.model.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Created by gorya on 16/05/2017.
 */
public class FPSManager extends Observable implements Runnable, ActionListener {
    private int FPS;

    public FPSManager(int FPS) {
        this.FPS = FPS;
    }

    @Override
    public void run() {
        Timer timer = new Timer(1000/FPS,this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setChanged();
        this.notifyObservers();
    }
}
