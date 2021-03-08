package com.example.habitti;

import android.content.Context;
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

        Preference button = findPreference("creditsButton");
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
    }
}
