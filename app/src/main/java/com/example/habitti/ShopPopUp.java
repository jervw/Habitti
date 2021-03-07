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

import androidx.appcompat.app.AppCompatActivity;

/**
 * <h1>Shop / Rewards</h1>
 * ShopPopUp program implements rewards system in the app.
 * @author Anna Raevskaia
 */
public class ShopPopUp extends AppCompatActivity {

    private SharedPreferences sharedPrefHabbits;
    private final String UserClothesKey = "LastUserClothes";
    private final String UserHairsKey = "LastUserHairs";

    int[] clothesImages;
    int[] hairsImages;

    GridView gridview;

    // CREATE A NEW ITEM IN THE SHOP:
    String[] itemNames = {"Locked 2 lvl", "Locked 3 lvl", "Locked 4 lvl", "Locked 6 lvl", "Locked8 lvl", "Locked 10 lvl"};
    int[] itemImages = {R.drawable.shop_item_1, R.drawable.shop_item_2, R.drawable.shop_item_3, R.drawable.shop_item_4, R.drawable.shop_item_6, R.drawable.shop_item_5};

    String selectedNameString;
    int selectedImage;
    int currentImagelothes1 = 0;
    int characterLvl;
    int a = 0;


    /**
     * 1: Sets shop_pop_up_window layout.
     * 2: Calls updateUI() method, that set current character's appearance.
     * 3: Finds grid view by id, create new ShopItemAdapter and set it to grid view.
     * 4: Gets user's level from the singleton GlobalModel class.
     * 5: Calls changeItemsText() method, that change default text of items to empty.
     * @see #changeItemsText().
     * The button closeBtn closes shop/rewards window and opens the main fragment.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        ShopItemAdapter itemAdapter = new ShopItemAdapter(itemNames, itemImages, this);
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

                a = i;

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

                Intent intent = new Intent(ShopPopUp.this, MainActivity.class);
                startActivity(intent);

                //SAVE USER DATA:
                Save.getInstance().saveCharacterImages2(ShopPopUp.this, currentImagelothes1, UserClothesKey);
            }
        });

    }


    /**
     *
     */
    // CLOTHES CHANGING BASED ON LEVEL AND INDEX (POSITION) OF ITEM IN GRID VIEW:
    private void changeClothes() {
        if ((a == 0) && (characterLvl == 1)) {
            // DO NOTHING
        } else if ((characterLvl == 2) && (a == 0 ) && (a != 1)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 2. lvl) " + selectedNameString + "index = " + a + ", level = " + characterLvl);
        } else if ((characterLvl == 3) && (a == 0) || (a == 1) && (characterLvl == 3)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 3. lvl) " + selectedNameString + "index = " + a + ", level = " + characterLvl);
        } else if ((a == 0) && (characterLvl == 4) || (a == 1) && (characterLvl == 4) || (a == 2) && (characterLvl == 4)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 4. lvl) " + selectedNameString + "index = " + a + ", level = " + characterLvl);
        } else if ((a == 0) && (characterLvl == 6) || (a== 1) && (characterLvl == 6) || (a == 2) && (characterLvl == 6) || (a == 3) && (characterLvl == 6)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 4. lvl) " + selectedNameString + "index = " + a + ", level = " + characterLvl);
        } else if ((a == 0) && (characterLvl == 8) || (a == 1) && (characterLvl == 8) || (a == 2) && (characterLvl == 8) || (a == 3) && (characterLvl == 8) || (a == 4) && (characterLvl == 8)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 4. lvl) " + selectedNameString + "index = " + a + ", level = " + characterLvl);
        } else if ((a == 0) && (characterLvl == 10) || (a == 1) && (characterLvl == 10) || (a == 2) && (characterLvl == 10) || (a == 3) && (characterLvl == 10) || (a == 4) && (characterLvl == 10) || (a == 5) && (characterLvl == 10)) {
            changeUserClothes();
            Log.d("SHOP", "On item clicked (item for 4. lvl) " + selectedNameString + "index = " + a + ", level = " + characterLvl);
        }
    }


    /**
     * The method changes user's clothes images in the shop_pop_up_window activity.
     *
     */
    // CHANGE CLOTHES IMAGES:
    private void changeUserClothes() {
        int selectedImage = itemImages[a];

        ImageView imageViewCharacterClothes1 = (ImageView) findViewById(R.id.userClothesImage1);

        clothesImages = new int[] {R.drawable.char_13, R.drawable.char_14, R.drawable.char_2, R.drawable.char_10, R.drawable.char_16, R.drawable.char_15};

        selectedImage++;
        selectedImage = selectedImage % clothesImages.length;
        imageViewCharacterClothes1.setImageResource(clothesImages[selectedImage]);


        // SET A NEW INDEX FOR THE SELECTED ITEM, THAT BASED ON INT ARRAY OF CLOTHES IN MAIN FRAGMENT:
        currentImagelothes1 = a;
        if(currentImagelothes1 == 2) {
            currentImagelothes1 = 1; // mag
        } else if (currentImagelothes1 == 0) {
            currentImagelothes1 = 0; // soldier
        } else if (currentImagelothes1 == 1) {
            currentImagelothes1 = 4; // sci fi soldier
        } else if (currentImagelothes1 == 3) {
            currentImagelothes1 = 3; // dress
        } else if (currentImagelothes1 == 5) {
            currentImagelothes1 = 5; // best
        } else if (currentImagelothes1 == 4) {
            currentImagelothes1 = 2; // mag with beard
        }

    }




    /**
     * If user's level is high enough, the program sets an empty text to text view in the item card.
     * Else if user doesn't have needed level, in the card will written, that level is needed.
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
     *
     */
    // LOAD CHARACTER IMAGES THEN ACTIVITY STARTS:
    private void updateUI() {
        Log.d("SHOP", "updateUI()");

        sharedPrefHabbits = getSharedPreferences("shared preference", Activity.MODE_PRIVATE);

        // GET CLOTHES FROM SHARED PREFERENCE.XML:
        clothesImages = new int[] {R.drawable.char_13, R.drawable.char_2, R.drawable.char_15, R.drawable.char_10, R.drawable.char_14, R.drawable.char_16};
        ImageView imageViewCharacterClothes1 = (ImageView) findViewById(R.id.userClothesImage1);
        if (sharedPrefHabbits.contains("LastUserClothes")) {
            imageViewCharacterClothes1.setImageResource(clothesImages[sharedPrefHabbits.getInt("LastUserClothes", -1)]);
        }

        // GET HAIRS FROM SHARED PREFERENCE.XML:
        hairsImages = new int[] {R.drawable.char_5, R.drawable.char_4, R.drawable.char_8, R.drawable.char_11, R.drawable.char_12, R.drawable.char_9};
        ImageView imageViewCharacterHairs1 = (ImageView) findViewById(R.id.userHairsImage1);
        if (sharedPrefHabbits.contains("LastUserHairs")) {
            imageViewCharacterHairs1.setImageResource(hairsImages[sharedPrefHabbits.getInt("LastUserHairs", -1)]);
        }

        // GET SEX FROM SHARED PREFERENCE.XML:
        ImageView imageViewCharacter1 = (ImageView) findViewById(R.id.userCharacterImage1);
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
