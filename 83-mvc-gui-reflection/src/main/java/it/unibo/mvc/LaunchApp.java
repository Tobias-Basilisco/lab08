package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.controller.DrawNumberControllerMultpleViewsImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberStandardOutputView;
import it.unibo.mvc.view.DrawNumberSwingView;

/**
 * Application entry-point.
 */
public final class LaunchApp {
    private static final String VIEWS_PACKAGE = "it.unibo.mvc.view";
    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) {
        final var model = new DrawNumberImpl();
        // final DrawNumberController app = new DrawNumberControllerImpl(model);
        // app.addView(new DrawNumberSwingView());


        // final DrawNumberController app = new DrawNumberControllerMultpleViewsImpl(model);
        // app.addView(new DrawNumberSwingView());
        // app.addView(new DrawNumberSwingView());
        // app.addView(new DrawNumberStandardOutputView());


        final DrawNumberController app = new DrawNumberControllerMultpleViewsImpl(model);
        final List <Class<? extends DrawNumberView>> viewClasses = new ArrayList<>();
        final List <String> viewClassNames = List.of("DrawNumberSwingView", "DrawNumberStandardOutputView");

        for ( final String vcName : viewClassNames){
            try {
                viewClasses.add(Class.forName(VIEWS_PACKAGE + "." + vcName).asSubclass(DrawNumberView.class));
            } catch (ClassNotFoundException e){
                System.out.println(e.getMessage() + e.getCause());
            }
        }

        for ( final Class<? extends DrawNumberView> viewClass : viewClasses){
            for (int i = 1; i <= 3; i++){
                try {
                    DrawNumberView view = viewClass.getConstructor().newInstance();
                    app.addView(view);
                } catch (Exception e){
                    System.out.println(e.getMessage() + e.getCause());
                }
            }
        }
    }
}
