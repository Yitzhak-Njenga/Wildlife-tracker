package models;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public DataBaseRule database = new DataBaseRule();//checkout database rule uses

    @Test
    public void sighting_instantiatesCorrectly_true(){
        sighting testSighting = setUpNewSighting();
        assertTrue(testSighting instanceof sighting);
    }

    @Test
    public void getLocation_returnsLocationOnClassInitialization_true(){
        sighting testSighting = setUpNewSighting();
        assertEquals("Zone A",testSighting.getLocation());
    }


    public sighting setUpNewSighting() {return new sighting()}


}


