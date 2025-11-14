package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final int ACCEPTABLE_MESSAGE_LENGTH = 10;
    private final int NEGATIVE_VALUE = -5;
    private DeathNote deathNote;
    private String instruction = null;

    /**
     * Prepare the tests with a blank deathNote book
     */
    @BeforeEach
    public void setUp(){
        deathNote = new DeathNoteImpl();
    }

    /*
     * helper that tests IllegalArgumentException for values out of range of the instruction list
     */
    private void testInstructionsOutOfRange(final int value){
        try {
            instruction = deathNote.getRule(value);
            Assertions.fail("No Exception thrown for value: " + value);
        } catch (Exception e){
            assertEquals(null, instruction);
            assertEquals("IllegalArgumentException", e.getClass().getSimpleName());
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() >= ACCEPTABLE_MESSAGE_LENGTH);
        }
    }

    /**
     * Tests negative iindex access to instruction list
     */
    @Test
    public void testInstructionsForNegativeIndex(){
        testInstructionsOutOfRange(NEGATIVE_VALUE);
    }

    /**
     * Tests zero iindex access to instruction list
     */
    @Test
    public void testInstructionsForZeroIndex(){
        testInstructionsOutOfRange(0);
    }


}