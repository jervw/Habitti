package com.example.habitti;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * <h1>Creating new habits</h1>
 * The intention of AddHabitDialog is to take advantage of the Android Dialog system.
 * the purpose is specifically to build a Dialog window, using the new_habit_dialog.xml file.
 * AddHabitDialog methods cannot be used to display the dialog.
 * @author Jere Vuola
 */

public class AddHabitDialog extends AppCompatDialogFragment {

    private int imageId;
    private int[] spinnerImages;
    private String spinnerSelectedImage;
    private String[] spinnerTitles;



    /**
     * Method onCreateDialog used to build the Dialog window
     * @return configured AlertDialog builder
     * @param savedInstanceState
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_habit_dialog, null);

        /**Attaching specific habit images and descriptions to arrays.*/
        spinnerImages = new int[]{R.drawable.habit_1, R.drawable.habit_2, R.drawable.habit_3, R.drawable.habit_4, R.drawable.habit_5, R.drawable.habit_6, R.drawable.habit_7, R.drawable.habit_8};
        spinnerTitles = new String[]{"No smoking", "Drink water", "No alcohol", "No sugar", "Eat healthy", "Exercise", "Good habit", "Bad habit"};

        TextView textView = (TextView) view.findViewById(R.id.habbitName);
        RadioGroup radio = (RadioGroup) view.findViewById(R.id.radioGroup);
        Spinner habitSpinner = (Spinner) view.findViewById(R.id.spinnerHabbits);

        /**Using our custom spinner adapter to list images properly.*/
        CustomSpinnerAdapter customAdapter = new CustomSpinnerAdapter(getContext(), spinnerImages, spinnerTitles);
        habitSpinner.setAdapter(customAdapter);


        /**Adding new habit image according selected image*/
        habitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> spinnerHabbits, View view, int pos, long id) {
                spinnerSelectedImage = customAdapter.getItemName(pos);//(habitSpinner.getItemAtPosition(pos).toString());
                switch (spinnerSelectedImage) {
                    case "No smoking":
                        imageId = R.drawable.habit_1;
                        break;
                    case "No alcohol":
                        imageId = R.drawable.habit_3;
                        break;
                    case "No sugar":
                        imageId = R.drawable.habit_4;
                        break;
                    case "Eat healthy":
                        imageId = R.drawable.habit_5;
                        break;
                    case "Exercise":
                        imageId = R.drawable.habit_6;
                        break;
                    case "Drink water":
                        imageId = R.drawable.habit_2;
                        break;
                    case "Good habit":
                        imageId = R.drawable.habit_7;
                        break;
                    case "Bad habit":
                        imageId = R.drawable.habit_8;
                        break;
                }
            }

            /**onNothingSelected() is required method of spinner listener and cannot be removed
             * @param parent*/
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}});


        /**builder.setView configures
         * 1. Configuring dialog title text
         * 2. Adds negative button listener with 'cancel' text, button only closes the dialog.
         * 3. Adds positive button listener with 'add' text, button checks fields and refreshes current fragment** */
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
                                int selectedType = radio.getCheckedRadioButtonId();
                                RadioButton selectedRadioButton = (RadioButton) view.findViewById(selectedType);
                                String nameCheck = textView.getText().toString();
                                if (nameCheck.equals("")) {
                                    nameCheck = customAdapter.getItemName(habitSpinner.getSelectedItemPosition());
                                }
                                GlobalModel.getInstance().addHabbit(new Habit(nameCheck, selectedRadioButton.getText().toString(), imageId));
                                FragmentManager fm = getFragmentManager();
                                MainFragment fragm = (MainFragment) fm.findFragmentById(R.id.fragment_container);
                                fragm.updateUI();
                            }
                        }
                );
        return builder.create();
    }
}


