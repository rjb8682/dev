import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import observing.Observer;

import java.util.Optional;

/**
 * The view + controller for the second part of Lab 1.
 *
 * @author Robert Bond III <rjb8682@rit.edu>
 */
public class ShapeGUI extends Application implements Observer {

    private static final double INITIAL_HEIGHT = 500;
    private static final double INITIAL_WIDTH = 700;

    private Group root;

    private Optional< Rectangle > chosen = Optional.empty();

    public static void main( String[] args ) {
        launch( args );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle( "Lab 1 -- Drawing Area" );

        root = new Group();

        Scene scene =  new Scene( root );
        scene.addEventHandler( MouseEvent.MOUSE_CLICKED, this::bkgdSelect );
        primaryStage.setScene( scene );
        primaryStage.setHeight( INITIAL_HEIGHT );
        primaryStage.setWidth( INITIAL_WIDTH );
        primaryStage.show();
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
            chosen = Optional.of( sourceRect );
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
//                Rectangle square = new Rectangle( mEvent.getX(), mEvent.getY(),
//                        DIM, DIM );
//                square.setFill( colors[ colorChoice ] );
//                colorChoice = ( colorChoice + 1 ) % NUM_COLORS;
//                square.addEventHandler( MouseEvent.MOUSE_CLICKED,
//                        this::chooseShape
//                );
//                root.getChildren().add( square );
//                square.toFront();
                break;
            case SECONDARY:
//                chosen.ifPresent( rect -> {
//                            rect.setX( mEvent.getX() );
//                            rect.setY( mEvent.getY() );
//                            chosen = Optional.empty();
//                        }
//                );
                break;
            default:
                System.err.println( "Other mouse button pushed." );
        }
    }
}
