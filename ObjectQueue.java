/**
 * Implements a resizable Queue.
 *
 */
public class ObjectQueue implements ObjectQueueInterface{

    private Object[] item;
    private int front;
    private int rear;
    private int count;

    /**
     * creates a new object of ObjectQueue class
     */
    public ObjectQueue() {
        item = new Object[1];
        front = 0;
        rear = -1;
        count = 0;
    }

    /**
     * checks if the queue is empty or not
     * @return true if the queue is empty; otherwise, false
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * checks if the queue is full or not
     * @return true if the queue is full; otherwise, false
     */
    public boolean isFull() {
        return count == item.length;
    }

    /**
     * clears the queue to its initial state (i.e. deletes)
     * all the elements
     */
    public void clear() {
        item = new Object[1];
        front = 0;
        rear = -1;
        count = 0;
    }

    /**
     * inserts an element to the queue
     * @param o - element of object type
     */
    public void insert(Object o) {
        if (isFull())
            resize(2 * item.length);
        rear = (rear+1) % item.length;
        item[rear] = o;
        ++count;
    }

    /**
     * removes and then returns the element at the front of
     * the queue
     * @return top element
     */
    public Object remove() {
        if (isEmpty()) {
            System.out.println("Queue Underflow");
            System.exit(1);
        }
        Object temp = item[front];
        item[front] = null;
        front = (front+1) % item.length;
        -- count;
        if (count == item.length/4 && item.length != 1)
        resize(item.length/2);
        return temp;
    }

    /**
     * returns the front element of the queue
     * @return top element without removing it
     */
    public Object query() {
        if (isEmpty()) {
            System.out.println("Queue Underflow");
            System.exit(1);
        }
        return item[front];
    }

    // private method resize, that resizes the stacks
    private void resize(int size) {
        Object[] temp = new Object[size];
        for (int i = 0; i < count; ++i) {
            temp[i] = item[front];
            front = (front+1) % item.length;
        }
        front = 0;
        rear = count-1;
        item = temp;
    }

}