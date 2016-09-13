package display;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;

import model.DrawingApp;
import model.Square;
import observing.Observer;


/**
 * The view + controller for the second part of Lab 1.
 * This is the code that will handle anything JavaFX,
 * including drawing the squares.
 *
 * @author Robert Bond III <rjb8682@rit.edu>
 */
public class ShapeGUI extends Application implements Observer<Square, String> {

    private static DrawingApp model;
    private static ArrayList<Rectangle> rectangles = new ArrayList<>();

    private static final double INITIAL_HEIGHT = 500;
    private static final double INITIAL_WIDTH = 700;
    private static final double DIM = 100;

    private Group root;
    private int selectedIndex = -1;

    public static void main( String[] args ) {
        launch( args );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle( "Lab 1 -- Drawing Area" );

        model = new DrawingApp();
        model.subscribe(this);
        root = new Group();

        Scene scene =  new Scene( root );
        scene.addEventHandler( MouseEvent.MOUSE_CLICKED, this::bkgdSelect );
        primaryStage.setScene( scene );
        primaryStage.setHeight( INITIAL_HEIGHT );
        primaryStage.setWidth( INITIAL_WIDTH );
        primaryStage.show();
    }

    public void notify(Square s, String info) {
        String[] infoTokens = info.split(":");
        switch (infoTokens[0]) {
            case "new":
                // info says "new" so create a new rectangle with the square given
                Rectangle square = new Rectangle( s.getX(), s.getY(), DIM, DIM);
                square.setFill(s.getColor());
                square.addEventHandler( MouseEvent.MOUSE_CLICKED,
                        this::chooseShape
                );
                rectangles.add(square);
                root.getChildren().add( square );
                square.toFront();
                break;

            case "move":
                // info says move so take the second token as an index into the rectangle list and move it.
                int index = Integer.parseInt(infoTokens[1]);
                Rectangle rect = rectangles.get(index);
                rect.setX(s.getX());
                rect.setY(s.getY());
                break;

            default:
                System.err.println("Incorrect key passed with info");
        }
    }

    /**
     * If this is the primary ("left") mouse button, remember what shape
     * was selected.
     * @param mEvent the JavaFX info for this event
     */
    private void chooseShape( MouseEvent mEvent ) {
        MouseButton button = mEvent.getButton();
        if ( button == MouseButton.PRIMARY ) {
            Rectangle sourceRect = (Rectangle)mEvent.getSource();
            selectedIndex = rectangles.indexOf(sourceRect);
            System.out.println("SelectedIndex: " + selectedIndex);
            mEvent.consume(); // Don't pass up to Scene.
        }
    }

    /**
     * If this is the primary ("left") mouse button, make a new square. If
     * this is the secondary ("right") mouse button, and a shape was
     * previously chosen move the square to this new location.
     * @param mEvent the JavaFX info for this event
     */
    private void bkgdSelect( MouseEvent mEvent ) {
        MouseButton button = mEvent.getButton();
        switch ( button ) {
            case PRIMARY:
                model.makeSquare(mEvent.getX(), mEvent.getY());
                break;
            case SECONDARY:
                model.moveSquare(selectedIndex, mEvent.getX(), mEvent.getY());
                break;
            default:
                System.err.println( "Other mouse button pushed." );
        }
    }
}
