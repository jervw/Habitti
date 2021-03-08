package com.example.habitti;


import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ProgressBar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

//Singleton-class
public class GlobalModel {
    private static final GlobalModel ourInstance = new GlobalModel();
    private ArrayList<Habbit> habbits = null;
    private ArrayList<HabbitsView> habbitsView = null;
    private ArrayList<Habbit> habbitsTesting = null;
    private ArrayList<HabbitsView> habbitsViewsTesting = null;
    private double userOverallScores;
    private int userLevel;
    private double levelCap;
    private int levelCapProgress;
    private int userOverallScoresProgress;

    public  static GlobalModel getInstance() {
        return ourInstance;
    }

    private GlobalModel() {
        habbits = new ArrayList<Habbit>();
        habbitsView = new ArrayList<HabbitsView>();
        habbitsTesting = new ArrayList<Habbit>();
        habbitsViewsTesting = new ArrayList<HabbitsView>();
        userOverallScores = 0;
        userLevel = 1;
        levelCap = 100;
        levelCapProgress = 100;
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

    public void addListView(Habbit habbit){
                habbitsView.add(new HabbitsView(habbit.getImageId(), habbit.getHabbitName(), habbit.getHabitType(), "" + habbit.getOverallScore(), ""+habbit.getDayStreak(), habbit.getDateCreated(), habbit.getScoreMultiplier(), habbit.getCheckedStatus()));
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
        if (habbit == null) {

        } else {
            habbits.clear();
            habbitsView.clear();
        ArrayList<Habbit> habbits = habbit;
        int index = 0;
        while (index < habbits.size() ) {
            addHabbit(habbits.get(index));
            index++;
        }
        }
    }

    public void resetMultiplier(Habbit habbit) {
        habbit.resetScoreMultiplier();
        updateHabbitViewList();
    }

    public String getOwnDateCreatedAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        return date;
    }

    //Used to update items in habbitsView array
    public void updateHabbitViewList () {
        int index = 0;
        habbitsView.clear();
        while (index < habbits.size()) {
            habbitsView.add(new HabbitsView(getHabbitItem(index).getImageId(), getHabbitItem(index).getHabbitName(), getHabbitItem(index).getHabitType(), "" + getHabbitItem(index).getOverallScore(), ""+getHabbitItem(index).getDayStreak(),
                    getHabbitItem(index).getDateCreated(), getHabbitItem(index).getScoreMultiplier(), getHabbitItem(index).getCheckedStatus()));
            index++;
        }
    }

    public void getUserScoresFromHabbits() {
        int index = 0;
        double overallScoresDouble = 0.0;
        while (index < habbits.size()) {
            overallScoresDouble = overallScoresDouble + getHabbitItem(index).getOverallScore();
            index++;
        }
        this.setUserOverallScores(overallScoresDouble);

        if (userOverallScores >= levelCap) {
            checkUserLevelUp();
        }
    }

    public int getProgressbarMax() {
        return this.levelCapProgress;
    }

    public void setProgressbarMax(int cap) {
        this.levelCapProgress = cap;
    }

    public void setOneHabbitScoresToProgress(Habbit habbit) {
        this.userOverallScoresProgress = this.userOverallScoresProgress + (int) habbit.getOverallScore();
    }

    public int getProgressbarProgress() {
        return this.userOverallScoresProgress;
    }
    public double getLevelCap() {
        return this.levelCap;
    }
    public void setLevelCap(double cap) {
        this.levelCap = cap;
    }

    public void setProgressbarProgress(int scores) {
        this.userOverallScoresProgress = scores;
    }

    public void checkUserLevelUp() {
            this.userLevel = this.userLevel + 1;
            this.levelCapProgress = (int) (levelCap * 1.10);
            this.levelCap =this.levelCap + (this.levelCap * 1.10);
            this.userOverallScoresProgress = 0;
        }

    public void setUserOverallScores(double scores) {
        this.userOverallScores = scores;
    }

    public void setHabitName(int index, String newHabitName){
        getHabbitItem(index).setHabitName(newHabitName);
    }

    public double getUserOverallScores() {
        return this.userOverallScores;
    }

    public int getUserLevel() {
        return this.userLevel;
    }

    public void setUserLevel(int i) {
        this.userLevel = i;
    }
}
