package com.example.rucafeandroid;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDonutAdapter extends RecyclerView.Adapter<MyDonutAdapter.MyDonutViewHolder> {

    private String data1[], data2[];
    private int images[];
    private Context context;


    public MyDonutAdapter(Context ct, String s1[], String s2[], int img[]){
        this.context = ct;
        this.data1 = s1;
        this.data2 = s2;
        this.images = img;
    }


    @NonNull
    @Override
    public MyDonutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyDonutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDonutViewHolder holder, int position) {
        holder.donut_names.setText(data1[position]);
        holder.donut_prices.setText(data2[position]);
        holder.donut_images.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyDonutViewHolder extends RecyclerView.ViewHolder{

        private TextView donut_names, donut_prices;
        private ImageView donut_images;
        private Button addDonut;
        private ConstraintLayout parentLayout;

        public MyDonutViewHolder(@NonNull View itemView) {
            super(itemView);
            donut_names = itemView.findViewById(R.id.donutNames);
            donut_prices = itemView.findViewById(R.id.donutPrices);
            donut_images = itemView.findViewById(R.id.donutImages);
            addDonut = itemView.findViewById(R.id.addDonut);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            donut_images.getLayoutParams().height = 300;
            donut_images.getLayoutParams().width = 300;

            setAddDonutOnClick(itemView);

            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DonutActivity.class);
                    intent.putExtra("Item", donut_names.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        private void setAddDonutOnClick(@NonNull View itemView) {
            addDonut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Add to order");
                    alert.setMessage(donut_names.getText().toString());

                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    donut_names.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                            String [] donutSplit = donut_names.getText().toString().split(" - ");
                            String name = donutSplit[0];
                            String flavor = donutSplit[1];
                            int quantity = 1;

                            Donut donut = new Donut(name, flavor, quantity);
                            MainActivity.addMenuItem(donut);
                        }
                        
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    donut_names.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }


    }
}
