package model;

import javafx.scene.paint.Color;
import observing.Observer;
import observing.Subject;

import java.util.ArrayList;

/**
 * The model for the second part of Lab 1.
 *
 * @author Robert Bond III <rjb8682@rit.edu>
 */
public class DrawingApp implements Subject<Square, String> {

    private ArrayList<Observer<Square, String>> observers = new ArrayList<>();

    private ArrayList<Square> squares = new ArrayList<>();

    private static final double DIM = 100;

    private static final Color[] colors = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.BLUE, Color.PURPLE, Color.CYAN, Color.MAGENTA,
            Color.BLACK
    };

    private final static int NUM_COLORS = colors.length;
    private int colorChoice;

    @Override
    public void subscribe(Observer<Square, String> obs) {
        observers.add(obs);
    }

    @Override
    public void unsubscribe(Observer<Square, String> obs) {
        observers.remove(obs);
    }

    public void makeSquare(double x, double y) {
        Color color = colors[colorChoice];
        colorChoice = ( colorChoice + 1 ) % NUM_COLORS;
        Square s = new Square(x, y, DIM, color);
        squares.add(s);
        notifyObservers(s, "new");
    }

    public void moveSquare(int index, double newX, double newY) {
        Square s = squares.get(index);
        s.move(newX, newY);
        notifyObservers(s, "move:"+index);
    }

    private void notifyObservers(Square s, String info) {
        for (Observer<Square, String> obs : observers) {
            obs.notify(s, info);
        }
    }
}
