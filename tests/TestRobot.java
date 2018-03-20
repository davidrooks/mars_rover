package hsbc;

import org.junit.*;
import static org.junit.Assert.*;
import java.awt.Point;

import java.util.*;

public class TestRobot {

    @Test
    public void testInitWhenValid() {
        Planet mars = new Planet(10,5);
        Robot rory = new Robot(mars, 5,0,'E');
        assertEquals("Planet unexpected", rory.getPlanet(), mars);
        assertEquals("Current position unexpected", rory.getXPosition(), 5);
        assertEquals("Current position unexpected", rory.getYPosition(), 0);
        assertEquals("Current direction", rory.getDirection(), 'E');
    }

    @Test
    public void testMoveWhenSingleMove() {
        Planet mars = new Planet(10,5);
        Robot rory = new Robot(mars, 5,0,'E');
        String position = rory.move("F");
        assertEquals("Current position unexpected", "6 0 E", position);
        position = rory.move("R");
        assertEquals("Current position unexpected", "6 0 S", position);
        position = rory.move("F");
        assertEquals("Current position unexpected", "6 0 S LOST", position);
        assertEquals("Number of scents unexpected", 1, mars.totalScents());
        assertTrue("Expeected position to be scented", mars.isScented(6,0));
    }

    @Test
    public void testMoveWhenMultipleMoves() {
        Planet mars = new Planet(10,5);
        Robot rory = new Robot(mars, 5,0,'E');
        String position = rory.move("FFFLFFFFFFFFF");
        assertEquals("Current position unexpected", "8 5 N LOST", position);
        assertEquals("Number of scents unexpected", 1, mars.totalScents());
        assertTrue("Expeected position to be scented", mars.isScented(8,5));
    }

    @Test
    public void testMoveWhenScented() {
        Planet mars = new Planet(10,10);
        Robot rory = new Robot(mars, 0,0,'N');
        String position = rory.move("FFFFFRFFFFFFFFFFFF");
        assertEquals("Current position unexpected", "10 5 E LOST", position);
        assertEquals("Number of scents unexpected", 1, mars.totalScents());
        assertTrue("Expeected position to be scented", mars.isScented(10,5));
        Robot walle = new Robot(mars, 5,5,'E');
        position = walle.move("FFFFFF");
        assertEquals("Current position unexpected", "10 5 E", position);
        assertEquals("Number of scents unexpected", 1, mars.totalScents());
        assertTrue("Expeected position to be scented", mars.isScented(10,5));
    }

    @Test
    public void testMoveWhenScentedAndMoveAfterHittingScent() {
        Planet mars = new Planet(10,10);
        Robot rory = new Robot(mars, 0,0,'N');
        String position = rory.move("FFFFFRFFFFFFFFFFFF");
        assertEquals("Current position unexpected", "10 5 E LOST", position);
        assertEquals("Number of scents unexpected", 1, mars.totalScents());
        assertTrue("Expeected position to be scented", mars.isScented(10,5));
        Robot walle = new Robot(mars, 5,5,'E');
        position = walle.move("FFFFFFLFFFFLFFFFF");
        assertEquals("Current position unexpected", "5 9 W", position);
        assertEquals("Number of scents unexpected", 1, mars.totalScents());
        assertTrue("Expeected position to be scented", mars.isScented(10,5));
    }

    @Test
    public void testMoveWhenScentedAndMoveAfterHittingScentAndLost() {
        Planet mars = new Planet(10,10);
        Robot rory = new Robot(mars, 0,0,'N');
        String position = rory.move("FFFFFRFFFFFFFFFFFF");
        assertEquals("Current position unexpected", "10 5 E LOST", position);
        assertEquals("Number of scents unexpected", 1, mars.totalScents());
        assertTrue("Expeected position to be scented", mars.isScented(10,5));
        Robot walle = new Robot(mars, 5,5,'E');
        position = walle.move("FFFFFFLFFFFFLFFFFFFFFFFF");
        assertEquals("Current position unexpected", "0 10 W LOST", position);
        assertEquals("Number of scents unexpected", 2, mars.totalScents());
        assertTrue("Expeected position to be scented", mars.isScented(10,5));
        assertTrue("Expeected position to be scented", mars.isScented(0,10));
    }
}
