package com.example.miravereda;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Preference extends AppCompatActivity {
    private Spinner spinnerPreference;
    private RadioButton rdark;
    private RadioButton rdlight;
    private RadioButton rdsystem;
    private Button back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preference);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
            spinnerPreference=findViewById(androidx.preference.R.id.spinner);
            rdark=findViewById(R.id.radio_dark);
            rdlight=findViewById(R.id.radio_light);
            rdsystem=findViewById(R.id.radio_system);
        });
    }
}