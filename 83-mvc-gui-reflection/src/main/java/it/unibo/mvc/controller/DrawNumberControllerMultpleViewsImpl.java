package it.unibo.mvc.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import it.unibo.mvc.api.DrawNumber;
import it.unibo.mvc.api.DrawNumberView;

public class DrawNumberControllerMultpleViewsImpl extends DrawNumberControllerImpl{
    List<DrawNumberView> views;

    public DrawNumberControllerMultpleViewsImpl(final DrawNumber model){
        super(model);
        views = new LinkedList<>();
    }

    @Override
    public void addView(final DrawNumberView view){
        Objects.requireNonNull(view, "Cannot set a null view");
        views.add(view);
        view.setController(this);
        view.start();
    }

    @Override
    public void newAttempt(final int n) {
        if (views.isEmpty()){
            throw new NullPointerException("There is no view attached!");
        }
        for (final DrawNumberView view : views){
            view.result(super.getModel().attempt(n));
        }
    }
}
