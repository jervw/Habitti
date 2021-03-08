package com.example.habitti;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * <h1>RewardsActivity</h1>
 * RewardsActivity program implements rewards system in the app.
 *
 * @author Anna Raevskaia
 */
public class RewardsActivity extends Activity {

    private SharedPreferences sharedPrefHabbits;
    private final String UserClothesKey = "LastUserClothes";
    private final String UserHairsKey = "LastUserHairs";

    int[] clothesImages;
    int[] hairsImages;

    GridView gridview;

    // NAMES AND IMAGES FOR ITEMS IN THE SHOP:
    String[] itemNames = {"Locked 2 lvl", "Locked 3 lvl", "Locked 4 lvl", "Locked 6 lvl", "Locked8 lvl", "Locked 10 lvl"};
    int[] itemImages = {R.drawable.shop_item_1, R.drawable.shop_item_2, R.drawable.shop_item_3, R.drawable.shop_item_4, R.drawable.shop_item_6, R.drawable.shop_item_5};

    String selectedNameString;
    int selectedImage;

    int currentImagelothes1 = 0;

    int characterLvl;

    int index = 0;


    /**
     * The OnCreate() method calls methods of the class in the right order for the program to work.
     * Firstly it sets shop_pop_up_window layout in the full screen.
     * Then it calls updateUI() method, that sets the current character's appearance.
     * Finds grid view by id, creates new RewardsItemAdapter, and sets it to grid view.
     * Gets user's level from the singleton GlobalModel class. It's also possible to set your level to text program working.
     * Calls changeItemsText() method, that changes the default text of items to empty.
     * Change character's clothes, if the user was clicked an item in the grid view.
     * Closes shop/rewards window and opens the main fragment, if closeBtn was clicked.
     * Close button also saves the user's data of images if the user's level doesn't equal 1.
     * This is for the reason, that 1. level characters can't change their appearance until they reach 2. level.
     * @param savedInstanceState is a reference to a Bundle object. It is passed into the onCreate method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SHOP", "Shop onCreate()");
        setContentView(R.layout.rewards_window);

        // FULL SCREEN POP UP WINDOW:
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width), (int) (height));


        // SET CURRENT CHARACTER'S APPEARANCE:
        updateUI();


        // GRID VIEW:
        gridview = (GridView) findViewById(R.id.GridViewId);
        RewardsItemAdapter itemAdapter = new RewardsItemAdapter(itemNames, itemImages, this);
        gridview.setAdapter(itemAdapter);


        // GET USER LEVEL FROM GLOBAL MODEL:
        characterLvl = GlobalModel.getInstance().getUserLevel();

        // SET TEST LEVEL:
        //characterLvl = 10;


        // SET EMPTY TEXT, IF USER'S LEVEL IS SUITABLE:
        changeItemsText();


        // CHANGE CLOTHES THEN ITEM WAS CLICKED:
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("SHOP", "On item clicked: " + "index = " + i + ", long = " + l);

                index = i;

                selectedNameString = itemNames[i];
                selectedImage = itemImages[i];

                changeClothes();
            }
        });


        // CLOSE SHOP WINDOW AND SAVE APPEARANCE:
        Button closeBtn = (Button) findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RewardsActivity.this, MainActivity.class);
                startActivity(intent);

                //SAVE USER DATA:
                if(characterLvl != 1) {
                    SaveManager.getInstance().saveCharacterImages2(RewardsActivity.this, currentImagelothes1, UserClothesKey);
                }
            }
        });

    }


    /**
     * This method checks that level user has and that item he was clicked.
     * The program gets index of clicked item in the grid view in the onCreate() method.
     * If the user's level and selected item match with any of the combinations - clothes
     * can be changed - the program starts the change the clothes method.
     */
    // CLOTHES CHANGING BASED ON LEVEL AND INDEX (POSITION) OF ITEM IN GRID VIEW:
    private void changeClothes() {
        if ((index == 0) && (characterLvl == 1)) {
            // DO NOTHING
        } else if ((characterLvl == 2) && (index == 0 ) && (index != 1)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 2. lvl) " + selectedNameString + "index = " + index + ", level = " + characterLvl);
        } else if ((characterLvl == 3) && (index == 0) || (index == 1) && (characterLvl == 3)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 3. lvl) " + selectedNameString + "index = " + index + ", level = " + characterLvl);
        } else if ((index == 0) && (characterLvl == 4) || (index == 1) && (characterLvl == 4) || (index == 2) && (characterLvl == 4)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 4. lvl) " + selectedNameString + "index = " + index + ", level = " + characterLvl);
        } else if ((index == 0) && (characterLvl == 6) || (index == 1) && (characterLvl == 6) || (index == 2) && (characterLvl == 6) || (index == 3) && (characterLvl == 6)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 4. lvl) " + selectedNameString + "index = " + index + ", level = " + characterLvl);
        } else if ((index == 0) && (characterLvl == 8) || (index == 1) && (characterLvl == 8) || (index == 2) && (characterLvl == 8) || (index == 3) && (characterLvl == 8) || (index == 4) && (characterLvl == 8)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 4. lvl) " + selectedNameString + "index = " + index + ", level = " + characterLvl);
        } else if ((index == 0) && (characterLvl == 10) || (index == 1) && (characterLvl == 10) || (index == 2) && (characterLvl == 10) || (index == 3) && (characterLvl == 10) || (index == 4) && (characterLvl == 10) || (index == 5) && (characterLvl == 10)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 4. lvl) " + selectedNameString + "index = " + index + ", level = " + characterLvl);
        }
    }


    /**
     * The method changes user's clothes images in the activity.
     */
    // CHANGE CLOTHES IMAGES:
    private void changeUserClothes() {
        int selectedImage = itemImages[index];

        ImageView imageViewCharacterClothes1 = (ImageView) findViewById(R.id.userClothesImage);

        clothesImages = new int[] {R.drawable.char_13, R.drawable.char_14, R.drawable.char_2, R.drawable.char_10, R.drawable.char_16, R.drawable.char_15};

        selectedImage++;
        selectedImage = selectedImage % clothesImages.length;
        imageViewCharacterClothes1.setImageResource(clothesImages[selectedImage]);

        // SET A INDEX FOR THE SELECTED ITEM:
        currentImagelothes1 = index;

    }


    /**
     * If user's level is high enough, the program sets an empty text to text view in the item card.
     * Else if the user doesn't have the needed level, the program will write, that level is required for theirs.
     */
    // CHANGE TEXT TO EMPTY IN ITEMS CARDS:
    private void changeItemsText() {

        Log.d("SHOP", "User level is " + characterLvl);

        // CHANGE THE TEXT OF ITEM, IF CHARACTER LEVEL IS ...:
        if (characterLvl == 2) {
            itemNames[0] = "";
        } else if(characterLvl == 3) {
            itemNames[0] = "";
            itemNames[1] = "";
        } else if(characterLvl == 4 || characterLvl == 5) {
            itemNames[0] = "";
            itemNames[1] = "";
            itemNames[2] = "";
        } else if(characterLvl == 6 || characterLvl == 7) {
            itemNames[0] = "";
            itemNames[1] = "";
            itemNames[2] = "";
            itemNames[3] = "";
        } else if(characterLvl == 8 || characterLvl == 9) {
            itemNames[0] = "";
            itemNames[1] = "";
            itemNames[2] = "";
            itemNames[3] = "";
            itemNames[4] = "";
        } else if(characterLvl == 10) {
            itemNames[0] = "";
            itemNames[1] = "";
            itemNames[2] = "";
            itemNames[3] = "";
            itemNames[4] = "";
            itemNames[5] = "";
        }
    }


    /**
     * This method loads character's data from Shared Preferences.
     */
    // LOAD CHARACTER IMAGES THEN ACTIVITY STARTS:
    private void updateUI() {
        Log.d("SHOP", "updateUI()");

        sharedPrefHabbits = getSharedPreferences("shared preference", Activity.MODE_PRIVATE);

        // GET CLOTHES FROM SHARED PREFERENCE.XML:
        clothesImages = new int[] {R.drawable.char_13, R.drawable.char_14, R.drawable.char_2, R.drawable.char_10, R.drawable.char_16, R.drawable.char_15};
        ImageView imageViewCharacterClothes1 = (ImageView) findViewById(R.id.userClothesImage);
        if (sharedPrefHabbits.contains("LastUserClothes")) {
            imageViewCharacterClothes1.setImageResource(clothesImages[sharedPrefHabbits.getInt("LastUserClothes", -1)]);
        }

        // GET HAIRS FROM SHARED PREFERENCE.XML:
        hairsImages = new int[] {R.drawable.char_5, R.drawable.char_4, R.drawable.char_8, R.drawable.char_11, R.drawable.char_12, R.drawable.char_9};
        ImageView imageViewCharacterHairs1 = (ImageView) findViewById(R.id.userHairsImage);
        if (sharedPrefHabbits.contains("LastUserHairs")) {
            imageViewCharacterHairs1.setImageResource(hairsImages[sharedPrefHabbits.getInt("LastUserHairs", -1)]);
        }

        // GET SEX FROM SHARED PREFERENCE.XML:
        ImageView imageViewCharacter1 = (ImageView) findViewById(R.id.userCharacterImage);
        int i = 0;
        if (sharedPrefHabbits.contains("LastUserSex")) {
            i = sharedPrefHabbits.getInt("LastUserSex", -1);
        }
        if (i == 1) {
            imageViewCharacter1.setImageResource(R.drawable.char_6);
        } else if (i == 0) {
            imageViewCharacter1.setImageResource(R.drawable.char_7);
        }
        Log.d("SHOP", "updateUI() end ");
    }

}
