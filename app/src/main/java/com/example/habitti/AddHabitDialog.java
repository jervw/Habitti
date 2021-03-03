package com.example.habitti;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AddHabitDialog extends AppCompatDialogFragment {

    MainFragment mainFragment;
    int imageId;
    String spinnerSelectedText;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_habit_dialog, null);

        final ImageView dialogImageView = (ImageView) view.findViewById(R.id.habitImage);
        dialogImageView.setImageResource(R.drawable.habit_1);

        TextView textView = (TextView) view.findViewById(R.id.habbitName);

        Spinner habitSpinner = (Spinner) view.findViewById(R.id.spinnerHabbits);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.habbits_array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        habitSpinner.setAdapter(adapter);

        habitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> spinnerHabbits, View view, int pos, long id) {
                spinnerSelectedText = habitSpinner.getItemAtPosition(pos).toString();
                switch (spinnerSelectedText) {
                    case "No smoking":
                        textView.setText(spinnerSelectedText);
                        imageId = R.drawable.habit_1;
                        break;
                    case "No alcohol":
                        textView.setText(spinnerSelectedText);
                        imageId = R.drawable.habit_3;
                        break;
                    case "No sugar":
                        textView.setText(spinnerSelectedText);
                        imageId = R.drawable.habit_4;
                        break;
                    case "Eat healthy":
                        textView.setText(spinnerSelectedText);
                        imageId = R.drawable.habit_5;
                        break;
                    case "Exercise":
                        textView.setText(spinnerSelectedText);
                        imageId = R.drawable.habit_6;
                        break;
                    case "Drink water":
                        textView.setText(spinnerSelectedText);
                        imageId = R.drawable.habit_2;
                        break;
                    case "Add custom habit":
                        textView.setText("");
                        textView.setHint("Custom");
                        imageId = R.drawable.ic_baseline_settings_24;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        builder.setView(view)
                .setTitle("New habit")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("app", "cancel habit");
                    }
                })
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GlobalModel.getInstance().addHabbit(new Habbit(textView.getText().toString(),imageId));
                        FragmentManager fm = getFragmentManager();
                        MainFragment fragm = (MainFragment)fm.findFragmentById(R.id.fragment_container);
                        fragm.updateUI();
                    }
                }
                );


        return builder.create();
    }



}
