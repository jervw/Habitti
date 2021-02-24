package com.example.habitti;

import android.content.Intent;
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

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("Tag", "OnCreate entered");
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //final ArrayList<HabbitsView> arrayList = new ArrayList<HabbitsView>();


        GlobalModel.getInstance().addHabbit(new Habbit("tupakointi",R.drawable.habit_1 ));
        GlobalModel.getInstance().addHabbit(new Habbit("Alkoholi", R.drawable.habit_3));

        //arrayList.add(new HabbitsView(R.drawable.ic_baseline_settings_24, GlobalModel.getInstance().tupakointi.getHabbitName(), "Scores: " + tupakointi.getOverallScore(), "Day streak: 2"));
        //arrayList.add(new HabbitsView(R.drawable.ic_baseline_settings_24, alkoholi.getHabbitName(), "10", "200"));

        HabbitsViewAdapter habbitsArrayAdapter = new HabbitsViewAdapter(getActivity(), GlobalModel.getInstance().getHabbitsView());

        ListView habbitsListView = (ListView) rootView.findViewById(R.id.listViewHabbits);

        habbitsListView.setAdapter(habbitsArrayAdapter);

        habbitsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                Intent showDetails = new Intent(getActivity(), HabbitDetails.class);
                showDetails.putExtra("EXTRA", i);
                startActivity(showDetails);
            }
        });
        return rootView;
    }


}
