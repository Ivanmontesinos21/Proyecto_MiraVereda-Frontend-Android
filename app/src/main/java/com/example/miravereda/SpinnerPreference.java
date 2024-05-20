package com.example.miravereda;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import java.util.Arrays;

public class SpinnerPreference extends Preference {
    public SpinnerPreference(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWidgetLayoutResource(R.layout.spinner_preference_layout);
    }
    @Override
    public void onBindViewHolder(PreferenceViewHolder holder){
        super.onBindViewHolder(holder);
        final AppCompatSpinner spinner=(AppCompatSpinner) holder.findViewById(R.id.spinner);


        String[] languajes=getContext().getResources().getStringArray(R.array.languajes);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,languajes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        SharedPreferences sharedPreferences=getSharedPreferences();
        String selectedLanguaje=sharedPreferences.getString("languaje","es");

        int position= Arrays.asList(languajes).indexOf(selectedLanguaje);
        spinner.setSelection(position);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("languaje",languajes[position]);
                        editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
