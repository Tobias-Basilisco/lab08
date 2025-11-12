package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote{
    private final Map<String, DeathInfo> notes = new HashMap<>();


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
        if (name == null){
            throw new NullPointerException("No name has been passed");
        }
        notes.put(name, new DeathInfo());  
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

    private class DeathInfo {

        private String cause;
        private String details;

        public DeathInfo(){
        }

        public DeathInfo(final String cause){
            this.cause = Objects.requireNonNull(cause);
        }

        public DeathInfo(final String cause, final String details){
            this(cause);
            this.details = Objects.requireNonNull(details);
        }

        public String getCause() {
            return cause;
        }

        public String getDetails() {
            return details;
        }

        public void setCause(String cause) {
            this.cause = Objects.requireNonNull(cause);
        }

        public void setDetails(String details) {
            this.details = Objects.requireNonNull(details);
        }
    }
}
