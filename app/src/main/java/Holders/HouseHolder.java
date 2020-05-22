package Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.rgbat.revcommunity.R;

public class HouseHolder extends RecyclerView.ViewHolder {
    public TextView description,place,price,date;
    public ImageView imageView;
    public HouseHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.house_display_image);
        description = itemView.findViewById(R.id.house_display_description);
        place = itemView.findViewById(R.id.house_display_place);
        price = itemView.findViewById(R.id.house_display_price);
        date = itemView.findViewById(R.id.house_display_date);
    }
}
