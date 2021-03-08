package com.example.habitti;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <h1>GlobalModel, singleton class</h1>
 * Used to store all Habit information
 * @author Santeri Hytönen
 */
//Singleton-class
public class GlobalModel {
    private static final GlobalModel ourInstance = new GlobalModel();
    private ArrayList<Habit> habits = null;
    private ArrayList<HabitsView> habitsView = null;
    private ArrayList<Habit> habbitsTesting = null;
    private ArrayList<HabitsView> habbitsViewsTesting = null;
    private double userOverallScores;
    private int userLevel;
    private double levelCap;
    private int levelCapProgress;
    private int userOverallScoresProgress;
    private int loginStreak;

    /**
     *
     * @return ourInstance instance
     */
    public  static GlobalModel getInstance() {
        return ourInstance;
    }

    /**
     * Stores Habit and HabitView-lists, user scores, user level, max scores needed for next level, max scores for display and login streak
     */
    private GlobalModel() {
        habits = new ArrayList<Habit>();
        habitsView = new ArrayList<HabitsView>();
        habbitsTesting = new ArrayList<Habit>();
        habbitsViewsTesting = new ArrayList<HabitsView>();
        userOverallScores = 0;
        userLevel = 1;
        levelCap = 100;
        levelCapProgress = 100;
        loginStreak = 0;
    }

    /**
     * Adds Habit to list
     * creates HabitView based on that Habit
     * @param habbit
     */
    public void addHabbit(Habit habbit) {
        habits.add(habbit);
        addListView(habbit);
    }

    /**
     *
     * @return Habit-list
     */
    public ArrayList<Habit> getHabitsList() {
        return this.habits;
    }

    /**
     *
     * @return HabitsView-list
     */
    public ArrayList<HabitsView> getHabbitsView() {
        return this.habitsView;
    }

    /**
     * Creates new HabitView on Habit's information
     * Adds that HabitView to HabitView-list
     * @param habbit
     */
    public void addListView(Habit habbit){
                habitsView.add(new HabitsView(habbit.getImageId(), habbit.getHabitName(), habbit.getHabitType(), "" + habbit.getOverallScore(), ""+habbit.getDayStreak(), habbit.getDateCreated(), habbit.getScoreMultiplier(), habbit.getCheckedStatus()));
    }

    /**
     *
     * @param i index for HabitView-list
     * @return HabitView with that index
     */
    public HabitsView getHabbitViewItem(int i) {
        return habitsView.get(i);
    }

    /**
     *
     * @param i index for Habit-list
     * @return Habit with that index
     */
    public Habit getHabitItem(int i) {
        return habits.get(i);
    }

    /**
     * Deletes specific Habit and HabitView
     * @param i index for Habit and HabitView wanted to delete
     */
    public void deleteHabbit(int i) {
        habitsView.remove(i);
        habits.remove(i);
    }

    /**
     * Check if ArrayList Habit is null
     * If not, clears ArrayLists Habit and HabitView
     * Copies param Habit list to a new Habit list
     * Adds all the Habit from that new list one by one to GlobalModels Habit-list
     *
     * @param habit List of Habit wanted to add to GlobalModel
     */
    public void replaceListHabbits(ArrayList<Habit> habit) {
        if (habit == null) {

        } else {
            habits.clear();
            habitsView.clear();
        ArrayList<Habit> habits = habit;
        int index = 0;
        while (index < habits.size() ) {
            addHabbit(habits.get(index));
            index++;
        }
        }
    }

    /**
     * Reset on Habit's score multiplier and day strike
     * @param habit Habit whose scores are being reset
     */
    public void resetMultiplier(Habit habit) {
        habit.resetScoreMultiplier();
        updateHabbitViewList();
    }

