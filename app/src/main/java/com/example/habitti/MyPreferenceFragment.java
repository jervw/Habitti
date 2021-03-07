package com.example.habitti;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class MyPreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.prefs, rootKey);

        Preference button = findPreference(getString(R.string.creditsButton));
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Santeri Hytönen\n Anna Raevskaia\n Jere Vuola";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            }

        });

        Preference list = findPreference("theme");
        list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.d("app", "change detected");
                startActivity(getActivity().getIntent());
                return true;
            }
        });



    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {

        //IT NEVER GETS IN HERE!
        if (key.equals("theme"))
        {
            Log.d("app", "change detected");
            // Set summary to be the user-description for the selected value
            Preference exercisesPref = findPreference(key);
            exercisesPref.setSummary(sharedPreferences.getString(key, ""));
        }
    }

}
