package com.example.rucafeandroid;
import java.util.ArrayList;

/**
 * Order Class represents what items are in one order that is placed by the user.
 * Each particular item in an order is stored in an array list of one instance of order.
 * There exists methods to manipulate items in an instance of order.
 * @author Akshat Mehta and Atharva Patil
 */
public class Order implements Customizable {

    private ArrayList <MenuItem> orderList = new ArrayList<>();

    /**
     * Add method that adds an item like coffee and donut to an order in the array list.
     * @param obj Coffee or donut object.
     * @return Returns true if added, false otherwise.
     */
    @Override
    public boolean add(Object obj){
        boolean success = orderList.add((MenuItem) obj);
        return success;
    }

    /**
     * Remove method that removes an item like coffee and donut from an order in the array list.
     * @param obj Coffee or donut object.
     * @return Returns true if added, false otherwise.
     */
    @Override
    public boolean remove(Object obj){
        boolean success = orderList.remove((MenuItem) obj);
        return success;
    }


    /**
     * Returns the array list of all the items that comprise one instance of order.
     * @return Returns array list that holds all items in an order
     */
    public ArrayList<MenuItem> getOrderList(){
        return orderList;
    }

    /**
     * toString method to textually represent an order as a string.
     * @return String that represents an order object.
     */
    @Override
    public String toString(){
        String orderString = "";
        for(MenuItem item : orderList){
            orderString = orderString + item.toString() + "\n";
        }
        return orderString;
    }

}
