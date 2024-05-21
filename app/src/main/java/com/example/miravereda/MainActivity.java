package com.example.miravereda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
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
            executeCall(this);
        });
    }

    @Override
    public void doInUI() {
        hideProgress();
        SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
        SharedPreferences.Editor editor= prefs.edit();
        Conversor conversor = Conversor.getConversor();
        editor.putString("usuario", conversor.toJson(usuario));
        editor.commit();
        Intent intent=new Intent(this, SecondScreen.class);
        startActivity(intent);
    }

    @Override
    public void doInBackground() {
        Credenciales credenciales = new Credenciales(
                usertext.getText().toString(),
                password.getText().toString()
        );
        usuario = Connector.getConector().post(Usuario.class, credenciales, "login/");
    }

}