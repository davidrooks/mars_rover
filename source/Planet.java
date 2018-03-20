package hsbc;

import java.util.*;
import java.awt.Point;

public class Planet {
    int MAX_X;
    int MAX_Y;
    Map<Point, Boolean> scents;

    public Planet(int x, int y) {
        MAX_X = x;
        MAX_Y = y;
        scents = new HashMap<Point, Boolean>();
    }

    public int getMaxX() {
        return this.MAX_X;
    }

    public int getMaxY() {
        return this.MAX_Y;
    }

    public void addScent(int x, int y){
        Point pos = new Point(x,y);
        scents.put(pos, true);
    }

    public Boolean isScented(int x, int y){
        return scents.containsKey(new Point(x,y));
    }

    public int totalScents() {
        return scents.size();
    }

    // public static void main(String[] args){
    //     Planet mars = new Planet(10,10);
    //     mars.addScent(5,0);
    //     System.out.println("position 5,0 is scented = " + mars.isScented(5,0));
    //     System.out.println("position 6,0 is scented = " + mars.isScented(6,0));
    // }
}
