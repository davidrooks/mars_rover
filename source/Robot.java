package hsbc;

import java.util.*;
import java.awt.Point;

public class Robot {
    private Point position;
    private char direction;
    private Planet planet;
    private Boolean lost;

    public Robot(Planet planet, int x, int y, char direction) {
        this.planet = planet;
        this.position = new Point(x,y);
        this.direction = direction;
        this.lost = false;
    }

    public Planet getPlanet() {
        return this.planet;
    }

    public int getXPosition() {
        return (int) this.position.x;
    }

    public int getYPosition() {
        return (int) this.position.y;
    }

    public char getDirection() {
        return this.direction;
    }

    public String move(String moves) {
        for (int i = 0; i < moves.length(); i++){
            char c = moves.charAt(i);

            switch (c) {
                case 'F': this.lost = this.moveForward(); break;
                case 'L': this.rotateLeft(); break;
                case 'R': this.rotateRight(); break;
            }

            if (this.lost) {
                return this.outputPostionandOrientation();
            }
        }
        return this.outputPostionandOrientation();
    }

    public String outputPostionandOrientation(){
        if (this.lost) {
            return (int) this.position.x + " " + (int) this.position.y + " " + this.direction + " LOST";
        } else if ((int) this.position.x > this.planet.getMaxX() ||
            (int) this.position.x < 0 ||
            (int) this.position.y > this.planet.getMaxY() ||
            (int) this.position.y < 0) {
            return "LOST";
        } else {
            return (int) this.position.x + " " + (int) this.position.y + " " + this.direction;
        }
    }

    private void rotateLeft(){
        switch (this.direction) {
            case 'E': this.direction = 'N'; break;
            case 'S': this.direction = 'E'; break;
            case 'W': this.direction = 'S'; break;
            case 'N': this.direction = 'W'; break;
        }
    }

    private void rotateRight(){
        switch (this.direction) {
            case 'E': this.direction = 'S'; break;
            case 'S': this.direction = 'W'; break;
            case 'W': this.direction = 'N'; break;
            case 'N': this.direction = 'E'; break;
        }
    }

    private boolean moveForward(){
        Point newPosition = null;
        switch (this.direction) {
            case 'E': newPosition = new Point((int) this.position.x + 1, (int) this.position.y); break;
            case 'W': newPosition = new Point((int) this.position.x - 1, (int) this.position.y); break;
            case 'N': newPosition = new Point((int) this.position.x, (int) this.position.y + 1); break;
            case 'S': newPosition = new Point((int) this.position.x, (int) this.position.y - 1); break;
        }

        if (newPosition != null) {
            if ((int) newPosition.x > this.planet.getMaxX() ||
                (int) newPosition.x < 0 ||
                (int) newPosition.y > this.planet.getMaxY() ||
                (int) newPosition.y < 0) {
                // we have moved out of bounds and fallen off grid
                if (this.planet.isScented((int) this.position.x, (int) this.position.y)){
                    // we found a scent and saved ourselves. yay!
                    return false;
                } else {
                    // i regret nothing!!
                    this.planet.addScent((int) this.position.x, (int) this.position.y);
                    return true;
                }
            } else {
                this.position = newPosition;
                return false;
            }
        }
        return false;
    }

    // public static void main(String[] args){
    //     Planet mars = new Planet(10,10);
    //     Robot rory = new Robot(mars, 0,0,'N');
    //     String position = rory.move("FFFFFRFFFFFFFFFFFF");
    //     System.out.println(position);
    // }
}
