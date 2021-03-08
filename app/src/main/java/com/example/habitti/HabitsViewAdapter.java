package com.example.habitti;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * <h1>HabitsViewAdapter</h1>
 * Creates a listView adapter based on habitView data
 * @author Santeri Hytönen
 */
public class HabitsViewAdapter extends ArrayAdapter<HabitsView>{

    /**
     *
     * @param context get the context
     * @param arrayList get the items to arrayList
     */
    public HabitsViewAdapter(@NonNull Activity context, ArrayList<HabitsView> arrayList) {
        super(context, 0, arrayList);
    }

    /**
     *
     * @param position get position of the item
     * @param convertView get convertView
     * @param parent get parent
     * @return one item to be placed in the listView
     */
    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }

        HabitsView currentNumberPosition = getItem(position);

        //Find the imageView and set the image from habits data
        ImageView habbitsImage = currentItemView.findViewById(R.id.imageView);
        assert currentNumberPosition != null;
        habbitsImage.setImageResource((currentNumberPosition.getIvHabbitImageIdImageId()));

        //Find the textView and set the name from habits data
        TextView textViewHabbitName = currentItemView.findViewById(R.id.textViewHabbitName);
        textViewHabbitName.setText(currentNumberPosition.getmHabbitName());

        //Find the textView and set the type from habits data
        TextView textViewHabitType = currentItemView.findViewById(R.id.habitType);
        textViewHabitType.setText(currentNumberPosition.getmHabitType());

        //Find the textView and set the days streak from habits data
        TextView textViewHabbitDaysStreak = currentItemView.findViewById(R.id.textViewDays);
        textViewHabbitDaysStreak.setText(currentNumberPosition.getmHabbitDaysStreak());

        //Find the checkBox and set its state from habits data
        CheckBox checkBox = currentItemView.findViewById(R.id.radioButton);
        checkBox.setChecked(currentNumberPosition.getCheckStatus());



        return currentItemView;
    }
}
