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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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

        iniciarSesion=findViewById(R.id.iniciarBoton);
        forgetPassword=findViewById(R.id.recordarcontrasenya);
        createAccount=findViewById(R.id.createAccount);
        usertext=findViewById(R.id.username);
        password=findViewById(R.id.password);
        ibpreferencias=findViewById(R.id.ibpreferencias);

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

        ActivityResultLauncher<Intent> activityResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result->{
           if (result.getResultCode()==RESULT_CANCELED)
               Toast.makeText(this,"Volviendo atras",Toast.LENGTH_LONG).show();
           else if (result.getResultCode()==RESULT_OK){
               Intent intent= result.getData();
               String username=intent.getExtras().toString();
               Toast.makeText(this,"Bienvenido " + username,Toast.LENGTH_LONG).show();
           }
        });

        createAccount.setOnClickListener(v -> {
            Intent intent=new Intent(this, NewUserActivity.class);
            intent.putExtra("username",usertext.getText().toString());
            activityResultLauncher.launch(intent);
        });
        forgetPassword.setOnClickListener(v -> {
            Intent intent=new Intent(this, UpdateCuentaActivity.class);
            activityResultLauncher.launch(intent);
        });

        iniciarSesion.setOnClickListener(v->{
            showProgress();
            executeCall(this);
        });
        ibpreferencias.setOnClickListener(v -> {
            Intent intent=new Intent(this, Preference.class);
            activityResultLauncher.launch(intent);
        });
    }

    @Override
    public void doInUI() {
        hideProgress();
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
            Toast.makeText(this,"El usuario con el mail: " + usertext.getText().toString() +  " no existe",Toast.LENGTH_LONG).show();
        }
    }

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