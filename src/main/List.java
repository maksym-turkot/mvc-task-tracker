/**
 * This class constructs generic linked lists.
 *
 * @author Maksym Turkot
 * @version 04/25/2021
 */
public class List<E> {
    private Node<E> head;

    /**
     * Constructor for objects of class List
     */
    public List() {
        this.head = null;
    }

    /**
     * Constructor for objects of class List
     * with a specified head element.
     * 
     * @param head - first element of the list
     */
    public List(Node<E> head) {
        this.head = head;
    }

    // Query Operations

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size(this.head);
    }

    /**
     * Recursive method that returns number of elments in this list.
     *
     * @param curr points to the current node in this list
     * @return the number of elements in this list
     */
    private int size(Node<E> curr) {
        // Check if we have reached the end of a list
        if (curr != null) {
            return 1 + size(curr.getNext());
        } else {
            return 0;
        }
    }

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     *
     * @param n element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     */
    public boolean contains(E n) {
        // Special case for an empty array
        if (this.isEmpty()) {
            return false;
        }        
        return contains(n, this.head);
    }

    /**
     * Recursive method that returns <tt>true</tt> if this list contains the 
     * specified element.
     *
     * @param n element whose presence in this list is to be tested
     * @param curr points to the current node in this list
     * @return <tt>true</tt> if this list contains the specified element
     */
    public boolean contains(E n, Node<E> curr) {
        // Check if we have reached the end of a list
        if (curr != null) {
            // Check if current node's vaue is the one we are looking for
            if (curr.getVal().equals(n)) {
                return true;
            }
            return contains(n, curr.getNext());
        }
        return false;
    }

    /**
     * Returns current list in a form of a CSV string.
     * 
     * @param curr points to the current node in this list
     * @return the CSV string of a list
     */
    public String toString() {
        // Special case for an empty list
        if (this.isEmpty()) {
            return "0";
        }
        
        return toString(this.head);
    }

    /**
     * Recursive method that returns current list in a form of a CSV string.
     * 
     * @param curr points to the current node in this list
     * @return the CSV string of a list
     */
    public String toString(Node<E> curr) {
        // Check if we have reached the end of a list
        if (curr != null) {
            return String.valueOf(curr.getVal()) + "," + toString(curr.getNext());
        } else {
            return "";
        }        
    }

    // Modification Operations

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    public boolean add(E e) {
        // Special case for an empty list
        if(this.isEmpty()) {
            this.head = new Node<E>(e);
            return true;
        }
        return add(e, this.head);
    }

    /**
     * Recurcive method that appends the specified element to the end of this list (optional
     * operation).
     *
     * @param e element to be appended to this list
     * @param curr points to the current node in this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    public boolean add(E e, Node<E> curr) {
        // Check if we have reached the end of a list
        if (curr.getNext() != null) {
            return add(e, curr.getNext());
        } else {
            curr.setNext(new Node<E>(e));
            return true;
        }
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     */
    public boolean remove(E o) {
        // Special case for an empty and one-element list
        if (this.isEmpty()) {
            return false;
        } else if(this.size() == 1) {
            head = null;
            return true;
        }
        return remove(o, this.head, this.head.getNext());
    }

    /**
     * Recursive method that removes the first occurrence of the specified element 
     * from this list, if it is present (optional operation).  If this list does 
     * not contain the element, it is unchanged.  More formally, removes the element 
     * with the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @param curr points to the current node in this list
     * @param toRemove points to the node after the current one
     * @return <tt>true</tt> if this list contained the specified element
     */
    public boolean remove(E o, Node<E> curr, Node<E> toRemove) {
        // Special case for the beginning of the array
        if(curr.getVal().equals(o)) {
            this.head = curr.getNext();
            curr.setNext(null);
            return true;
        }

        // Check if we have reached the end of a list
        if (curr.getNext() != null) {
            // Check if cnext node's vaue is the one we are looking for
            if (toRemove.getVal().equals(o)) {
                curr.setNext(toRemove.getNext()); // Relink previous node
                toRemove.setNext(null); // Unlink unwanted element
                return true;
            }
            return remove(o, curr.getNext(), toRemove.getNext());
        }
        return false;
    }

    // Bulk Modification Operations

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *         is not supported by this list
     */
    public void clear() {
        try {
            this.head = null;
        } 
        catch (UnsupportedOperationException unsOpEx) {
            System.out.println("Exception in clear: " + unsOpEx);
        }
    }


    // Positional Access Operations

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public E get(int index) {
        try {
            // Throws an exception if index is less than 0 or larger than size of list
            if ((index < 0) || (index >= this.size())) {
                IndexOutOfBoundsException indOutOfBoundEx = new IndexOutOfBoundsException();
                throw indOutOfBoundEx;
            }

            return get(index, 0, this.head);
        }
        catch (IndexOutOfBoundsException indOutOfBoundEx) {
            System.out.println("Exception in get: " + indOutOfBoundEx);
            return null;
        }
    }

    /**
     * Recursive method that returns the element at the specified position 
     * in this list.
     *
     * @param index index of the element to return
     * @param cnt counts elements in the list
     * @param curr points to the current node in this list
     * @return the element at the specified position in this list
     */
    public E get(int index, int cnt, Node<E> curr) {
        // Check if we have reached the end of a list
        if (cnt < this.size()) {
            // Check if current node's vaue is the one we are looking for
            if (cnt == index) {
                return curr.getVal();
            }
            return get(index, ++cnt, curr.getNext());
        }
        return null;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public E set(int index, E element) {
        try {
            // Throws an exception if index is less than 0 or larger than size of list
            if ((index < 0) || (index >= this.size())) {
                IndexOutOfBoundsException indOutOfBoundEx = new IndexOutOfBoundsException();
                throw indOutOfBoundEx;
            }

            return set(index, element, 0, this.head, this.head.getVal());
        }
        catch (IndexOutOfBoundsException indOutOfBoundEx) {
            System.out.println("Exception in set: " + indOutOfBoundEx);
            return null;
        }
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @param cnt counts elements in the list
     * @param curr points to the current node in this list
     * @param val stores the value of the current element
     * @return the element previously at the specified position
     */
    public E set(int index, E element, int cnt, Node<E> curr, E val) {
        // Check if we have reached the end of a list
        if (cnt < this.size()) {
            // Check if current node's vaue is the one we are looking for
            if (cnt == index) {
                curr.setVal(element);
                return val;
            }

            return set(index, element, ++cnt, curr.getNext(), curr.getNext().getVal());
        }
        return null;
    }

    /**
     * Inserts the specified element at the specified position in this list.  
     * Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    public void add(int index, E element) {
        try {
            // Throws an exception if index is less than 0 or larger than size of list
            if ((index < 0) || (index >= this.size())) {
                IndexOutOfBoundsException indOutOfBoundEx = new IndexOutOfBoundsException();
                throw indOutOfBoundEx;
            }

            add(index, element, 0, this.head, this.head.getNext());
        }
        catch (IndexOutOfBoundsException indOutOfBoundEx) {
            System.out.println("Exception in add: " + indOutOfBoundEx);
        }
    }

    /**
     * Recursive method that inserts the specified element at the specified position in this list.  
     * Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @param cnt counts elements in the list
     * @param curr points to the current node in this list
     * @param currNext points to the node after the current one in this list
     */
    public void add(int index, E element, int cnt, Node<E> curr, Node<E> currNext) {
        // Special case for the first element
        if(index == 0) {
            Node<E> newNode = new Node<E>(element); // Create a new node
            newNode.setNext(this.head); // Link new node to current head
            this.head = newNode; // Reference new head
            return;
        }

        // Check if we have reached the end of a list
        if (currNext != null) {
            // Check if next node's vaue is the one we are looking for
            if(cnt == index - 1) {
                curr.setNext(new Node<E>(element));
                curr.getNext().setNext(currNext);
            }
            add(index, element, ++cnt, curr.getNext(), currNext.getNext());
        }
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public E remove(int index) {
        try {
            // Throws an exception if index is less than 0 or larger than size of list
            if ((index < 0) || (index >= this.size())) {
                IndexOutOfBoundsException indOutOfBoundEx = new IndexOutOfBoundsException();
                throw indOutOfBoundEx;
            }

            return remove(index, 0, this.head, this.head.getNext());
        }
        catch (IndexOutOfBoundsException indOutOfBoundEx) {
            System.out.println("Exception in remove: " + indOutOfBoundEx);
            return null;
        }
    }

    /**
     * Recursive method that removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @param cnt counts elements in the list
     * @param curr points to the current node in this list
     * toRemove points to the node after the current one
     * @return the element previously at the specified position
     */
    public E remove(int index, int cnt, Node<E> curr, Node<E> currNext) {
        // Special case for the first element
        if(index == 0) {
            this.head = curr.getNext(); // Relink new head
            curr.setNext(null); // Unlink unwanted element
            return curr.getVal();
        }

        // Check if we have reached the end of a list
        if (curr != null) {
            // Check if current node's vaue is the one we are looking for
            if (cnt == index - 1) {
                curr.setNext(currNext.getNext());
                currNext.setNext(null);
                return currNext.getVal();
            }
            return remove(index, ++cnt, curr.getNext(), currNext.getNext());
        }
        return null;
    }

    // Search Operations
    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param n element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int indexOf(E n) {
        return indexOf(n, 0, this.head);
    }

    /**
     * Recursive method that returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param n element to search for
     * @param cnt counts elements in the list
     * @param curr points to the current node in this list
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int indexOf(E n, int cnt, Node<E> curr) {
        // Check if we have reached the end of a list
        if (curr != null) {
            // Check if current node's vaue is the one we are looking for
            if (curr.getVal().equals(n)) {
                return cnt;
            }

            return indexOf(n, ++cnt, curr.getNext());
        }

        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param n element to search for
     * @return the index of the last occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int lastIndexOf(E n) {
        return lastIndexOf(n, -1, 0, this.head);
    }
    
    /**
     * Recursive method that returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param n element to search for
     * @param cnt counts elements in the list
     * @param curr points to the current node in this list
     * @return the index of the last occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int lastIndexOf(E n, int found, int cnt, Node<E> curr) {
        // Check if we have reached the end of a list
        if (curr != null) {
            // Check if current node's vaue is the one we are looking for
            if (curr.getVal().equals(n)) {
                return lastIndexOf(n, cnt, ++cnt, curr.getNext());
            }

            return lastIndexOf(n, found, ++cnt, curr.getNext());
        } else {
            return found;
        }
    }
    
    // View
    
    /**
     * Returns a copy of the portion of this list between the specified
     * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.  (If
     * <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list is
     * empty.)  The returned list may or may not reflect any future changes
     * in the original list and should only be relied upon as a temporary 
     * snapshot.
     *
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return a copy of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *         (<tt>fromIndex &lt; 0 || toIndex &gt; size ||
     *         fromIndex &gt; toIndex</tt>)
     */
    public List<E> subList(int fromIndex, int toIndex) {
        List<E> sub = new List<E>();

        try {
            // Throws an exception if index is less than 0 or larger than size of list
            if ((fromIndex < 0) || (fromIndex >= this.size()) || (toIndex < fromIndex) || (fromIndex > toIndex)) {
                IndexOutOfBoundsException indOutOfBoundEx = new IndexOutOfBoundsException();
                throw indOutOfBoundEx;
            }

            return subList(fromIndex, toIndex, 0, sub, this.head);
        }
        catch (IndexOutOfBoundsException indOutOfBoundEx) {
            System.out.println("Exception in subList: " + indOutOfBoundEx);
            return sub;
        }
    }

    /**
     * Recursive method that returns a copy of the portion of this list between the specified
     * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.  (If
     * <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list is
     * empty.)  The returned list may or may not reflect any future changes
     * in the original list and should only be relied upon as a temporary 
     * snapshot.
     *
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @param curr points to the current node in this list
     * @return a copy of the specified range within this list
     */
    public List<E> subList(int fromIndex, int toIndex, int cnt, List<E> sub, Node<E> curr) {
        // Check if current element is within wanted bounds
        if (cnt >= fromIndex && cnt < toIndex) {
            sub.add(curr.getVal());
        }

        // Check if we have reached the end of a list
        if (curr != null) {
            return subList(fromIndex, toIndex, ++cnt, sub, curr.getNext());
        }

        return sub;
    }
}
