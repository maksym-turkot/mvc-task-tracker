/**
 * This class constructs node in the linked list List.
 *
 * @author Maksym Turkot
 * @version 04/18/2021
 */
public class Node<E> {
    private E val;
    private Node<E> next;
    
    /**
     * Constructor for objects of class Node
     */
    public Node() {
        this.val = null;
        this.next = null;
    }
    
    /**
     * Constructor for objects of class Node with given value
     */
    public Node(E val) {
        this.val = val;
        next = null;
    }
    
    /**
     * Constructor for objects of class Node with given value
     * and next node
     */
    public Node(E val, Node<E> next) {
        this.val = val;
        this.next = next;
    }
    
    /**
     * Returns the node's value
     */
    public E getVal() {     
        return this.val;
    }
    
    /**
     * Returns the next node
     */
    public Node<E> getNext() {
        return this.next;
    }
    
    /**
     * Sets the node's value
     */
    public void setVal(E val) {
        this.val = val;
    }
    
    /**
     * Sets the next node
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }
}