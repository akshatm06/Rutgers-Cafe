package com.example.rucafeandroid;

/**
 * Donut class used to create donut objects to be added as Menu Items to the orders.
 * Donuts have different types, flavors, and can be ordered in different quantities.
 * @author Akshat Mehta and Atharva Patil
 */
public class Donut extends MenuItem{
    private String donutType;
    private String donutFlavor;
    public static final double YEAST = 1.59;
    public static final double CAKE = 1.79;
    public static final double DONUT_HOLES = 0.39;
    private static final double NO_PRICE = 0.00;

    /**
     * Donut Consturctor to create a donut object that has a type, flavor, and quantity.
     * @param type type of donut (yeast donut, cake donut, and donut holes).
     * @param flavor flavor for each particular type of donut.
     * @param quant amount of donuts.
     */
    public Donut(String type, String flavor, int quant){
        super(quant);
        this.donutType = type;
        this.donutFlavor = flavor;
    }

    /**
     * The price of each donut based on type.
     * @return double, the price of 1 donut given its type.
     */
    @Override
    public double itemPrice(){
        if(donutType.equals("Yeast")){
            return YEAST;
        }
        else if(donutType.equals("Cake")){
            return CAKE;
        }
        else if(donutType.equals("Donut Holes")){
            return DONUT_HOLES;
        }
        return NO_PRICE;
    }

    /**
     * toString method to textually represent a donut object.
     * @return String representation of a donut.
     */
    @Override
    public String toString(){
        return this.donutType + ": " + this.donutFlavor + " - " + this.quantity;
    }

    /**
     * Returns the type of donut a donut object is.
     * @return Type of donut.
     */
    public String getDonutType(){
        return this.donutType;
    }

    /**
     * Returns the flavor of the donut object.
     * @return Flavor of donut object.
     */
    public String getDonutFlavor(){
        return this.donutFlavor;
    }

    /**
     * Checks if two donuts are the same based on type and flavor.
     * @param item MenuItem
     * @return true if same, false otherwise.
     */
    @Override
    public boolean equals(MenuItem item){
        Donut donut = (Donut) item;
        if(this.donutType.equals(donut.donutType) && this.donutFlavor.equals(donut.donutFlavor)){
                return true;
        }
        return false;
    }


}
