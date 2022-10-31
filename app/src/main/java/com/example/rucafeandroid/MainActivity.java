package com.example.rucafeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;

import java.io.Serializable;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements Serializable {

    private ImageView orderDonut;
    private ImageView orderCoffee;
    private ImageView yourOrder;
    private ImageView storeOrder;
    private static Order order = new Order();
    private static StoreOrders storeOrders = new StoreOrders();
    private static double price = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderDonut = (ImageView) findViewById(R.id.OrderDonut);
        orderDonut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DonutActivity.class);
                startActivity(intent);
            }
        });

        orderCoffee = (ImageView) findViewById(R.id.OrderCoffee);
        orderCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
                startActivity(intent);
            }
        });

        yourOrder = (ImageView) findViewById(R.id.YourOrders);
        yourOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, YourOrderActivity.class);
                startActivity(intent);
            }
        });

        storeOrder = (ImageView) findViewById(R.id.StoreOrders);
        storeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StoreOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void setOrder(Order orderTemp){
        order = orderTemp;
    }

    public static Order getOrder(){
        return order;
    }

    public static StoreOrders getStoreOrders(){ return storeOrders; }

    public static void addMenuItem(MenuItem item){
        for(MenuItem i : order.getOrderList()) {
            if (item instanceof Coffee && i instanceof Coffee && i.equals(item)) {
                i.quantity = i.getQuantity() + 1;
                System.out.println(order.toString());
                return;

            }
            else if (item instanceof Donut && i instanceof Donut && i.equals(item)) {
                i.quantity = i.getQuantity() + 1;
                System.out.println(order.toString());
                return;
                }
            }
        order.add(item);
        System.out.println(order.toString());
    }

    public static void removeMenuItem(String item){
        for(MenuItem i : order.getOrderList()){
            if(i.toString().equals(item)) {
                order.remove(i);
                break;
            }
        }
    }

    public static double findSubTotal(){
        double tempPrice = 0.00;
        for(MenuItem i : order.getOrderList()) {
            if (i instanceof Coffee) {
                tempPrice += i.itemPrice() * i.getQuantity();
                System.out.println(i.itemPrice() + "  " + i.getQuantity());
            }
            else if (i instanceof Donut) {
                tempPrice += i.itemPrice() * i.getQuantity();
                System.out.println(i.itemPrice() + "  " + i.getQuantity());
            }
            System.out.println(tempPrice);
        }
        System.out.println(tempPrice);
        price = tempPrice;
        return price;
    }

    public static double findSubTotalForOrder(Order orderTemp){
        double tempPrice = 0.00;
        for(MenuItem i : orderTemp.getOrderList()) {
            if (i instanceof Coffee) {
                tempPrice += i.itemPrice() * i.getQuantity();
                System.out.println(i.itemPrice() + "  " + i.getQuantity());
            }
            else if (i instanceof Donut) {
                tempPrice += i.itemPrice() * i.getQuantity();
                System.out.println(i.itemPrice() + "  " + i.getQuantity());
            }
            System.out.println(tempPrice);
        }
        System.out.println(tempPrice);
        price = tempPrice;
        return price;
    }
    public static void addOrder(){
        storeOrders.add(order);
    }

    public static String[] findTotalForAllOrders(){
        String total[] = new String[storeOrders.getStoreOrdersList().size()];
        int count = 0;
        DecimalFormat formatter = new DecimalFormat("0.00");
        for(Order order : storeOrders.getStoreOrdersList()){
            double overallTotal = findSubTotalForOrder(order) + (findSubTotalForOrder(order) * YourOrderActivity.SALES_TAX);
            total[count] = formatter.format(overallTotal);
            count++;
        }
        return total;
    }
}