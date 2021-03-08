package com.example.habitti;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <h1>SaveManager</h1>
 * SaveManager is a singleton class. It implements saving system in the app.
 *
 * @author Anna Raevskaia
 */
public class SaveManager {

    private static final SaveManager instance = new SaveManager();

    public static SaveManager getInstance() {return instance;}

    /**
     * Constructor of the class.
     */
    private SaveManager() {
    }

    /**
     * @param context get context
     * @return context
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("shared preference", Context.MODE_PRIVATE);
    }

    /**
     * This method is used to save user current clothes, hairs, sex images and name.
     * @param context               get context
     * @param SingUpName            get user's current name
     * @param UserNameKey           get user's name key
     * @param currentImageClothes   get user's current clothes image
     * @param UserClothesKey        get user's clothes key
     * @param currentImageHairs     get user's current hairs image
     * @param UserHairsKey          get user's hairs key
     * @param currentCharacterSex   get user's current sex (male/female) image
     * @param UserSexKey            get user's sex key
     */
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

    /**
     * This method is used to save user current clothes image. It is created for using in rewards activity.
     * @param context               get context
     * @param currentImageClothes   get user's current clothes image
     * @param UserClothesKey        get user's clothes key
     */
    // SAVE JUST CLOTHES IMAGES, USED IN SHOP
    public void saveCharacterImages2(Context context, int currentImageClothes, String UserClothesKey) {
        SharedPreferences sharedPrefs = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putInt(UserClothesKey, currentImageClothes);

        editor.commit();
    }




}
