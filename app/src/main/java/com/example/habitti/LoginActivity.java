package com.example.habitti;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * <h1>LoginActivity</h1>
 * LoginActivity program implements registration system in the app.
 * A program allows a user to input his name and select a character's appearance and sex.
 * Then it saves the user's data in the Shared Preferences. The program checks if the name
 * was already saved before, and in this case, it skips login_activity and moves on to the main_fragment.
 *
 * @author Anna Raevskaia
 */
public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPrefs;
    private String UserName = "UserName";
    private final String UserNameKey = "LastUserName";
    private int UserClothes;
    private final String UserClothesKey = "LastUserClothes";
    private int UserHairs;
    private final String UserHairsKey = "LastUserHairs";
    private int UserSex;
    private final String UserSexKey = "LastUserSex";

    private EditText SingUpName;
    private Button btnNext;

    private ImageView imageViewCharacter;
    private ImageView imageViewClothes;
    private Button btnChangeToFemale;
    private Button btnChangeToMale;
    private Button btnChangeClothes;
    private Button btnChangeHairs;
    private ImageView imageViewHairs;
    String singUpName;
    boolean devMode = false;

    int[] hairsImages;
    int[] clothesImages;
    private int currentImageClothes;
    private int currentImageHairs;
    private int currentCharacterSex;

    public static final String EXTRA_MESSAGE = "com.example.habitti";

    /**
     * The method calls checkIfNameNotEmpty() method, which checks if the user's name already existed in Shared Preferences.
     * That method checks if the name was already created, and in this case starts the next activity.
     * By clicking "next button" the program saves user's data in Shared Preferences and calls checkUserName() method.
     * There is another buttons as well: for changing character's sex, clothes ans hairs.
     * @param savedInstanceState is a reference to a Bundle object. It is passed into the onCreate method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        // GO TO THE MAIN ACTIVITY, IF THE NAME WAS ALREADY CREATED:
        checkIfNameNotEmpty();


        SingUpName = (EditText) findViewById(R.id.usernameField);
        btnNext = (Button) findViewById(R.id.buttonNext);

        // NEXT BUTTON:
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPrefs = getSharedPreferences("shared preference", Context.MODE_PRIVATE);
                UserName = sharedPrefs.getString(UserNameKey, "0");

                UserClothes = sharedPrefs.getInt(UserClothesKey, 0);
                UserHairs = sharedPrefs.getInt(UserHairsKey, 0);
                UserSex = sharedPrefs.getInt(UserSexKey, 0);


                // CHECKING FOR THE USER'S INPUT NAME:
                singUpName = SingUpName.getText().toString();
                checkUserName();


                // SAVE USER DATA:
                SaveManager.getInstance().saveCharacterImages(LoginActivity.this, SingUpName.getText().toString(), UserNameKey, currentImageClothes, UserClothesKey,
                        currentImageHairs, UserHairsKey, currentCharacterSex, UserSexKey);
            }
        });


        // CHARACTER SEX BUTTON:
        imageViewCharacter = (ImageView) findViewById(R.id.imageViewCharacter);
        btnChangeToFemale = (Button) findViewById(R.id.btnFemale);

        // Default sex displayed (male)
        imageViewCharacter.setImageResource(R.drawable.char_7);
        currentCharacterSex = 0;

        btnChangeToFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewCharacter.setImageResource(R.drawable.char_6);
                currentCharacterSex = 1;
            }
        });

        btnChangeToMale = (Button) findViewById(R.id.btnMale);
        btnChangeToMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewCharacter.setImageResource(R.drawable.char_7);
                currentCharacterSex = 0;
            }
        });


        // CHARACTER CLOTHES BUTTON:
        imageViewClothes = (ImageView) findViewById(R.id.imageViewClothes);
        btnChangeClothes = (Button) findViewById(R.id.btnChangeClothes);
        clothesImages = new int[] {R.drawable.char_13, R.drawable.char_14, R.drawable.char_2};

        btnChangeClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentImageClothes++;
                currentImageClothes = currentImageClothes % clothesImages.length;
                imageViewClothes.setImageResource(clothesImages[currentImageClothes]);
            }
        });


        // CHARACTER HAIRS BUTTON:
        imageViewHairs = (ImageView) findViewById(R.id.imageViewHairs);
        btnChangeHairs = (Button) findViewById(R.id.btnChangeHairs);
        hairsImages = new int[]{R.drawable.char_5, R.drawable.char_4, R.drawable.char_8, R.drawable.char_11, R.drawable.char_12, R.drawable.char_9};

        btnChangeHairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentImageHairs++;
                currentImageHairs = currentImageHairs % hairsImages.length;
                imageViewHairs.setImageResource(hairsImages[currentImageHairs]);
            }
        });

    }

    /**
     * This method checks the user's input text. In case if the input was empty, it writes "What is your name?" on the screen.
     * The program sets dev mode boolean is true, if inputted text was "dev".
     * Else if input is not empty or contain "dev", the program starts the next activity.
     */
    // CHECKING FOR THE USER'S INPUT NAME:
    private void checkUserName() {

        switch (singUpName) {
            case ("dev"): {
                Log.d("login activity", "dev mode");
                devMode = true;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case (""): {
                Log.d("login activity", "empty name");
                Toast toast = Toast.makeText(getApplicationContext(), "What is your name?", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            default: {
                Log.d("login activity", "name is " + singUpName);
                devMode = false;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    /**
     * The method checks if the user's name already existed in Shared Preferences.
     * If name was already created, the program starts the next activity.
     */
    // GO TO THE MAIN FRAGMENT, IF THE NAME WAS ALREADY CREATED:
    private void checkIfNameNotEmpty() {

        sharedPrefs = this.getSharedPreferences("shared preference", MODE_PRIVATE);
        String name;

        if (sharedPrefs.contains("LastUserName")) {
            name = sharedPrefs.getString("LastUserName", "");

            if (!name.equals("")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    /**
     * OnPause() method was created for checking pause state in app.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LOGIN", "OnPause() LoginActivity");
    }
}
