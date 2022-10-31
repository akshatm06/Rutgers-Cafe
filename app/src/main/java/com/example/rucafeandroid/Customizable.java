package com.example.rucafeandroid;

/**
 * Customizable Interface handles all operations to
 * add and remove items to store orders and the ordering basket.
 * @author Akshat Mehta and Atharva Patil
 */
public interface Customizable {

    /**
     * Abstract method to add an object.
     * @param obj Object.
     * @return true if added, false otherwise.
     */
    boolean add(Object obj);

    /**
     * Abstract method to remove an object.
     * @param obj Object.
     * @return true if added, false otherwise.
     */
    boolean remove(Object obj);

}
