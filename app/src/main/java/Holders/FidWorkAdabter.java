package Holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rgbat.revcommunity.R;

public class FidWorkAdabter extends RecyclerView.ViewHolder {
     public TextView worPlace,workType,workNumber,dateTime;

    public FidWorkAdabter(@NonNull View itemView) {
        super(itemView);
        worPlace = itemView.findViewById(R.id.editText_place);
        workType = itemView.findViewById(R.id.editText_type);
        workNumber = itemView.findViewById(R.id.editText_number);
        dateTime= itemView.findViewById(R.id.editText_dateTime);

    }
}
