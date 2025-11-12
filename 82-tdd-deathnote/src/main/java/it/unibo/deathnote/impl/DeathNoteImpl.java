package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote{
    private final Map<String, DeathInfo> notes = new HashMap<>();
    private static final long CauseMilisecondsMargin = 40; 
    private static final long DetailsMilisecondsMargin = 6_040; 


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
        private long nameTime;
        private long causeTime;

        public DeathInfo(){
            this.nameTime = System.currentTimeMillis();
        }

        // public DeathInfo(final String cause){
        //     this();
        //     this.cause = Objects.requireNonNull(cause);
        // }

        // public DeathInfo(final String cause, final String details){
        //     this(cause);
        //     this.causeTime = System.currentTimeMillis();
        //     this.details = Objects.requireNonNull(details);
        // }

        public String getCause() {
            return cause;
        }

        public String getDetails() {
            return details;
        }

        public long getTimeWritten() {
            return nameTime;
        }

        public boolean setCause(String cause, long time) {
            if (canSetCause(time)){
                this.cause = Objects.requireNonNull(cause);
                this.causeTime = System.currentTimeMillis();
                return true;
            } else {
                return false;
            }
        }

        public boolean setDetails(String details, long time) {
            if (canSetDetails(time)){
                this.details = Objects.requireNonNull(details);
                return true;
            } else {
                return false;
            }
        }

        private boolean canSetCause(final long time){
            if ( CauseMilisecondsMargin >= (time - nameTime)){
                return true;
            } else {
                return false;
            }
        }

        private boolean canSetDetails(final long time){
            if ( DetailsMilisecondsMargin >= (time - causeTime)){
                return true;
            } else {
                return false;
            }
        }
    }
}
