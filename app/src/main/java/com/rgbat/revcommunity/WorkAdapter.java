package com.rgbat.revcommunity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.MyViewHolder> {

    private List<CtegoryModel> catList;
    private Dialog loadingDialog;



    public WorkAdapter(List<CtegoryModel> catList) {
        this.catList = catList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.share_layout,parent,
                false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int pos) {
       // holder.texEmployer.setText(catList.get(position).getId());
//        holder.texDescription.setText(catList.get(position).getName());
//        holder.texEmployer.setText(catList.get(position).getNoOfSets());
       // holder.dateTime.setText(catList.get(position));
        String description =catList.get(pos).getName();
        String employer =catList.get(pos).getNoOfSets();

        holder.setData(description,employer,pos,this);

    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView texDescription,texEmployer,texPlace,dateTime;
        ImageButton delete;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            texDescription = itemView.findViewById(R.id.desc);
            texEmployer = itemView.findViewById(R.id.employer);
            texPlace = itemView.findViewById(R.id.place);
           // dateTime = itemView.findViewById(R.id.date_time);

loadingDialog = new Dialog(itemView.getContext());

            loadingDialog = new Dialog(itemView.getContext());
            loadingDialog.setContentView(R.layout.loading_progress);
            loadingDialog.setCancelable(false);
            loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
            loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);



           // delete = itemView.findViewById(R.id.delete_btn);


        }


        public void setData(String description, String place, final int pos, final WorkAdapter adapter) {

            texEmployer.setText(place);
            texDescription.setText(description);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog dialog = new AlertDialog.Builder(itemView.getContext())
                            .setTitle("DELETE")
                            .setMessage("Do You Want Delete ? ")
                            .setPositiveButton("Delet", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            }).setNegativeButton("Cancel", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    dialog.getButton(dialog.BUTTON_POSITIVE).setBackgroundColor(Color.RED);
                    dialog.getButton(dialog.BUTTON_NEGATIVE).setBackgroundColor(Color.RED);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, 0, 20, 0);
                    dialog.getButton(dialog.BUTTON_NEGATIVE).setLayoutParams(params);


                }
            });


        }

    }
}
