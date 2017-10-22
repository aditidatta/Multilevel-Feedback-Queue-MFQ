/**
 * Interface for ObjectQueue class.
 * @author Aditi Datta
 */
public interface ObjectQueueInterface {
    boolean isEmpty();
    boolean isFull();
    void insert(Object o);
    Object remove();
    Object query();
}
