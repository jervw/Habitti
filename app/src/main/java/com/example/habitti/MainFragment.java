package com.example.habitti;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

public class MainFragment extends Fragment {

    private SharedPreferences sharedPrefHabbits;
    private final String sharedPreferenceName = "shared preference";
    ListView habbitsListView;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);



        updateUI();

        return rootView;
    }
    
    /*private void saveHabbitData() {
        sharedPrefHabbits = getSharedPreferences(sharedPreferenceName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefHabbits.edit();
        Gson gson = new Gson();
        String jsonHabbits = gson.toJson(GlobalModel.getInstance().GetHabbitsList());
        String jsonHabbitsView = gson.toJson(GlobalModel.getInstance().getHabbitsView());
        editor.putString("Habbits list", jsonHabbits);
        editor.putString("Habbits list view", jsonHabbitsView);
        editor.apply();
    }

    private void loadHabbitData() {
        sharedPrefHabbits = getSharedPreferences(sharedPreferenceName, Activity.MODE_PRIVATE);

    }

     */

    private void updateUI() {
        HabbitsViewAdapter habbitsArrayAdapter = new HabbitsViewAdapter(getActivity(), GlobalModel.getInstance().getHabbitsView());
        habbitsListView = (ListView) rootView.findViewById(R.id.listViewHabbits);
        habbitsListView.setAdapter(habbitsArrayAdapter);

        habbitsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                Intent showDetails = new Intent(getActivity(), HabbitDetails.class);
                showDetails.putExtra("EXTRA", i);
                startActivity(showDetails);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();

    }


}
