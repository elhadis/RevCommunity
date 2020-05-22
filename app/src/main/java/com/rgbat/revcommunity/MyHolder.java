package com.rgbat.revcommunity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {
    TextView place,phone,date,desc;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        place = itemView.findViewById(R.id.place);
        phone =itemView.findViewById(R.id.employer);
        date =itemView.findViewById(R.id.date_time);
        desc = itemView .findViewById(R.id.desc);

    }
}
