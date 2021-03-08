package com.example.habitti;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveManager {

    private static final SaveManager instance = new SaveManager();

    public static SaveManager getInstance() {return instance;}

    private SaveManager() {
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("shared preference", Context.MODE_PRIVATE);
    }

    // SAVE NAME AND CLOTHES IMAGES, USED IN LOGIN ACTIVITY
    public void saveCharacterImages(Context context, String SingUpName, String UserNameKey, int currentImageClothes, String UserClothesKey,
                                    int currentImageHairs, String UserHairsKey, int currentCharacterSex, String UserSexKey) {
        SharedPreferences sharedPrefs = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(UserNameKey, SingUpName);

        editor.putInt(UserClothesKey, currentImageClothes); // Tallentaa int currentImageClothes
        editor.putInt(UserHairsKey, currentImageHairs);
        editor.putInt(UserSexKey, currentCharacterSex);

        editor.commit();
    }

    // SAVE JUST CLOTHES IMAGES, USED IN SHOP
    public void saveCharacterImages2(Context context, int currentImageClothes, String UserClothesKey) {
        SharedPreferences sharedPrefs = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putInt(UserClothesKey, currentImageClothes);

        editor.commit();
    }




}
