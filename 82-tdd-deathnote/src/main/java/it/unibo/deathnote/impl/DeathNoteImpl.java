package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote{

    /**
     * {@inheritDoc}
     */
    public String getRule(int ruleNumber){
        if (ruleNumber < 1 || ruleNumber > RULES.size()){
            throw new IllegalArgumentException("Rules range from 1 to " + RULES.size());
        }
        return RULES.get( ruleNumber - 1);
    }

    /**
     * {@inheritDoc}
     */
    public void writeName(String name){        
    }

    /**
     * {@inheritDoc}
     */
    public boolean writeDeathCause(String cause){
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean writeDetails(String details){
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public String getDeathCause(String name){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getDeathDetails(String name){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNameWritten(String name){
        return false;
    }
}
