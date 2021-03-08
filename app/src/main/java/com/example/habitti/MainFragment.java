package com.example.habitti;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

/**
 * <h1>MainFragment</h1>
 * @author Santeri Hytönen
 * @author Anna Raevskaia
 * @author Jere Vuola
 * MainFragment is used as the main view for the user
 */
public class MainFragment extends Fragment {

    private SharedPreferences sharedPrefHabbits;
    private final String sharedPreferenceName = "shared preference";

    SaveManager saveLoad = SaveManager.getInstance().getInstance();

    int[] clothesImages;
    int[] hairsImages;

    ListView habbitsListView;
    View rootView;
    int userDayStreak = 0;
    //TextView userDayStreakText;
    TextView level;
    TextView userLoginStreak;
    TextView userScores;

    /**
     *
     * Basic onCreate method
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        dateCheck dateCheck = new dateCheck(getActivity());

        level = (TextView) rootView.findViewById(R.id.levelText);
        userLoginStreak = (TextView) rootView.findViewById(R.id.textViewUserLoginStreak);
        userScores = (TextView) rootView.findViewById(R.id.textViewUserScores);
        //userDayStreakText = (TextView) rootView.findViewById(R.id.textViewUserDayStreak);
        userScores.setText("Scores: " + GlobalModel.getInstance().getUserOverallScores());


        //Load saved preferences and put them on screen
        initializeCalendar();
        loadHabbitData();
        updateUI();
        level.setText("Level : " + GlobalModel.getInstance().getUserLevel() + " XP:  " + GlobalModel.getInstance().getProgressbarProgress()
                + " / " + GlobalModel.getInstance().getProgressbarMax());
        userScores.setText("Total scores: " + GlobalModel.getInstance().getUserOverallScores());
        userLoginStreak.setText("Login streak: " + GlobalModel.getInstance().getLoginStreak());

        return rootView;
    }


    /**
     * Used to setup the calendar
     */
    private void initializeCalendar() {
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        Calendar endDate = Calendar.getInstance();

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .configure()
                    .showTopText(false)
                .end()
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //TODO do something
            }
        });
    }

    /**
     * Gets user scores, level progress, user level and Habit-list and stores them to sharedPreferences
     */
    private void saveHabbitData() {
        sharedPrefHabbits = getActivity().getSharedPreferences(sharedPreferenceName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefHabbits.edit();
        Gson gson = new Gson();
        Double userScoresD = GlobalModel.getInstance().getUserOverallScores();
        int userLevel = GlobalModel.getInstance().getUserLevel();
        String userScores = String.valueOf(userScoresD);
        int userScoresProgress = GlobalModel.getInstance().getProgressbarProgress();
        int userScoresCapProgress = GlobalModel.getInstance().getProgressbarMax();
        double levelCapD = GlobalModel.getInstance().getLevelCap();
        String levelCap = String.valueOf(levelCapD);
        String jsonHabbits = gson.toJson(GlobalModel.getInstance().getHabitsList());
        editor.putInt("User level", userLevel);
        editor.putInt("Progress cap", userScoresCapProgress);
        editor.putString("Habbits list", jsonHabbits);
        editor.putString("User Scores", userScores);
        editor.putInt("User Scores Progress", userScoresProgress);
        editor.putString("Level cap", levelCap);
        editor.apply();
        Log.d("Tag", "Saved");
        Log.d("Tag", "Level " + GlobalModel.getInstance().getUserLevel());
    }

    /**
     * Gets user scores, level progress, user level and Habit-list and stores them to sharedPreferences
     * Also checks if firstCheckOfDay is true or false
     * If its true user DateCheck to see if user login streak has kept
     */
    public void loadHabbitData() {
        sharedPrefHabbits = getActivity().getSharedPreferences(sharedPreferenceName, Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String userScores = sharedPrefHabbits.getString("User Scores", "0");
        int userScoresProgress = sharedPrefHabbits.getInt("User Scores Progress", 0);
        int userLevel = sharedPrefHabbits.getInt("User level", 1);
        String levelCap = sharedPrefHabbits.getString("Level cap", "100");
        double levelCapD = Double.parseDouble(levelCap);
        double userScoresD = Double.parseDouble(userScores);
        int userScoresProgressCap = sharedPrefHabbits.getInt("Progress cap", 100);
        String jsonHabbits = sharedPrefHabbits.getString("Habbits list", null);
        Type typeHabbits = new TypeToken<Collection<Habit>>() {
        }.getType();
        GlobalModel.getInstance().replaceListHabbits(gson.fromJson(jsonHabbits, typeHabbits));
        GlobalModel.getInstance().setUserLevel(userLevel);
        GlobalModel.getInstance().setProgressbarMax(userScoresProgressCap);
        GlobalModel.getInstance().setUserOverallScores(userScoresD);
        GlobalModel.getInstance().setProgressbarProgress(userScoresProgress);
        GlobalModel.getInstance().setLevelCap(levelCapD);
        //Go check if day has passed since last app start and give points accordingly
        if (MainActivity.firstCheckOfDay == true) {
            dateCheck.checkDate();
            userDayStreak = dateCheck.loginDayStreak();
            GlobalModel.getInstance().setLoginStreak(userDayStreak);
            userLoginStreak.setText("Login streak: " + userDayStreak);
            MainActivity.firstCheckOfDay = false;
        }
    }

    /**
     * Used to update the UI
     * First get user scores, user level, current level progress and current progress max points and sets them to UI
     * Next setups arrayAdapter and saves data
     * Next set onItemClickListener and onItemLongClickListener for the listView items
     * At last call updateCostume()
     */
    public void updateUI() {
        //Check if ArrayList from GlobalModel is set null by sharedPreferences
        //If yes, create new ArrayList. If no get that ArrayList from GlobalModel. Put that ArrayList to ArrayAdapter
        //Find listView by id and set ArrayAdapter to it. Then save current habbits from that ArrayList.
        GlobalModel.getInstance().getUserScoresFromHabits();
        userScores.setText("Total scores: " + GlobalModel.getInstance().getUserOverallScores());
        level.setText("Level : " + GlobalModel.getInstance().getUserLevel() + " XP: " + GlobalModel.getInstance().getProgressbarProgress()
                + " / " + GlobalModel.getInstance().getProgressbarMax());
        HabitsViewAdapter habbitsArrayAdapter;
        if (GlobalModel.getInstance().getHabbitsView() == null) {
            habbitsArrayAdapter = new HabitsViewAdapter(getActivity(), new ArrayList<HabitsView>());
        } else {
            habbitsArrayAdapter = new HabitsViewAdapter(getActivity(), GlobalModel.getInstance().getHabbitsView());
        }


        Log.d("MAIN FRAGMENT", "updateUI");
        habbitsArrayAdapter = new HabitsViewAdapter(getActivity(), GlobalModel.getInstance().getHabbitsView());
        habbitsListView = (ListView) rootView.findViewById(R.id.listViewHabbits);
        habbitsListView.setAdapter(habbitsArrayAdapter);
        saveHabbitData();

        habbitsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view, int i, long l) {
                HabitDetailsDialog detailsDialog = new HabitDetailsDialog(i);
                detailsDialog.show(getFragmentManager(), "habit details");
                return false;
            }
        });

        HabitsViewAdapter finalHabbitsArrayAdapter = habbitsArrayAdapter;
        habbitsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                if (!GlobalModel.getInstance().getHabitItem(i).getCheckedStatus()) {
                GlobalModel.getInstance().getHabitItem(i).setCheckedStatus(true);
                GlobalModel.getInstance().getHabitItem(i).addDailyScore();
                finalHabbitsArrayAdapter.notifyDataSetChanged();
                GlobalModel.getInstance().updateHabbitViewList();
                GlobalModel.getInstance().getUserScoresFromHabits();
                GlobalModel.getInstance().setOneHabbitScoresToProgress(GlobalModel.getInstance().getHabitItem(i));
                GlobalModel.getInstance().getUserScoresFromHabits();
                userScores.setText("Total scores: " + GlobalModel.getInstance().getUserOverallScores());
                level.setText("Level : " + GlobalModel.getInstance().getUserLevel() + " XP: " + GlobalModel.getInstance().getProgressbarProgress()
                        + " / " + GlobalModel.getInstance().getProgressbarMax());

                Log.d("Tag", "Progress: " + GlobalModel.getInstance().getProgressbarProgress());
                Log.d("Tag", "Overall scores" + GlobalModel.getInstance().getUserOverallScores());
                Log.d("Tag", "Level " + GlobalModel.getInstance().getUserLevel());
            }
            }
        });

        updateCostume();
    }

    /**
     *
     */
    public void updateCostume() {
        // GET NAME FROM SHARED PREFERENCE.XML:
        sharedPrefHabbits = this.getActivity().getSharedPreferences("shared preference", Context.MODE_PRIVATE);
        TextView textViewUserName = (TextView) rootView.findViewById(R.id.username);
        if (sharedPrefHabbits.contains("LastUserName")) {
            textViewUserName.setText(sharedPrefHabbits.getString("LastUserName", ""));
        }

        // GET CLOTHES FROM SHARED PREFERENCE.XML:
        //clothesImages = new int[] {R.drawable.char_13, R.drawable.char_2, R.drawable.char_15, R.drawable.char_10, R.drawable.char_14};
        clothesImages = new int[] {R.drawable.char_13, R.drawable.char_14, R.drawable.char_2, R.drawable.char_10, R.drawable.char_16, R.drawable.char_15};
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

    /**
     * on resume call updateUI, get user scores and display them
     */
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
        Log.d("Tag", "Level " + GlobalModel.getInstance().getUserLevel());
        Log.d("Tag", "Progress " + GlobalModel.getInstance().getProgressbarProgress());
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
        GlobalModel.getInstance().getUserScoresFromHabits();
        userScores.setText("Scores: " + GlobalModel.getInstance().getUserOverallScores());
    }

    /**
     * on pause save data
     */
    @Override
    public void onPause() {
        super.onPause();
        saveHabbitData();
        Log.d("MAIN", "OnPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        saveHabbitData();
    }
}
