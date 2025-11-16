package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberStandardOutputView implements DrawNumberView{
    
    /**
     *{@inheritDoc}
     */
    public void setController(DrawNumberController observer){
        /* Output-only view */
    }

    /**
     * {@inheritDoc}
     */
    public void start(){
        /* No need to set visible standard output */
    }

    /**
     * {@inheritDoc}
     */
    public void result(DrawResult res){
        System.out.println(res.getDescription());
    }
}
