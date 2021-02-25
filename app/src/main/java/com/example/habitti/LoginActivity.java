package com.example.habitti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText SingUpName;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //VÄLIAIKEINEN SIJOITUSPAIKKA
        GlobalModel.getInstance().addHabbit(new Habbit("tupakointi",R.drawable.habit_1 ));
        GlobalModel.getInstance().addHabbit(new Habbit("Alkoholi", R.drawable.habit_3));

        SingUpName = (EditText) findViewById(R.id.editTextSingUpName);
        btnNext = (Button) findViewById(R.id.btnSingUp);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Vaihta käyttäjän nimi ja profiilikuva
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
