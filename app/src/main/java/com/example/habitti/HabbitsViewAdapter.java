package com.example.habitti;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class HabbitsViewAdapter extends ArrayAdapter<HabbitsView>{

    public HabbitsViewAdapter(@NonNull Activity context, ArrayList<HabbitsView> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }

        HabbitsView currentNumberPosition = getItem(position);

        ImageView habbitsImage = currentItemView.findViewById(R.id.imageView);
        assert currentNumberPosition != null;
        habbitsImage.setImageResource((currentNumberPosition.getIvHabbitImageIdImageId()));

        TextView textViewHabbitName = currentItemView.findViewById(R.id.textViewHabbitName);
        textViewHabbitName.setText(currentNumberPosition.getmHabbitName());

        TextView textViewHabbitDaysStreak = currentItemView.findViewById(R.id.textViewDays);
        textViewHabbitDaysStreak.setText(currentNumberPosition.getmHabbitDaysStreak());

        return currentItemView;
    }
}
