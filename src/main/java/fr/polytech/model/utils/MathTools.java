package fr.polytech.model.utils;

/**
 * Created by gorya on 5/3/17.
 */
public class MathTools {

    public static int modulo(int number,int modulo) {
        return (number < 0)?modulo - ((-number) % modulo) : number  % modulo;
    }


}
