package com.example.rucafeandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class YourOrderActivity extends AppCompatActivity {

    private ListView yourOrderList;
    private TextView yourOrderSubTotal;
    private TextView yourOrderSalesTax;
    private TextView yourOrderTotal;
    private Button yourOrderPlaceOrder;
    public static final double SALES_TAX = 0.06625;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_order_first);
        yourOrderList = findViewById(R.id.yourOrderList);
        yourOrderSubTotal = findViewById(R.id.yourOrderSubTotal);
        yourOrderSalesTax = findViewById(R.id.yourOrderTax);
        yourOrderTotal = findViewById(R.id.yourOrderTotal);
        yourOrderPlaceOrder = findViewById(R.id.yourOrderPlaceOrder);

        ArrayList<String> orderListString = new ArrayList<>();
        for(MenuItem item : MainActivity.getOrder().getOrderList()){
            orderListString.add(item.toString());
        }
        ArrayAdapter<String> list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderListString);
        yourOrderList.setAdapter(list);

        DecimalFormat formatter = new DecimalFormat("0.00");
        double subTotal = MainActivity.findSubTotal();
        yourOrderSubTotal.setText("Sub-Total: $" + formatter.format(subTotal));

        double salesTax = MainActivity.findSubTotal() * SALES_TAX;
        yourOrderSalesTax.setText("Sales Tax: $" + formatter.format(salesTax));
        yourOrderTotal.setText("Total: $" + formatter.format(subTotal + salesTax));

        yourOrderList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(YourOrderActivity.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete MenuItem?");
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.removeMenuItem(orderListString.remove(positionToRemove));
                        list.notifyDataSetChanged();
                        double subTotalTemp = MainActivity.findSubTotal();
                        yourOrderSubTotal.setText("Sub-Total: $" + formatter.format(subTotalTemp));
                        double salesTaxTemp = MainActivity.findSubTotal() * SALES_TAX;
                        yourOrderSalesTax.setText("Sales Tax: $" + formatter.format(salesTaxTemp));
                        yourOrderTotal.setText("Total: $" + formatter.format(subTotalTemp + salesTaxTemp));
                    }});
                adb.show();
            }
        });

        yourOrderPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!list.isEmpty()) {
                    MainActivity.addOrder();
                    MainActivity.setOrder(new Order());
                    yourOrderList.setAdapter(null);
                    list.clear();
                    yourOrderSubTotal.setText("Sub-Total: $0.00");
                    yourOrderSalesTax.setText("Sales Tax: $0.00");
                    yourOrderTotal.setText("Total: $0.00");
                }
                else{
                    String message[] = getResources().getStringArray(R.array.ordering_basket_error);
                    Toast.makeText(view.getContext(), message[0], Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}