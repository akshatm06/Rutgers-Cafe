package com.example.rucafeandroid;

/**
 * MenuItem class used to create coffee and donut objects to be added as Menu Items to the orders.
 * MenuItem's represent the things ordered.
 * @author Akshat Mehta and Atharva Patil
 */
public class MenuItem {

    protected int quantity;
    private static final double MENUITEM_HAS_NO_PRICE = 0.00;

    /**
     * MenuItem constructor that simply initializes the MenuItem quantity.
     * Other specific characteristics are initialized in Coffee and Donut class.
     * @param quantityGiven quantity of MenuItem.
     */
    public MenuItem(int quantityGiven){
        this.quantity = quantityGiven;
    }

    /**
     * Price of each MenuItem. It is overridden in Coffee and Donut classes to get real price.
     * @return double, price of menu item.
     */
    public double itemPrice(){
        return MENUITEM_HAS_NO_PRICE;
    }

    /**
     * Checks if two MenuItems are equal.
     */
    public boolean equals(MenuItem item){
        return true;
    }
    /**
     * Get the quantity of each menu item.
     * @return int, quantity.
     */
    public int getQuantity(){ return quantity; }
}
