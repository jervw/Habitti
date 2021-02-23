package com.example.habitti;


import java.util.ArrayList;

//Singleton-class
public class GlobalModel {
    private static final GlobalModel ourInstance = new GlobalModel();
    private ArrayList<Habbit> habbits = null;
    private ArrayList<HabbitsView> habbitsView = null;

    public  static GlobalModel getInstance() {
        return ourInstance;
    }

    private GlobalModel() {
        habbits = new ArrayList<Habbit>();
        habbitsView = new ArrayList<HabbitsView>();
    }

    public void addHabbit(Habbit habbit) {
        habbits.add(habbit);
        addListView(habbit);
    }

    public ArrayList<Habbit> GetHabbitsList() {
        return this.habbits;
    }

    public ArrayList<HabbitsView> getHabbitsView() {
        return this.habbitsView;
    }

    public void addListView(Habbit habbit) {
        habbitsView.add(new HabbitsView(habbit.getImageId(), habbit.getHabbitName(), "Scores: " + habbit.getOverallScore(), "Day streak: 2", habbit.getDateCreated()));
    }

    public HabbitsView getHabbit(int i) {
        return habbitsView.get(i);
    }
}
