package Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rgbat.revcommunity.R;

public class PrroductsHolder extends RecyclerView.ViewHolder {


    public TextView productPrice,productPhone,productTime;
    public ImageView productImage;
    public PrroductsHolder(@NonNull View itemView) {
        super(itemView);

        productPrice = itemView.findViewById(R.id.product_price);
        productPhone = itemView.findViewById(R.id.product_phone);
        productTime = itemView.findViewById(R.id.product_date);
        productImage = itemView.findViewById(R.id.product_image);
    }
}
