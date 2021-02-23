package com.example.habitti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //final ArrayList<HabbitsView> arrayList = new ArrayList<HabbitsView>();


        GlobalModel.getInstance().addHabbit(new Habbit("tupakointi",R.drawable.smoking ));

        //arrayList.add(new HabbitsView(R.drawable.ic_baseline_settings_24, GlobalModel.getInstance().tupakointi.getHabbitName(), "Scores: " + tupakointi.getOverallScore(), "Day streak: 2"));
        //arrayList.add(new HabbitsView(R.drawable.ic_baseline_settings_24, alkoholi.getHabbitName(), "10", "200"));

        HabbitsViewAdapter habbitsArrayAdapter = new HabbitsViewAdapter(getActivity(), GlobalModel.getInstance().getHabbitsView());

        ListView habbitsListView = (ListView) rootView.findViewById(R.id.listViewHabbits);

        habbitsListView.setAdapter(habbitsArrayAdapter);
        return rootView;
    }


}
