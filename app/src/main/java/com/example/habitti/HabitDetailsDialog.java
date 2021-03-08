package com.example.habitti;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * <h1>Viewing habit details</h1>
 * The intention of AddHabitDialog is to take advantage of the Android Dialog system.
 * the purpose is specifically to build a Dialog window, using the new_habit_dialog.xml file.
 * AddHabitDialog methods cannot be used to display the dialog.
 * @author Jere Vuola
 */

public class HabitDetailsDialog extends AppCompatDialogFragment {
    private EditText detailsHabitName;
    private TextView detailsHabitType;
    private ImageView detailsHabitImage;
    private TextView detailsHabitDateCreated;
    private TextView detailsHabitDaysStreak;
    private TextView detailsHabitScores;
    private TextView detailsHabitMultiplier;
    private int index;

    public HabitDetailsDialog(int index){
        this.index = index;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.habit_details_dialog, null);



        detailsHabitName = view.findViewById(R.id.detailsHabitName);
        detailsHabitType = view.findViewById(R.id.detailsHabitType);
        detailsHabitImage = view.findViewById(R.id.detailsHabitImage);
        detailsHabitDateCreated = view.findViewById(R.id.detailsHabitCreationDate);
        detailsHabitDaysStreak = view.findViewById(R.id.detailsHabitStreak);
        detailsHabitScores = view.findViewById(R.id.detailsHabitScore);
        detailsHabitMultiplier = view.findViewById(R.id.detailsHabitMultiplier);

        detailsHabitName.setText(GlobalModel.getInstance().getHabbitViewItem(index).getmHabbitName());
        detailsHabitType.setText("Habit type: " + GlobalModel.getInstance().getHabbitViewItem(index).getmHabitType());
        detailsHabitImage.setImageResource(GlobalModel.getInstance().getHabbitViewItem(index).getIvHabbitImageIdImageId());
        detailsHabitDateCreated.setText("Date created: " + GlobalModel.getInstance().getHabbitViewItem(index).getDateCreated());
        detailsHabitDaysStreak.setText("Habit streak: " + GlobalModel.getInstance().getHabbitViewItem(index).getmHabbitDaysStreak());
        detailsHabitScores.setText("Habit score: " + GlobalModel.getInstance().getHabbitViewItem(index).getmHabbitScores());
        detailsHabitMultiplier.setText("Score multiplier: " + Double.toString(GlobalModel.getInstance().getHabbitViewItem(index).getScoreMultiplier()));


        builder.setView(view)
                .setTitle("Habit details")
                .setNegativeButton("delete habit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GlobalModel.getInstance().deleteHabbit(index);
                        FragmentManager fm = getFragmentManager();
                        MainFragment fragm = (MainFragment)fm.findFragmentById(R.id.fragment_container);
                        fragm.updateUI();

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GlobalModel.getInstance().setHabitName(index, detailsHabitName.getText().toString());
                                GlobalModel.getInstance().updateHabbitViewList();
                                FragmentManager fm = getFragmentManager();
                                MainFragment fragm = (MainFragment)fm.findFragmentById(R.id.fragment_container);
                                fragm.updateUI();
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(getResources().getColor(R.color.md_pink_A400));
        return alert;
    }
}
