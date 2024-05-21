package com.example.miravereda;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
public class RadioButtonPreference extends Preference {
    public RadioButtonPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWidgetLayoutResource(R.layout.radiobutton_preference_layout);
    }

    @Override
    public void onBindViewHolder(@NonNull PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);


        final RadioButton lightRadioButton = (RadioButton) holder.findViewById(R.id.radio_light);
        final RadioButton darkRadioButton = (RadioButton) holder.findViewById(R.id.radio_dark);
        final RadioButton systemRadioButton = (RadioButton) holder.findViewById(R.id.radio_system);

        // Obtener el tema actual guardado en las SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences();
        assert sharedPreferences != null;
        String selectedTheme = sharedPreferences.getString("theme", "system");

        lightRadioButton.setChecked("light".equals(selectedTheme));
        darkRadioButton.setChecked("dark".equals(selectedTheme));
        systemRadioButton.setChecked("system".equals(selectedTheme));


        lightRadioButton.setOnClickListener(v -> saveTheme("light"));
        darkRadioButton.setOnClickListener(v -> saveTheme("dark"));
        systemRadioButton.setOnClickListener(v -> saveTheme("system"));
    }
        private void saveTheme(String theme) {
            SharedPreferences.Editor editor=
                    getSharedPreferences().edit().putString("theme",theme);
            editor.apply();
        }


    }

