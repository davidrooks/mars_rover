package hsbc;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

public class TestPlanet {

    @Test
    public void testInitWhenValid() {
        Planet mars = new Planet(10,5);
        assertEquals("MAX X unexpected", mars.getMaxX(), 10);
        assertEquals("MAX Y unexpected", mars.getMaxY(), 5);
        assertEquals("Number of scents unexpected", 0, mars.totalScents());
    }

    @Test
    public void testScent() {
        Planet mars = new Planet(10,10);
        mars.addScent(5,0);
        assertEquals("Number of scents unexpected", 1, mars.totalScents());
        assertTrue(mars.isScented(5,0));
        assertFalse(mars.isScented(5,5));
    }
}
