package hsbc;

import org.junit.*;
import static org.junit.Assert.*;
import java.lang.RuntimeException;

import java.util.*;

public class TestInstructions {

    @Test(expected = RuntimeException.class)
    public void testInitWhenInvalidPlanet1() {
        Instructions i = new Instructions("A B\n");
    }

    @Test(expected = RuntimeException.class)
    public void testInitWhenInvalidPlanet2() {
        Instructions i = new Instructions("12\n");
    }
    @Test(expected = RuntimeException.class)
    public void testInitWhenInvalidStartingPosition1() {
        Instructions i = new Instructions("10 10\nA B 4");
    }

    @Test(expected = RuntimeException.class)
    public void testInitWhenInvalidStartingPosition2() {
        Instructions i = new Instructions("10 10\n55E");
    }

    @Test
    public void testInitWhenStartingPositionOutsidePlanet() {
        Instructions i = new Instructions("10 10\n11 0 E");
        assertEquals("unexpected output value", 1, i.getResult().size());
        assertEquals("unexpected output value", "LOST", i.getResult().get(0));
    }

    @Test
    public void testInitWhenValidPlanetandStartPostionButNoMoveInstructions() {
        Instructions i = new Instructions("10 10\n5 5 E\n");
        assertEquals("unexpected output value", 1, i.getResult().size());
        assertEquals("unexpected output value", "5 5 E", i.getResult().get(0));
    }

    @Test
    public void testInitWhenValidPlanetandStartPostionButInvalidMoveInstructions() {
        Instructions i = new Instructions("10 10\n5 5 E\n12GTHY6745");
        assertEquals("unexpected output value", 1, i.getResult().size());
        assertEquals("unexpected output value", "5 5 E", i.getResult().get(0));
    }

    @Test
    public void testInitWhenValidPlanetandStartPostionButSomeInvalidMoveInstructions() {
        Instructions i = new Instructions("10 10\n5 5 E\n1F2GFTHYL6745");
        assertEquals("unexpected output value", 1, i.getResult().size());
        assertEquals("unexpected output value", "7 5 N", i.getResult().get(0));
    }
}
