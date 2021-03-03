package com.example.habitti;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class AddNewHabbits extends AppCompatActivity {
    TextView selection;
    Spinner spinnerHabbits;
    EditText customHabbit;
    Button buttonAdd;
    int imageId;
    String spinnerSelectedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_habbit);

        selection = findViewById(R.id.textViewSelection);
        customHabbit = findViewById(R.id.editTextCustomHabbit);
        spinnerHabbits = (Spinner) findViewById(R.id.spinnerHabbits);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.habbits_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHabbits.setAdapter(adapter);

        spinnerHabbits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> spinnerHabbits, View view, int pos, long id) {
                spinnerSelectedText = spinnerHabbits.getItemAtPosition(pos).toString();
                switch (spinnerSelectedText) {
                    case "No smoking":
                        selection.setText(spinnerSelectedText + " selected");
                        imageId = R.drawable.habit_1;
                        break;
                    case "No alcohol":
                        selection.setText(spinnerSelectedText + " selected");
                        imageId = R.drawable.habit_3;
                        break;
                    case "No sugar":
                        selection.setText(spinnerSelectedText + " selected");
                        imageId = R.drawable.habit_4;
                        break;
                    case "Eat healthy":
                        selection.setText(spinnerSelectedText + " selected");
                        imageId = R.drawable.habit_5;
                        break;
                    case "Exercise":
                        selection.setText(spinnerSelectedText + " selected");
                        imageId = R.drawable.habit_6;
                        break;
                    case "Drink water":
                        selection.setText(spinnerSelectedText + " selected");
                        imageId = R.drawable.habit_2;
                        break;
                    case "Add custom habit":
                        selection.setText(spinnerSelectedText + " selected");
                        imageId = R.drawable.ic_baseline_settings_24;
                        break;
                }
                if (spinnerHabbits.getItemAtPosition(pos).toString().equals("Add custom habit")) {
                    customHabbit.setVisibility(View.VISIBLE);
                } else {
                    customHabbit.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selection.setText("Nothing selected");
            }
        });
    }

    public void onClickAddHabbit(View view) {
        if (customHabbit.getText().toString().isEmpty() && spinnerSelectedText.equals("Add custom habit")) {
            selection.setText("Please add name for your habit");
        } else if (!customHabbit.getText().toString().isEmpty() && spinnerSelectedText.equals("Add custom habit")) {
            GlobalModel.getInstance().addHabbit(new Habbit(customHabbit.getText().toString(), imageId));
            finish();
        } else if (!spinnerSelectedText.isEmpty()) {
            Log.d("Tag", spinnerSelectedText + imageId);
            GlobalModel.getInstance().addHabbit(new Habbit(spinnerSelectedText,imageId));

            finish();
        }

    }
}