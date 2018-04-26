package q2;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Presents a user interface for the user to create a five point star using
 * MouseEvents that allow the user to click and drag.
 *
 * @author Justin Quan, Set B
 * @version 1.0
 */
public class DrawStar extends Application {

    /** The contents of the application scene. */
    private Group root;
    /** Center point of the five point star. */
    private Point2D center;
    /** Circle to move to first mouse click location. */
    private final Circle atCenter = new Circle(0, 0, 3);
    /** First point at end of the mouse drag location. */
    private Point2D p1;
    /** Angle between each point of the star. */
    private final double twoPIFifth = (2 * Math.PI / 5);

    /**
     * Displays an initially empty scene, waiting for the user to draw lines
     * with the mouse.
     * 
     * @param primaryStage
     *            a Stage
     */
    public void start(Stage primaryStage) {
        root = new Group(atCenter);
        atCenter.setFill(Color.CYAN);

        final int appWidth = 900;
        final int appHeight = 700;
        Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);

        scene.setOnMousePressed(this::mousePress);
        scene.setOnMouseDragged(this::mouseDrag);

        primaryStage.setTitle("Star");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * Creates a circle and sets the center point of the five point star
     * when the mouse is pressed.
     * 
     * @param event
     *            invoked this method
     */
    public void mousePress(MouseEvent event) {
        atCenter.setCenterX(event.getX());
        atCenter.setCenterY(event.getY());
        center = new Point2D(event.getX(), event.getY());
    }
    /**
     * Updates the first point of the start to the location of the mouse drag
     * end location.  Uses the first point to establish the location of the
     * other four points.
     * 
     * @param event
     *            invoked this method
     */
    public void mouseDrag(MouseEvent event) {
        p1 = new Point2D(event.getX(), event.getY());
        
        double radius = Math.sqrt(Math.pow(p1.getX() - center.getX(), 2) 
            + Math.pow(p1.getY() - center.getY(), 2));
        
        double a1 = 
            Math.atan2(p1.getY() - center.getY(), p1.getX() - center.getX());
        double a2 = a1 + twoPIFifth;
        double a3 = a2 + twoPIFifth;
        double a4 = a3 + twoPIFifth;
        double a5 = a4 + twoPIFifth;

        Point2D p2 = new Point2D(center.getX() + (radius * Math.cos(a2)), 
            center.getY() + (radius * Math.sin(a2)));

        Point2D p3 = new Point2D(center.getX() + (radius * Math.cos(a3)), 
            center.getY() + (radius * Math.sin(a3)));

        Point2D p4 = new Point2D(center.getX() + (radius * Math.cos(a4)), 
            center.getY() + (radius * Math.sin(a4)));

        Point2D p5 = new Point2D(center.getX() + (radius * Math.cos(a5)), 
            center.getY() + (radius * Math.sin(a5)));

        Line l1 = new Line(p1.getX(), p1.getY(), p3.getX(), p3.getY());
        Line l2 = new Line(p3.getX(), p3.getY(), p5.getX(), p5.getY());
        Line l3 = new Line(p5.getX(), p5.getY(), p2.getX(), p2.getY());
        Line l4 = new Line(p2.getX(), p2.getY(), p4.getX(), p4.getY());
        Line l5 = new Line(p4.getX(), p4.getY(), p1.getX(), p1.getY());
        
        l1.setStroke(Color.CYAN);
        l2.setStroke(Color.CYAN);
        l3.setStroke(Color.CYAN);
        l4.setStroke(Color.CYAN);
        l5.setStroke(Color.CYAN);
        
        root.getChildren().clear();
        root.getChildren().add(atCenter);
        root.getChildren().addAll(l1, l2, l3, l4, l5);
    }

    /**
     * Launches the JavaFX application.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}