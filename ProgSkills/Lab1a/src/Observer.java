/**
 * @author Robert Bond III <rjb8682@rit.edu>
 */
public abstract class Observer<T> {
    // The subject that I am looking for notifications for
    protected Subject<T> subject;
    // The method that is called when the subject notifies me of a change
    public abstract void notifyObserver();
}
