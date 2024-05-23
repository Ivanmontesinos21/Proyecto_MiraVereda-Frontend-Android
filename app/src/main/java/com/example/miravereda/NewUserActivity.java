package com.example.miravereda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miravereda.API.Connector;
import com.example.miravereda.base.BaseActivity;
import com.example.miravereda.base.CallInterface;
import com.example.miravereda.model.NewUsuario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class NewUserActivity extends BaseActivity implements CallInterface {

    private Connector connector;


    private Button crearusuario;

    private DatePicker datePicker;

    private TextInputEditText Enombre;
    private TextInputEditText Eapellidos;

    private TextInputEditText Email;

    private TextInputEditText Eusername;
    private TextInputEditText Edomicilio;
    private TextInputEditText EcodigoPostal;
    NewUsuario usuario;


    private TextInputEditText Epassword;

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
        Enombre = findViewById(R.id.nombrenew);
        Eapellidos = findViewById(R.id.apellidosnew);
        datePicker=findViewById(R.id.date);
        Email = findViewById(R.id.mailnew);
        Epassword = findViewById(R.id.passwordnew);
        Edomicilio = findViewById(R.id.domicilionew);
        EcodigoPostal = findViewById(R.id.codpostalnew);
        crearusuario.setOnClickListener(v -> {
            if (crearusuario.toString().isEmpty() || Enombre.toString().isEmpty() || Eapellidos.toString().isEmpty() || Email.toString().isEmpty() ||
                      Epassword.toString().isEmpty()) {
                Toast.makeText(this, "Por favor, rellene todos los campos son requeridos", Toast.LENGTH_LONG).show();
            } else {
                executeCall(this);
            }
        });

    }

    @Override
    public void doInBackground() {
        registrar();

    }

    @Override
    public void doInUI() {
        if(usuario != null) {
            Toast.makeText(this, "Usuario creado", Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            Toast.makeText(this, "Error al crear usuario", Toast.LENGTH_LONG).show();
        }
    }

    private long getDate() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime().getTime() / 1000;
    }

    private void registrar() {
        String nombre=Enombre.getText().toString();
        String apellidos=Eapellidos.getText().toString();
        String email=Email.getText().toString();
        String domicilio = Edomicilio.getText().toString();
        String codigoPostal = EcodigoPostal.getText().toString();
        long date1=getDate();
        String password=Epassword.getText().toString();
        NewUsuario u=new NewUsuario(0,nombre,apellidos,email,date1,password,domicilio,codigoPostal);
        try {
            usuario = Connector.getConector().post(NewUsuario.class, u, "usuario/");
        }
        catch (NullPointerException e) {
            usuario = null;
        }

    }
}