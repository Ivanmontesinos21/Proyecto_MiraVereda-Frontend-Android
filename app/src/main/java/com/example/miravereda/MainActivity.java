package com.example.miravereda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.miravereda.API.Connector;
import com.example.miravereda.API.Conversor;
import com.example.miravereda.base.BaseActivity;
import com.example.miravereda.base.CallInterface;
import com.example.miravereda.model.ContenidoAudiovisual;
import com.example.miravereda.model.Credenciales;
import com.example.miravereda.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainActivity extends BaseActivity implements CallInterface {

    private Button iniciarSesion;

    private Button forgetPassword;

    private Button createAccount;
    private Usuario usuario;
    private TextInputEditText usertext;
    private TextInputEditText password;
    private TextInputLayout iusertext;
    boolean valido;
    private ImageButton ibpreferencias;

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /**
         * Inicializamos todos los botones y textos que vamos a usar
         */

        iniciarSesion=findViewById(R.id.iniciarBoton);
        forgetPassword=findViewById(R.id.recordarcontrasenya);
        createAccount=findViewById(R.id.createAccount);
        usertext=findViewById(R.id.username);
        password=findViewById(R.id.password);
        ibpreferencias=findViewById(R.id.ibpreferencias);
        /**
         * Esto es para elegir el tema
         */

        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = prefs2.getString("theme", "auto");
        if (theme.equals("dark")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if (theme.equals("light")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
        /**
         * Esto es para el inicio de sesión
         */

        SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
        if(!prefs.getString("usuario", "").isEmpty()) {
            String email = prefs.getString("mail", "");
            String contrasenya = prefs.getString("contrasenya", "");
            if(!email.isEmpty() && !contrasenya.isEmpty()) {
                usertext.setText(email);
                password.setText(contrasenya);
                showProgress();
                executeCall(this);
            }
        }
        /**
         * Este es el onclick para crear una cuenta y te manda a la actividad New user Activity
         */

        createAccount.setOnClickListener(v -> {
            Intent intent=new Intent(this, NewUserActivity.class);
            intent.putExtra("username",usertext.getText().toString());
            startActivity(intent);
        });
        /**
         * Es para poder cambiar la contraseña si se le ha olvidado al usuario y mediante el intent te manda a la actividad UpdateCuentaActivity
         */
        forgetPassword.setOnClickListener(v -> {
            Intent intent=new Intent(this, UpdateCuentaActivity.class);
            startActivity(intent);
        });
        /**
         * es para iniciar sesion en una cuenta ya creada
         */

        iniciarSesion.setOnClickListener(v->{
            showProgress();
            executeCall(this);
        });
        /**
         * Para cambiar las preferencias de cambio de tema
         */
        ibpreferencias.setOnClickListener(v -> {
            Intent intent=new Intent(this, Preference.class);
            startActivity(intent);
        });
    }

    /**
     * Realiza cambios en segundo plano
     */
    @Override
    public void doInUI() {
        if (usuario != null) {
            SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            Conversor conversor = Conversor.getConversor();
            editor.putString("usuario", conversor.toJson(usuario));
            editor.putString("mail",usertext.getText().toString());
            editor.putString("contrasenya", password.getText().toString());
            editor.apply();
            editor.commit();
            Intent intent = new Intent(this, SecondScreen.class);
            startActivity(intent);
        } else{
            Toast.makeText(this,"Error de inicio de sesión",Toast.LENGTH_LONG).show();
        }
        hideProgress();
    }

    /**
     * Realiza cambios en segundo plano que se muestran en la pantalla
     */

    @Override
    public void doInBackground() {
        Credenciales credenciales = new Credenciales(
                usertext.getText().toString(),
                password.getText().toString()
        );
        try {

            usuario = Connector.getConector().post(Usuario.class, credenciales, "login/");

        }catch (NullPointerException pointerException){
            Log.d(MainActivity.class.getSimpleName(),"El email o la contraseña no son validos");


        }

    }

}