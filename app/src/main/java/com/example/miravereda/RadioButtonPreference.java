package com.example.miravereda;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;

import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
public class RadioButtonPreference extends Preference {
    public RadioButtonPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWidgetLayoutResource(R.layout.radiobutton_preference_layout);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);


        final RadioButton lightRadioButton = (RadioButton) holder.findViewById(R.id.radio_light);
        final RadioButton darkRadioButton = (RadioButton) holder.findViewById(R.id.radio_dark);
        final RadioButton systemRadioButton = (RadioButton) holder.findViewById(R.id.radio_system);

        // Obtener el tema actual guardado en las SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences();
        String selectedTheme = sharedPreferences.getString("theme", "system");


        switch (selectedTheme) {
            case "light":
                lightRadioButton.setChecked(true);
                break;
            case "dark":
                darkRadioButton.setChecked(true);
                break;
            case "system":
                systemRadioButton.setChecked(true);
                break;
        }


        View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton radioButton = (RadioButton) view;

                // Guardar el tema seleccionado en las SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                switch (radioButton.getId()) {
                    case R.id.radio_light:
                        editor.putString("theme", "light");
                        break;
                    case R.id.radio_dark:
                        editor.putString("theme", "dark");
                        break;
                    case R.id.radio_system:
                        editor.putString("theme", "system");
                        break;
                }
                editor.apply();
            }
        };


        lightRadioButton.setOnClickListener(radioButtonClickListener);
        darkRadioButton.setOnClickListener(radioButtonClickListener);
        systemRadioButton.setOnClickListener(radioButtonClickListener);
    }
}
