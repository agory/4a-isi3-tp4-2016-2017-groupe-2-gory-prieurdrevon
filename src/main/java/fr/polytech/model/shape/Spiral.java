package fr.polytech.model.shape;

import fr.polytech.model.Segment;
import fr.polytech.model.Tortue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gorya on 12/04/2017.
 */
public class Spiral implements Shape{

    private int n;
    private int a;
    private int k;

    public Spiral(int n, int a, int k) {
        this.n = n;
        this.a = a;
        this.k = k;
    }

    @Override
    public List<Segment> draw(Tortue tortue) {
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            tortue.couleurSuivante();
            tortue.avancer(n);
            tortue.droite(360/a);
            n = n+1;
        }
        return segments;
    }
}
