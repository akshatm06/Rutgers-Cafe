package com.example.rucafeandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StoreOrderActivity extends AppCompatActivity {

    private ListView storeOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_order_first);

        storeOrderList = findViewById(R.id.storeOrderList);

        ArrayList<String> storeOrderListString = new ArrayList<>();
        int count = 0;
        for(Order order : MainActivity.getStoreOrders().getStoreOrdersList()){
            String total[] = MainActivity.findTotalForAllOrders();
            storeOrderListString.add(order.toString() + "\n" + "Total: " + total[count]);
            count++;
        }
        ArrayAdapter<String> storeList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, storeOrderListString);
        storeOrderList.setAdapter(storeList);

        storeOrderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(StoreOrderActivity.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete Order?");
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.removeMenuItem(storeOrderListString.remove(positionToRemove));
                        storeList.notifyDataSetChanged();

                    }});
                adb.show();
            }
        });
    }
}