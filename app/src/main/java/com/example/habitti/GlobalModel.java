package com.example.habitti;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//Singleton-class
public class GlobalModel {
    private static final GlobalModel ourInstance = new GlobalModel();
    private ArrayList<Habit> habbits = null;
    private ArrayList<HabitsView> habbitsView = null;
    private ArrayList<Habit> habbitsTesting = null;
    private ArrayList<HabitsView> habbitsViewsTesting = null;
    private double userOverallScores;
    private int userLevel;
    private double levelCap;
    private int levelCapProgress;
    private int userOverallScoresProgress;
    private int loginStreak;

    public  static GlobalModel getInstance() {
        return ourInstance;
    }

    private GlobalModel() {
        habbits = new ArrayList<Habit>();
        habbitsView = new ArrayList<HabitsView>();
        habbitsTesting = new ArrayList<Habit>();
        habbitsViewsTesting = new ArrayList<HabitsView>();
        userOverallScores = 0;
        userLevel = 1;
        levelCap = 100;
        levelCapProgress = 100;
        loginStreak = 0;
    }

    public void addHabbit(Habit habbit) {
        habbits.add(habbit);
        addListView(habbit);
    }

    public ArrayList<Habit> getHabbitsList() {
        return this.habbits;
    }

    public ArrayList<HabitsView> getHabbitsView() {
        return this.habbitsView;
    }

    public void addListView(Habit habbit){
                habbitsView.add(new HabitsView(habbit.getImageId(), habbit.getHabbitName(), habbit.getHabitType(), "" + habbit.getOverallScore(), ""+habbit.getDayStreak(), habbit.getDateCreated(), habbit.getScoreMultiplier(), habbit.getCheckedStatus()));
    }

    public HabitsView getHabbitViewItem(int i) {
        return habbitsView.get(i);
    }

    public Habit getHabbitItem(int i) {
        return habbits.get(i);
    }

    public void deleteHabbit(int i) {
        habbitsView.remove(i);
        habbits.remove(i);
    }

    public void replaceListHabbits(ArrayList<Habit> habbit) {
        if (habbit == null) {

        } else {
            habbits.clear();
            habbitsView.clear();
        ArrayList<Habit> habbits = habbit;
        int index = 0;
        while (index < habbits.size() ) {
            addHabbit(habbits.get(index));
            index++;
        }
        }
    }

    public void resetMultiplier(Habit habbit) {
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
            habbitsView.add(new HabitsView(getHabbitItem(index).getImageId(), getHabbitItem(index).getHabbitName(), getHabbitItem(index).getHabitType(), "" + getHabbitItem(index).getOverallScore(), ""+getHabbitItem(index).getDayStreak(),
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
        if (overallScoresDouble < levelCapProgress) {
            this.userOverallScoresProgress = (int) overallScoresDouble;
        } else {
            this.userOverallScoresProgress = (int) (overallScoresDouble) - levelCapProgress;
        }
        if (userOverallScores >= levelCap) {
            checkUserLevelUp();
        }
    }

    public int getProgressbarMax() {
        return this.levelCapProgress;
    }

    public int getProgressbarProgress() {
        return this.userOverallScoresProgress;
    }

    public void setProgressbarProgress(int scores) {
        this.userOverallScoresProgress = scores;
    }

    public void checkUserLevelUp() {
            this.userLevel++;
            this.levelCapProgress = (int) (levelCap * 1.05);
            this.levelCap =this.levelCap + (this.levelCap * 1.05);
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

    public void setLoginStreak(int i) {
        this.loginStreak = i;
    }

    public int getLoginStreak() {
        return this.loginStreak;
    }
}
