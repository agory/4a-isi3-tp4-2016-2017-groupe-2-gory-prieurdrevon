package fr.polytech.model.utils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gorya on 30/04/2017.
 */
public class ColorService {
    private static ColorService instance;

    public Map<Integer, Color> colors;

    public static ColorService getInstance(){
        if(instance == null)
            instance = new ColorService();
        return instance;
    }

    private ColorService() {
        this.colors = new HashMap<>();
        colors.put(0, Color.black);
        colors.put(1, Color.blue);
        colors.put(2, Color.cyan);
        colors.put(3, Color.darkGray);
        colors.put(4, Color.red);
        colors.put(5, Color.green);
        colors.put(6, Color.lightGray);
        colors.put(7, Color.magenta);
        colors.put(8, Color.lightGray);
        colors.put(9, Color.magenta);
        colors.put(9, Color.orange);
        colors.put(10, Color.gray);
        colors.put(11, Color.pink);
    }

    public Color decode(int c) {
        if (colors.containsKey(c))
            return colors.get(c);
        return colors.get(0);
    }

    public Color nextColor(Color color){
        for (Map.Entry<Integer,Color> entry:colors.entrySet()) {
            if(entry.getValue().equals(color))
                return colors.get((entry.getKey() + 1) % colors.size());
        }
        return colors.get(0);
    }
}
