package com.example.miravereda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.PreferenceManager;

public class Preference extends AppCompatActivity {
    private Spinner spinnerPreference;
    private RadioGroup radioGroupTheme;
    private RadioButton rdark;
    private RadioButton rdlight;
    private RadioButton rdsystem;
    private Button back;
    private Button apply;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);


        spinnerPreference = findViewById(R.id.spinner);
        radioGroupTheme = findViewById(R.id.radio_group);
        rdark = findViewById(R.id.radio_dark);
        rdlight = findViewById(R.id.radio_light);
        rdsystem = findViewById(R.id.radio_system);
        back = findViewById(R.id.button_back);
        apply = findViewById(R.id.button_apply);
        /**
         * Configura el Spinner
         */


        String[] languages = getResources().getStringArray(R.array.languajes);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPreference.setAdapter(adapter);

        /**
         *   Carga las preferencias guardadas
          */

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String selectedLanguage = sharedPreferences.getString("language", "es");
        String selectedTheme = sharedPreferences.getString("theme", "system");

        /**
         *  Configura el Spinner según las preferencias guardadas
         */
        int languagePosition = adapter.getPosition(selectedLanguage);
        spinnerPreference.setSelection(languagePosition);

        /**
         *  Configura los RadioButtons según las preferencias guardadas
         */
        if ("light".equals(selectedTheme)) {
            rdlight.setChecked(true);
        } else if ("dark".equals(selectedTheme)) {
            rdark.setChecked(true);
        } else {
            rdsystem.setChecked(true);
        }

        /**
         *  Configura los listeners para guardar las preferencias
         */
        spinnerPreference.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("language", languages[position]);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se hace nada
            }
        });
        /**
         * comprueba el id y si el id es el indicado pues lo cambia.
         */

        radioGroupTheme.setOnCheckedChangeListener((group, checkedId) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (checkedId == R.id.radio_light) {
                editor.putString("theme", "light");
            } else if (checkedId == R.id.radio_dark) {
                editor.putString("theme", "dark");
            } else if (checkedId == R.id.radio_system) {
                editor.putString("theme", "system");
            }
            editor.apply();
        });

        /**
         *   Configura el botón de aplicar cambios
         */
        apply.setOnClickListener(v -> {
            // Recarga la actividad principal para aplicar los cambios
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        // Configura el botón de regreso
        back.setOnClickListener(v -> finish());
    }
}