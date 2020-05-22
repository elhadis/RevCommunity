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

import Holders.HouseHolder;
import Model.Houses;

public class Apartemnts2Activity extends AppCompatActivity {
    private RecyclerView apartmentList;
    private LinearLayoutManager linearLayoutManager;
    private Dialog loadingDialog;
    private DatabaseReference Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartemnts2);

        apartmentList = findViewById(R.id.apartment_list);
        linearLayoutManager = new LinearLayoutManager(Apartemnts2Activity.this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        apartmentList.setHasFixedSize(true);
        apartmentList.setLayoutManager(linearLayoutManager);

        Ref = FirebaseDatabase.getInstance().getReference().child("Houses");

        loadingDialog = new Dialog(Apartemnts2Activity.this);
        loadingDialog.setContentView(R.layout.loading_progress);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadingDialog.show();

        loadingData();
    }

    private void loadingData() {



        FirebaseRecyclerOptions<Houses> options = new
                FirebaseRecyclerOptions.Builder<Houses>()
                .setQuery(Ref,Houses.class).build();
        FirebaseRecyclerAdapter<Houses, HouseHolder> adapter = new
                FirebaseRecyclerAdapter<Houses, HouseHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull HouseHolder holder, int position, @NonNull Houses model) {
                        holder.description.setText(model.getDescription());
                        holder.price.setText(" Rent price = "+ model.getPrice());
                        holder.place.setText("Apartment City is " + model.getPlace());
                        holder.date.setText(model.getDate()+ "  "  + model.getTime());
                        Picasso.get().load(model.getHouseimage()).into(holder.imageView);
                        loadingDialog.dismiss();


                    }

                    @NonNull
                    @Override
                    public HouseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_layout,parent,
                                false);
                        return new HouseHolder(view);
                    }
                };
        apartmentList.setAdapter(adapter);
        adapter.startListening();


    }


}
