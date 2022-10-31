package com.example.rucafeandroid;

/**
 * Coffee class used to create coffee objects to be added as Menu Items to the orders.
 * Coffee's have size, different add ins, and can be ordered in different quantities.
 * @author Akshat Mehta and Atharva Patil
 */
public class Coffee extends MenuItem implements Customizable{
    private String size;
    private boolean [] addIns = {false, false, false, false, false};
    public static final double BASE_PRICE = 1.69;
    public static final double ADD_ONS = 0.30;
    public static final double TALL = 0.40;
    public static final double GRANDE = 0.80;
    public static final double VENTI = 1.20;



    /**
     * Coffee constructor that creates the coffee object.
     * @param sze size of coffee.
     * @param quant number of coffees ordered.
     */
    public Coffee(String sze, int quant){
        super(quant);
        this.size = sze;
    }

    /**
     * Add a coffee add in to the array of add ins by setting the index to true.
     * @param o object o, which will be an integer in our case to represent the index of array.
     * @return boolean true if added, false otherwise.
     */
    @Override
    public boolean add(Object o){
        if(o instanceof Integer){
            addIns[(Integer) o] = true;
            return true;
        }
        return false;
    }

    /**
     * Remove a coffee add in to the array of add ins by setting the index to false.
     * @param o object o, which will be an integer in our case to represent the index of array.
     * @return boolean true if removed, false otherwise.
     */
    @Override
    public boolean remove(Object o){
        if(o instanceof Integer){
            addIns[(Integer) o] = false;
            return true;
        }
        return false;
    }



    /**
     * The price of each coffee based on size and add ins.
     * @return double, the price of 1 coffee given its attributes.
     */
    @Override
    public double itemPrice() {
        double price = BASE_PRICE;
        if(size.equals("Tall")){
            price += TALL;
        }
        else if(size.equals("Grande")){
            price += GRANDE;
        }
        else if(size.equals("Venti")){
            price += VENTI;
        }

        int count = 0;
        for(int i = 0; i < addIns.length; i++){
            if(addIns[i])
                count++;
        }
        price += count * ADD_ONS;
        return price;
    }

    /**
     * Turn the coffee object into a string that is readable by us.
     * @return String, coffee object as string.
     */
    @Override
    public String toString(){
        String temp = " ";
        for(int i = 0; i < addIns.length; i++){
            if(addIns[i]){
                if(i == 0) temp += "Cream ";
                else if(i == 1) temp += "Syrup ";
                else if(i == 2) temp += "Milk ";
                else if(i == 3) temp += "Caramel ";
                else if(i == 4) temp += "Whipped Cream ";
            }
        }
        return size + " -" + temp + "- " + quantity;
    }

    /**
     * Checks if two coffees are the same based on size and add ins.
     * @param item MenuItem
     * @return true if same, false otherwise.
     */
    @Override
    public boolean equals(MenuItem item){
        Coffee coffee = (Coffee) item;
        if(this.size.equals(coffee.size)){
            for(int i = 0; i < addIns.length; i++){
                if(this.addIns[i] != coffee.addIns[i]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Get the addIns array.
     * @return boolean array addIns.
     */
    public boolean[] getAddIns(){
        return addIns;
    }

    /**
     * Set the size of the coffee.
     * @param s String size.
     */
    public void setSize(String s){
        this.size = s;
    }

    /**
     * Set the quantity of the coffee.
     * @param q quantity q.
     */
    public void setQuantity(int q){
        this.quantity = q;
    }
}
