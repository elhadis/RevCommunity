package com.rgbat.revcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.security.interfaces.RSAPrivateCrtKey;

import Holders.PrroductsHolder;
import Model.Products;

public class Productsctivity extends AppCompatActivity {

    private RecyclerView productList;
    private LinearLayoutManager linearLayoutManager;
    private Dialog loadingDialog;
    private DatabaseReference Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productsctivity);
        productList = findViewById(R.id.product_main_list);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        productList.setLayoutManager(linearLayoutManager);

        Ref = FirebaseDatabase.getInstance().getReference().child("Products");





        loadingDialog = new Dialog(Productsctivity.this);
        loadingDialog.setContentView(R.layout.loading_progress);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadingDialog.show();

        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(Ref,Products.class).build();
        FirebaseRecyclerAdapter<Products, PrroductsHolder> adapter = new
                FirebaseRecyclerAdapter<Products, PrroductsHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull PrroductsHolder holder, int position, @NonNull Products model) {
                        holder.productPrice.setText("Price = " +model.getPrice());
                        holder.productPhone.setText("Tel  " +model.getPhone());
                        holder.productTime.setText(model.getDate()+ " " +model.getTime());
                        Picasso.get().load(model.getProductimage()).into(holder.productImage);
                        loadingDialog.dismiss();

                    }

                    @NonNull
                    @Override
                    public PrroductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout,parent,false);
                        return new PrroductsHolder(view);
                    }
                };
        productList.setAdapter(adapter);
        adapter.startListening();
    }
}
