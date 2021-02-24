package com.example.habitti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    private EditText SingUpName;
    private Button btnNext;

    private ImageView imageViewCharacter;
    private ImageView imageViewClothes;
    private Button btnChangeToFemale;
    private Button btnChangeToMale;
    private Button btnChangeClothes;
    private Button btnChangeHairs;
    private ImageView imageViewHairs;

    int[] hairsImages;
    int[] clothesImages;
    private int currentImageClothes;
    private int currentImageHairs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        SingUpName = (EditText) findViewById(R.id.editTextSingUpName);
        btnNext = (Button) findViewById(R.id.btnSingUp);

        // NEXT BUTTON:
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Vaihta käyttäjän nimi ja profiilikuva
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        // CHARACTER SEX:
        imageViewCharacter = (ImageView) findViewById(R.id.imageViewCharacter);

        btnChangeToFemale = (Button) findViewById(R.id.btnFemale);
        btnChangeToFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewCharacter.setImageResource(R.drawable.char_6);
            }
        });

        btnChangeToMale = (Button) findViewById(R.id.btnMale);
        btnChangeToMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewCharacter.setImageResource(R.drawable.char_7);
            }
        });


        // CHARACTER CLOTHES:
        imageViewClothes = (ImageView) findViewById(R.id.imageViewClothes);
        btnChangeClothes = (Button) findViewById(R.id.btnChangeClothes);
        clothesImages = new int[] {R.drawable.char_2, R.drawable.char_3};

        btnChangeClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentImageClothes++;
                currentImageClothes = currentImageClothes % clothesImages.length;
                imageViewClothes.setImageResource(clothesImages[currentImageClothes]);
            }
        });


        // CHARACTER HAIRS:
        imageViewHairs = (ImageView) findViewById(R.id.imageViewHairs);
        btnChangeHairs = (Button) findViewById(R.id.btnChangeHairs);
        hairsImages = new int[] {R.drawable.char_5, R.drawable.char_4, R.drawable.char_1};

        btnChangeHairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentImageHairs++;
                currentImageHairs = currentImageHairs % hairsImages.length;
                imageViewHairs.setImageResource(hairsImages[currentImageHairs]);
            }
        });

    }
}
