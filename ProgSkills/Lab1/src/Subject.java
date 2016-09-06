import java.util.ArrayList;

/**
 *
 * @author Robert Bond III <rjb8682@rit.edu>
 */
public abstract class Subject<T> {
    // List of observers getting notifications from me
    private ArrayList<Observer<T>> observerList = new ArrayList<>();
    // The state that this subject holds. Must be of type T.
    private T state;
    // Boolean flag to prevent extra notifications from being sent out.
    private boolean changed = false;

    // Getter for the current state of the Subject
    public T getCurrentState() {
        return state;
    }

    // Changes the state to what is given. Appropriately sends out the notifications to
    // it's observers that it's state changed.
    public void changeState(T incomingState) {
        state = incomingState;
        if (!changed) {
            changed = true;
            notifyObservers();
        }
    }

    // Adds the given observer to the list of currently observing observers
    //      Observer<T> o: The observer to add to the observerList
    public void startObserving (Observer<T> o) {
        observerList.add(o);
    }

    // Removes the given observer from the list of currently observing observers
    //      Observer<T> o: The observer to remove from the observerList
    public void stopObserving (Observer<T> o) {
        observerList.remove(o);
    }

    // Iterates over the list of observers and notifies each one. I do not pass the state
    // here because it is left up to the observer to get the new current state from the
    // Subject.
    private void notifyObservers() {
        for (Observer<T> o : observerList) {
            o.notifyObserver();
        }
        changed = false;
    }
}
