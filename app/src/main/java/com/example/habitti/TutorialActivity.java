package com.example.habitti;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
/**
 * <h1>Tutorial view</h1>
 * This class does nothing but add an xml file to the activity
 * @author Santeri Hytönen
 */
public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_use);
    }
}
