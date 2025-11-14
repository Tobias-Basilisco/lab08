package it.unibo.deathnote.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote{
    private final Map<String, DeathInfo> notes = new LinkedHashMap<>();
    private String lastNameWritten = null;
    private static final long CAUSE_MILIS_MARGIN = 40; 
    private static final long DETAILS_MILIS_MARGIN = 6_040;
    static public final String DEFAULT_DEATH_CAUSE = "Heart Attack";


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
        lastNameWritten = name;  
    }

    /**
     * {@inheritDoc}
     */
    public boolean writeDeathCause(String cause){
        if (notes.isEmpty()){
            throw new IllegalStateException("No name written on the DeathNote yet");
        }
        if (cause == null){
            throw new IllegalStateException("Null cause parsed");
        }
        String name = getlastNameWritten();

        return notes.get(name).setCause(cause, System.currentTimeMillis());
    }

    /**
     * {@inheritDoc}
     */
    public boolean writeDetails(String details){
        if (notes.isEmpty()){
            throw new IllegalStateException("No name written on the DeathNote yet");
        }
        if (details == null){
            throw new IllegalStateException("Null details parsed");
        }
        String name = getlastNameWritten();
        
        return notes.get(name).setDetails(details, System.currentTimeMillis());
    }

    /**
     * {@inheritDoc}
     */
    public String getDeathCause(String name){
        if (!isNameWritten(name)){
            throw new IllegalArgumentException("The name parsed is not written on the DeathNote: " + name);
        }
        DeathInfo info = notes.get(name);

        return ( (info != null && info.getCause() != null)
                ? info.getCause()
                : DeathNoteImpl.DEFAULT_DEATH_CAUSE
                );
    }

    /**
     * {@inheritDoc}
     */
    public String getDeathDetails(String name){
        if (!isNameWritten(name)){
            throw new IllegalArgumentException("The name parsed is not written on the DeathNote: " + name);
        }
        DeathInfo info = notes.get(name);

        return info.getDetails();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNameWritten(String name){
        if (notes.isEmpty()){
            return false;
        }
        for (final String n : notes.keySet()){
            if (n.equals(name)){
                return true;
            }
        }
        return false;
    }

    
    private String getlastNameWritten() {
        if (notes.isEmpty()){
            throw new IllegalStateException("No name written on the DeathNote yet");
        }
        return Objects.requireNonNull(lastNameWritten);
    }

    private class DeathInfo {

        private String cause;
        private String details;
        private long nameTime;
        private long causeTime;

        public DeathInfo(){
            this.nameTime = System.currentTimeMillis();
        }

        public String getCause() {
            return cause;
        }

        public String getDetails() {
            return details;
        }

        public long getNameTime() {
            return nameTime;
        }

        public long getCauseTime() {
            return causeTime;
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
            if ( CAUSE_MILIS_MARGIN >= (time - getNameTime())){
                return true;
            } else {
                return false;
            }
        }

        private boolean canSetDetails(final long time){
            if ( DETAILS_MILIS_MARGIN >= (time - getCauseTime())){
                return true;
            } else {
                return false;
            }
        }
    }
}
