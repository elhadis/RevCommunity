package com.rgbat.revcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Holders.FidWorkAdabter;
import Model.PostingWork;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView postList;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Dialog loadingDialog;
    private DatabaseReference Ref;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        postList = findViewById(R.id.all_users_post_list);
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");


        postList.setHasFixedSize(true);


        linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        postList.setLayoutManager(linearLayoutManager);

        loadingDialog = new Dialog(MainActivity.this);
        loadingDialog.setContentView(R.layout.loading_progress);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Ref = FirebaseDatabase.getInstance().getReference().child("Posting");
        View nav_view = navigationView.inflateHeaderView(R.layout.navigation_header);
        actionBarDrawerToggle =new ActionBarDrawerToggle(MainActivity.this,drawerLayout,
                R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                UserMenuSelector(item);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void UserMenuSelector(MenuItem item) {
        switch (item .getItemId()){


            case R.id.help:
                Intent intent = new Intent(MainActivity.this,AdminActivity.class);
                startActivity(intent);
                break;

                case R.id.house:
                    Intent apartment = new Intent(getBaseContext(),Apartemnts2Activity.class);
                    startActivity(apartment);

                break;


                case R.id.coors:
                    Intent house  =new Intent(getBaseContext(),ShareWorkActivity.class);
                    startActivity(house);
//                    Intent coors  =new Intent(getBaseContext(),SetsActivity.class);
//                    startActivity(coors);
                break;

                case R.id.kid:
                    Intent kids  =new Intent(getBaseContext(),FindWorkActivity.class);
                    startActivity(kids);
                break;

                case R.id.family:
                    Intent families  =new Intent(getBaseContext(),FamiliesActivity.class);
                    startActivity(families);
                break;

            case R.id.product:
                Intent product  =new Intent(getBaseContext(),Productsctivity.class);
                startActivity(product);
                break;




        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        findWork();
        loadingDialog.show();
    }

    private void findWork() {
        FirebaseRecyclerOptions<PostingWork> options =
                new FirebaseRecyclerOptions.Builder<PostingWork>()
                        .setQuery(Ref,PostingWork.class).build();
        FirebaseRecyclerAdapter<PostingWork, FidWorkAdabter> adapter = new
                FirebaseRecyclerAdapter<PostingWork, FidWorkAdabter>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull FidWorkAdabter holder, int position, @NonNull PostingWork model) {
                        holder.workType.setText(model.getWorkType());
                        holder.worPlace.setText(model.getWorkCity());
                        holder.workNumber.setText(model.getWorkEmployerNum());
                        holder.dateTime.setText(model.getDate()+ " " + model.getTime());
                        loadingDialog.dismiss();



                    }

                    @NonNull
                    @Override
                    public FidWorkAdabter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_work_layout,
                                parent,false);
                        return new FidWorkAdabter(view);
                    }
                };

        postList.setAdapter(adapter);
        adapter.startListening();


    }
}
