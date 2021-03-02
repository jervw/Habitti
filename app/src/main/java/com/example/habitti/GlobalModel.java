package com.example.habitti;


import android.content.SharedPreferences;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

//Singleton-class
public class GlobalModel {
    private static final GlobalModel ourInstance = new GlobalModel();
    private ArrayList<Habbit> habbits = null;
    private ArrayList<HabbitsView> habbitsView = null;
    private ArrayList<Habbit> habbitsTesting = null;
    private ArrayList<HabbitsView> habbitsViewsTesting = null;

    public  static GlobalModel getInstance() {
        return ourInstance;
    }

    private GlobalModel() {
        habbits = new ArrayList<Habbit>();
        habbitsView = new ArrayList<HabbitsView>();
        habbitsTesting = new ArrayList<Habbit>();
        habbitsViewsTesting = new ArrayList<HabbitsView>();
    }

    public void addHabbit(Habbit habbit) {
        habbits.add(habbit);
        addListView(habbit);
    }

    public ArrayList<Habbit> getHabbitsList() {
        return this.habbits;
    }

    public ArrayList<HabbitsView> getHabbitsView() {
        return this.habbitsView;
    }

    public void addListView(Habbit habbit) {
        dateCounter dateCounter = new dateCounter();
        habbitsView.add(new HabbitsView(habbit.getImageId(), habbit.getHabbitName(), "Scores: " + habbit.getOverallScore(), "Days streak:" + dateCounter.compareDays(habbit.getDateCreated()), habbit.getDateCreated().toDate(), habbit.getScoreMultiplier()));
    }

    public HabbitsView getHabbitViewItem(int i) {
        return habbitsView.get(i);
    }

    public Habbit getHabbitItem(int i) {
        return habbits.get(i);
    }

    public void deleteHabbit(int i) {
        habbitsView.remove(i);
        habbits.remove(i);
    }

    public void replaceListHabbits(ArrayList<Habbit> habbit) {
        this.habbitsTesting = habbit;
    }

    public void replaceListHabbitsList(ArrayList<HabbitsView> habbitsView) {
        this.habbitsViewsTesting = habbitsView;
    }

    //Gets all the habbits and gives them more multiplier and daily score
    public void dailyPointsAndMultipliers() {
        int index = 0;
        while (index < habbits.size()) {
            getHabbitItem(index).addScoreMultiplier();
            getHabbitItem(index).addDailyScore();
            index++;
        }
    }
}
