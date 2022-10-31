package com.example.rucafeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MicrophoneInfo;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CoffeeActivity extends AppCompatActivity implements OnItemSelectedListener{
    private TextView subTotal;
    private CheckBox milkCheckBox, creamCheckBox, syrupCheckBox, caramelCheckBox, whippedCreamCheckBox;
    private Button addCoffee;
    private Spinner coffeeSizes;
    private static final double INITIAL_PRICE = 0.00;
    private static final int INITIAL_QUANTITY = 1;
    private Coffee coffee = new Coffee("Short", INITIAL_QUANTITY);
    private double price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_first);

        subTotal = (TextView) findViewById(R.id.coffeeSubTotal);
        //subTotal.setText("0.00");
        addCoffee = (Button) findViewById(R.id.addCoffee);

        String [] sizes = {"Short", "Tall", "Grande", "Venti"};
        coffeeSizes = findViewById(R.id.coffeeSize);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coffeeSizes.setAdapter(adapter);
        coffeeSizes.setOnItemSelectedListener(this);

        initiateCheckboxes();

        creamCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(creamCheckBox.isChecked()){
                    updatePriceForAddOns("yes");
                    coffee.add(0);
                }
                else{
                    updatePriceForAddOns("no");
                    coffee.remove(0);
                }
            }
        });
        syrupCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(syrupCheckBox.isChecked()){
                    updatePriceForAddOns("yes");
                    coffee.add(1);
                }
                else{
                    updatePriceForAddOns("no");
                    coffee.remove(1);
                }
            }
        });
        milkCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(milkCheckBox.isChecked()){
                    updatePriceForAddOns("yes");
                    coffee.add(2);
                }
                else{
                    updatePriceForAddOns("no");
                    coffee.remove(2);
                }
            }
        });
        caramelCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(caramelCheckBox.isChecked()){
                    updatePriceForAddOns("yes");
                    coffee.add(3);
                }
                else{
                    updatePriceForAddOns("no");
                    coffee.remove(3);
                }
            }
        });
        whippedCreamCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(whippedCreamCheckBox.isChecked()){
                    updatePriceForAddOns("yes");
                    coffee.add(4);
                }
                else{
                    updatePriceForAddOns("no");
                    coffee.remove(4);
                }
            }
        });

        addCoffee.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Coffee coffee2 = new Coffee((String) coffeeSizes.getSelectedItem(), 1);

                for(int i = 0; i < coffee.getAddIns().length; i++){
                    if(coffee.getAddIns()[i]) coffee2.add(i);
                }
                String message = coffee2.toString() + " - Added";
                MainActivity.addMenuItem(coffee2);

                if(creamCheckBox.isChecked()) creamCheckBox.toggle();
                if(syrupCheckBox.isChecked()) syrupCheckBox.toggle();
                if(milkCheckBox.isChecked()) milkCheckBox.toggle();
                if(caramelCheckBox.isChecked()) caramelCheckBox.toggle();
                if(whippedCreamCheckBox.isChecked()) whippedCreamCheckBox.toggle();

                for(int i = 0; i < coffee.getAddIns().length; i++){
                    if(coffee.getAddIns()[i]) coffee.remove(i);
                }
                coffeeSizes.setAdapter(adapter);
                subTotal.setText("1.69");

                Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text = adapterView.getItemAtPosition(position).toString();
        double basePrice = INITIAL_PRICE;
        for(int i = 0; i < coffee.getAddIns().length; i++) {
            if(coffee.getAddIns()[i]) basePrice += Coffee.ADD_ONS;
        }

        double tempPrice = Coffee.BASE_PRICE;
        DecimalFormat formatter = new DecimalFormat("0.00");
        if(text.equals("Short")) basePrice += tempPrice;
        else if(text.equals("Tall")) basePrice += tempPrice + Coffee.TALL;
        else if(text.equals("Grande")) basePrice += tempPrice + Coffee.GRANDE;
        else if(text.equals("Venti")) basePrice += tempPrice + Coffee.VENTI;

        subTotal.setText(formatter.format(basePrice));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void initiateCheckboxes(){
        milkCheckBox = findViewById(R.id.milkCheckBox);
        creamCheckBox = findViewById(R.id.creamCheckBox);
        syrupCheckBox = findViewById(R.id.syrupCheckBox);
        caramelCheckBox = findViewById(R.id.caramelCheckBox);
        whippedCreamCheckBox = findViewById(R.id.whippedCreamCheckBox);
    }

    /**
     * Determines total price of Coffee add ons
     * @param command command argument for if add on is selected or not.
     */
    private void updatePriceForAddOns(String command){
        price = Double.parseDouble((String) subTotal.getText());
        DecimalFormat formatter = new DecimalFormat("0.00");

        if(command.equals("yes")) price += (coffee.ADD_ONS);
        else price -= (coffee.ADD_ONS);

        subTotal.setText(formatter.format(price));
    }
}