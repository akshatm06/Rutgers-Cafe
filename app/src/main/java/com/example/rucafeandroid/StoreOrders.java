package com.example.rucafeandroid;
import java.util.ArrayList;
/**
 * StoreOrders class contains all the orders placed by RU Cafe, where each order contains different menu items.
 * @author Akshat Mehta and Atharva Patil
 */
public class StoreOrders implements Customizable {

    private ArrayList <Order> storeOrdersList = new ArrayList<>();

    /**
     * Add an order to the store orders list.
     * @param obj order object.
     * @return true if added, false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        boolean success = storeOrdersList.add((Order) obj);
        return success;
    }

    /**
     * Remove an order from the store orders list.
     * @param obj order object.
     * @return true if removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        boolean success = storeOrdersList.remove((Order) obj);
        return success;
    }

    /**
     * Get the store orders list.
     * @return ArrayList<Order> storeOrdersList.
     */
    public ArrayList <Order> getStoreOrdersList(){
        return storeOrdersList;
    }

    /**
     * Turn the store orders list into a string that is readable.
     * @return String, store orders list.
     */
    @Override
    public String toString(){
        return storeOrdersList.toString();
    }
}
