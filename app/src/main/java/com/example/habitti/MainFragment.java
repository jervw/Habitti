package com.example.habitti;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    private SharedPreferences sharedPrefHabbits;
    private final String sharedPreferenceName = "shared preference";
    ObjectMapper objectMapper = new ObjectMapper();

    int[] clothesImages;
    int[] hairsImages;

    ListView habbitsListView;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        objectMapper.registerModule(new JodaModule());

        GlobalModel.getInstance().addHabbit(new Habbit("Testailu", R.drawable.ic_baseline_settings_24));
        loadHabbitData();
        updateUI();

        return rootView;

    }

    private void saveHabbitData() {
        sharedPrefHabbits = getActivity().getSharedPreferences(sharedPreferenceName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefHabbits.edit();
        String jsonHabbits;
        String jsonHabbitsView;

        ArrayList<Habbit> habbits = new ArrayList<>();
        try {
            jsonHabbitsView = objectMapper.writeValueAsString(GlobalModel.getInstance().getHabbitsView());
            editor.putString("Habbits list view", jsonHabbitsView);
        } catch (Exception e) {

        }
        try {
            jsonHabbits = objectMapper.writeValueAsString(GlobalModel.getInstance().getHabbitsList());
            editor.putString("Habbits list", jsonHabbits);
        } catch (Exception e) {

        }
        editor.apply();
        Log.d("Tag", "Saved");
    }

    public void loadHabbitData() {
        sharedPrefHabbits = getActivity().getSharedPreferences(sharedPreferenceName, Activity.MODE_PRIVATE);

        String jsonHabbits = sharedPrefHabbits.getString("Habbits list", null);
        String jsonHabbitsView = sharedPrefHabbits.getString("Habbits list view", null);
        //Type typeHabbits = new TypeToken<ArrayList<Habbit>>() {}.getType();
        //Type typeHabbitsView = new TypeToken<ArrayList<HabbitsView>>() {}.getType();
        try {
            ArrayList<Habbit> habbit = objectMapper.readValue(jsonHabbits, ArrayList.class);
            Log.d("Tag", habbit.toString());
            GlobalModel.getInstance().replaceListHabbits(habbit);
        } catch (Exception e) {
        }
        try {
            ArrayList<HabbitsView> habbitsViews = objectMapper.readValue(jsonHabbitsView, ArrayList.class);
            Log.d("Tag", habbitsViews.toString());
            GlobalModel.getInstance().replaceListHabbitsList(habbitsViews);
        } catch (Exception e) {
        }

    }


    private void updateUI() {
        HabbitsViewAdapter habbitsArrayAdapter = new HabbitsViewAdapter(getActivity(), GlobalModel.getInstance().getHabbitsView());
        habbitsListView = (ListView) rootView.findViewById(R.id.listViewHabbits);
        habbitsListView.setAdapter(habbitsArrayAdapter);
        saveHabbitData();


        habbitsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                Intent showDetails = new Intent(getActivity(), HabbitDetails.class);
                showDetails.putExtra("EXTRA", i);
                startActivity(showDetails);
            }
        });


        // GET NAME FROM SHARED PREFERENCE.XML:
        sharedPrefHabbits = this.getActivity().getSharedPreferences("shared preference", Context.MODE_PRIVATE);
        TextView textViewUserName = (TextView) rootView.findViewById(R.id.username);
        if (sharedPrefHabbits.contains("LastUserName")) {
            textViewUserName.setText(sharedPrefHabbits.getString("LastUserName", ""));
        }

        // GET CLOTHES FROM SHARED PREFERENCE.XML:
        clothesImages = new int[] {R.drawable.char_13, R.drawable.char_2, R.drawable.char_15, R.drawable.char_10, R.drawable.char_14};
        ImageView imageViewCharacterClothes = (ImageView) rootView.findViewById(R.id.userClothesImage);
        if (sharedPrefHabbits.contains("LastUserClothes")) {
            imageViewCharacterClothes.setImageResource(clothesImages[sharedPrefHabbits.getInt("LastUserClothes", -1)]);
        }

        // GET HAIRS FROM SHARED PREFERENCE.XML:
        hairsImages = new int[] {R.drawable.char_5, R.drawable.char_4, R.drawable.char_8, R.drawable.char_11, R.drawable.char_12, R.drawable.char_9};
        ImageView imageViewCharacterHairs = (ImageView) rootView.findViewById(R.id.userHairsImage);
        if (sharedPrefHabbits.contains("LastUserHairs")) {
            imageViewCharacterHairs.setImageResource(hairsImages[sharedPrefHabbits.getInt("LastUserHairs", -1)]);
        }

        // GET SEX FROM SHARED PREFERENCE.XML:
        ImageView imageViewCharacter = (ImageView) rootView.findViewById(R.id.userCharacterImage);
        int i = 0;
        if (sharedPrefHabbits.contains("LastUserSex")) {
            i = sharedPrefHabbits.getInt("LastUserSex", -1);
        }
        if (i == 1) {
            imageViewCharacter.setImageResource(R.drawable.char_6);
        } else if (i == 0) {
            imageViewCharacter.setImageResource(R.drawable.char_7);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onPause() {
        super.onPause();
        saveHabbitData();
    }


}
