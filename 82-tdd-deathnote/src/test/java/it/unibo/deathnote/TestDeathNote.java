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

    private final int ACCEPTABLE_MESSAGE_LENGTH = 10;
    private final int NEGATIVE_VALUE = -5;
    private final long SLEEP_MILIS = 100;
    private final String HUMAN_NAME = "Humanoid Johnson";
    private final String HUMAN_2_NAME = "Hum Anoid";
    private final String CAUSE_OF_DEATH = "karting accident";
    private final String GET_RULE_EXCEPTION = "IllegalArgumentException";
    private final String WRITE_DEATH_CAUSE_EXCEPTION = "IllegalStateException";
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
            assertEquals(GET_RULE_EXCEPTION, e.getClass().getSimpleName());
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
        for (int i = 1; i <= DeathNote.RULES.size(); i++){
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

    /**
     * check that writing a cause of death before writing a name throws the correct exception
     * write the name of a human in the notebook
     * verify that the cause of death is a heart attack
     * write the name of another human in the notebook
     * set the cause of death to "karting accident"
     * verify that the cause of death has been set correctly (returned true, and the cause is indeed "karting accident")
     * sleep for 100ms
     * try to change the cause of death 
     * verify that the cause of death has not been changed
    */
    @Test
    public void testCauseOfDeath(){
        try {
            deathNote.writeDeathCause(CAUSE_OF_DEATH);
            Assertions.fail("Writing cause of death on an empty deathNote should throw an exception");
        } catch (Exception e){
            assertEquals(null, instruction);
            assertEquals(WRITE_DEATH_CAUSE_EXCEPTION, e.getClass().getSimpleName());
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() >= ACCEPTABLE_MESSAGE_LENGTH);
            deathNote.writeName(HUMAN_NAME);
            assertEquals(DeathNoteImpl.DEFAULT_DEATH_CAUSE ,deathNote.getDeathCause(HUMAN_NAME));
            deathNote.writeName(HUMAN_2_NAME);
            assertTrue(deathNote.writeDeathCause(CAUSE_OF_DEATH));
            assertEquals(CAUSE_OF_DEATH, deathNote.getDeathCause(HUMAN_2_NAME));
            try {
                Thread.sleep(SLEEP_MILIS);
                assertFalse(deathNote.writeDeathCause(DeathNoteImpl.DEFAULT_DEATH_CAUSE));
                assertEquals(CAUSE_OF_DEATH, deathNote.getDeathCause(HUMAN_2_NAME));
            } catch (InterruptedException ie){
                System.out.println(ie.getMessage());
            }  
        }
    }
}