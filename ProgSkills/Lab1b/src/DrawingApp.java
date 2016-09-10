import javafx.scene.paint.Color;
import observing.AbstractSubject;

/**
 * The model for the second part of Lab 1.
 *
 * @author Robert Bond III <rjb8682@rit.edu>
 */
public class DrawingApp extends AbstractSubject {

    private static final double DIM = 100;

    private static final Color[] colors = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.BLUE, Color.PURPLE, Color.CYAN, Color.MAGENTA,
            Color.BLACK
    };
    private final static int NUM_COLORS = colors.length;
    private int colorChoice = 0;

}
