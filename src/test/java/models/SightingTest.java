package models;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public DataBaseRule database = new DataBaseRule();//checkout database rule uses

    @Test
    public void sighting_instantiatesCorrectly_true() {
        sighting testSighting = setUpNewSighting();
        assertTrue(testSighting instanceof sighting);
    }

    @Test
    public void getLocation_returnsLocationOnClassInitialization_true() {
        sighting testSighting = setUpNewSighting();
        assertEquals("Zone A", testSighting.getLocation());
    }


    @Test
    public void save_insertObjectsToTheInstanceOfSighting_true() {
        sighting testSighting = setUpNewSighting();
        testSighting.save();
        assertTrue(sighting.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        sighting firstSighting = setUpNewSighting();
        firstSighting.save();
        sighting secondSighting = new sighting("Njenga", "Zone B", 2, "common");
        secondSighting.save();
        assertEquals(true, sighting.all().get(0).equals(firstSighting));
        assertEquals(true, sighting.all().get(1).equals(secondSighting));
    }



    @Test
    public void save_assignsIdToTheObject() {
        sighting testSighting = setUpNewSighting();
        testSighting.save();
        sighting savedSighting = sighting.all().get(0);
        assertEquals(testSighting.getId(), savedSighting.getId());

    }
    public sighting setUpNewSighting() {
        return new sighting("njenga", "zone c", 3, "common");

    }

}