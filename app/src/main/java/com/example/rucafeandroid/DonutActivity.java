package com.example.rucafeandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Arrays;

public class DonutActivity extends AppCompatActivity {

    private RecyclerView donutRecyclerView;

    private String donutNames[], donutPrices[];
    int donutImages[] = {R.drawable.sugar, R.drawable.jelly, R.drawable.pumpkin, R.drawable.glazed,
            R.drawable.chocolate, R.drawable.vanilla, R.drawable.strawberry, R.drawable.redvelvet,
            R.drawable.powdered, R.drawable.lemon, R.drawable.cinnamon, R.drawable.blueberry};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_first);

        Intent intent = getIntent();
        Order order = (Order) intent.getSerializableExtra("ORDER");

        donutRecyclerView = findViewById(R.id.donutRecyclerView);

        donutNames = getResources().getStringArray(R.array.donut_names);
        donutPrices = getResources().getStringArray(R.array.donut_prices);

        MyDonutAdapter myDonutAdapter = new MyDonutAdapter(this, donutNames, donutPrices, donutImages);
        donutRecyclerView.setAdapter(myDonutAdapter);
        donutRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}