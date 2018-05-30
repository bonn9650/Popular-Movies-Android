package com.example.android.popularmovies;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class FilmsListSettingsFragment extends PreferenceFragmentCompat {


    public FilmsListSettingsFragment() {


    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.list_preferences);

    }

}
