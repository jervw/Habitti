package com.example.habitti;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

/**
 * <h1>Preference fragment</h1>
 * This preference fragment loads preferences from prefs.xml by using PreferenceScreen component
 * @author Jere Vuola
 */
public class PreferenceFragment extends PreferenceFragmentCompat {


    /**
     * Loads preferences from xml file and adds functionality to 'Credits' button
     * @param rootKey
     */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.prefs, rootKey);

        Preference credits = findPreference("creditsButton");
        credits.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                CharSequence text = "Santeri Hytönen\n Anna Raevskaia\n Jere Vuola";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), text, duration);
                toast.show();
                return true;
            }
        });

        Preference tutorial = findPreference("tutorial");
        tutorial.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent y = new Intent(getActivity().getApplicationContext(), TutorialActivity.class);
                startActivity(y);
                return true;
            }
        });
    }
}
