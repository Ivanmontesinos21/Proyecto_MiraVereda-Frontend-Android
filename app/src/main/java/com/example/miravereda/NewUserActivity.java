package com.example.miravereda;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class NewUserActivity extends AppCompatActivity {

    private CuentaRepository cuentaRepository;

    private Button crearusuario;

    private TextInputEditText nombre;
    private TextInputEditText apellidos;

    private TextInputEditText mail;

    private TextInputEditText username;

    private CalendarView calendarView;

    private TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        crearusuario = findViewById(R.id.createUser);
        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        mail = findViewById(R.id.mail);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        //calendarView=findViewById(R.id.calendar);
        crearusuario.setOnClickListener(v -> {
            if (crearusuario.toString().isEmpty() || nombre.toString().isEmpty() || apellidos.toString().isEmpty() || mail.toString().isEmpty() ||
                    username.toString().isEmpty() || password.toString().isEmpty()) {
                Toast.makeText(this, "Porfavor rellena todos los campos son requeridos", Toast.LENGTH_LONG).show();
            } else {
                //Aqui hay que añadir la cuenta


            }

        });


    }
}