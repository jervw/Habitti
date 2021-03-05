package com.example.habitti;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveLoad {

    private static final SaveLoad instance = new SaveLoad();

    public static SaveLoad getInstance() {return instance;}

    private SaveLoad() {
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

    public String loadCharacterName(Context context, String SingUpName) {
        SharedPreferences sharedPrefs = getSharedPreferences(context);
        if (sharedPrefs.contains("LastUserName")) {
            SingUpName = sharedPrefs.getString("LastUserName", "");
        }
        return SingUpName;
    }

    /*public void saveHabbitData(Context context, ArrayList arrayList, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        //String jsonHabbits = gson.toJson(GlobalModel.getInstance().GetHabbitsList());
        String json = gson.toJson(arrayList);
        editor.putString("Habbits list", json);
        editor.apply();
    }

    public ArrayList loadHabbitData(Context context, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Habbits list", null);
        Type type = new TypeToken<ArrayList<Habbit>>() {}.getType();
        ArrayList habbitsView = gson.fromJson(json, type);

        if (habbitsView == null) {
            habbitsView = new ArrayList();
        }

        return habbitsView;
    }*/
}
