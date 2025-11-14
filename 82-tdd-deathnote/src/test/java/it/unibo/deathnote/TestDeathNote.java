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
    private static final String HUMAN_NAME = "Humanoid Johnson";
    private static final String HUMAN_2_NAME = "Hum Anoid";
    private DeathNote deathNote;
    private String instruction = null;

    /**
     * Prepare the tests with a blank deathNote book
     */
    @BeforeEach
    public void setUp(){
        deathNote = new DeathNoteImpl();
    }

    //  1)
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

    //  2)
    /*
    * helper that tests legal values of the instruction list
    */
    private void testInstructionsInRange(final int value){
        assertNotNull(deathNote.getRule(value));
        assertFalse(deathNote.getRule(value).isBlank());
    }

    /**
     * Tests all valid iindexes access to instruction list
     */
    @Test
    public void testInstructionsForAllValidIndexes(){
        for (int i = 1; i <= deathNote.RULES.size(); i++){
            testInstructionsInRange(i);
        }
    }

    //  3)
    /**
     * verifies that the human has not been written in the notebook yet
     * writes the human in the notebook
     * verifies that the human has been written in the notebook
     * verifies that another human has not been written in the notebook
     * verifies that the empty string has not been written in the notebook
    */
    @Test
    public void testNameWriting(){
        assertFalse(deathNote.isNameWritten(HUMAN_NAME));
        try {
            deathNote.writeName(HUMAN_NAME);
        } catch (Exception e) {
            Assertions.fail("No exception should be thrown for first name written: " + HUMAN_NAME);
        }
        assertTrue(deathNote.isNameWritten(HUMAN_NAME));
        assertFalse(deathNote.isNameWritten(HUMAN_2_NAME));
        assertFalse(deathNote.isNameWritten(""));
    }


}