    /**
     * Used by Habits when they are created to get their creation date
     * @return current date
     */
    public String getOwnDateCreatedAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        return date;
    }

    /**
     * Delete all item from HabitsView and replace them by new HabitViews with updated data
     */
    //Used to update items in habbitsView array
    public void updateHabbitViewList () {
        int index = 0;
        habitsView.clear();
        while (index < habits.size()) {
            habitsView.add(new HabitsView(getHabitItem(index).getImageId(), getHabitItem(index).getHabitName(), getHabitItem(index).getHabitType(), "" + getHabitItem(index).getOverallScore(), ""+ getHabitItem(index).getDayStreak(),
                    getHabitItem(index).getDateCreated(), getHabitItem(index).getScoreMultiplier(), getHabitItem(index).getCheckedStatus()));
            index++;
        }
    }

    /**
     * Used to call every Habit and get their scores
     * Add those scores to users scores
     * Also check if user scores are bigger or equal to level cap
     * If so, call checkUserLevelUp() for leveling up
     */
    public void getUserScoresFromHabits() {
        int index = 0;
        double overallScoresDouble = 0.0;
        while (index < habits.size()) {
            overallScoresDouble = overallScoresDouble + getHabitItem(index).getOverallScore();
            index++;
        }
        this.setUserOverallScores(overallScoresDouble);

        if (userOverallScores >= levelCap) {
            checkUserLevelUp();
        }
    }

    /**
     * Used on MainFragment to show how much points are needed for level up
     * @return this.levelCapProgress
     */
    public int getProgressbarMax() {
        return this.levelCapProgress;
    }

    /**
     * Used to set how much points are needed for the next level
     * @param cap new level cap for the next level
     */
    public void setProgressbarMax(int cap) {
        this.levelCapProgress = cap;
    }

    /**
     * Gets one Habit and get its scores to be added to progress
     * @param habit one Habit
     */
    public void setOneHabbitScoresToProgress(Habit habit) {
        this.userOverallScoresProgress = this.userOverallScoresProgress + (int) habit.getOverallScore();
    }

    /**
     * Used to show how much point is gained since last level up
     * @return current progress
     */
    public int getProgressbarProgress() {
        return this.userOverallScoresProgress;
    }

    /**
     * Get the level cap needed for next level
     * @return current level cap
     */
    public double getLevelCap() {
        return this.levelCap;
    }

    /**
     * Set the level cap
     * @param cap new level cap
     */
    public void setLevelCap(double cap) {
        this.levelCap = cap;
    }

    /**
     * Used to set the current progress shown in MainFragment
     * @param scores new progress
     */
    public void setProgressbarProgress(int scores) {
        this.userOverallScoresProgress = scores;
    }

    /**
     * When enough points are gained call this method
     * Adds one to user level
     * Increase both real level cap and level cap shown in MainFragment
     * Set current progress to 0
     */
    public void checkUserLevelUp() {
            this.userLevel = this.userLevel + 1;
            this.levelCapProgress = (int) (levelCap * 1.10);
            this.levelCap =this.levelCap + (this.levelCap * 1.10);
            this.userOverallScoresProgress = 0;
        }

    /**
     * Set user scores
     * @param scores new scores
     */
    public void setUserOverallScores(double scores) {
        this.userOverallScores = scores;
    }

    /**
     * Used to change Habit name
     * @param index Place in the Habit-list
     * @param newHabitName New name
     */
    public void setHabitName(int index, String newHabitName){
        getHabitItem(index).setHabitName(newHabitName);
    }

    /**
     * Used to get users scores
     * @return users current scores
     */
    public double getUserOverallScores() {
        return this.userOverallScores;
    }

    /**
     * Used to get users current level
     * @return users current level
     */
    public int getUserLevel() {
        return this.userLevel;
    }

    /**
     * Used to set users level
     * @param i new level
     */
    public void setUserLevel(int i) {
        this.userLevel = i;
    }

    /**
     * Used to set users login streak
     * @param i new login streak
     */
    public void setLoginStreak(int i) {
        this.loginStreak = i;
    }

    /**
     * Used to get users login streak
     * @return users current login streak
     */
    public int getLoginStreak() {
        return this.loginStreak;
    }
}
