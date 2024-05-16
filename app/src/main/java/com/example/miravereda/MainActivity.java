package com.example.miravereda;

import android.content.Intent;
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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button iniciarSesion;

    private Button forgetPassword;

    private Button createAccount;

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
            Intent intent=new Intent(getApplicationContext(), NewUserActivity.class);
            intent.putExtra("username",usertext.getText().toString());
            activityResultLauncher.launch(intent);
        });
        forgetPassword.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(), UpdateCuentaActivity.class);
            activityResultLauncher.launch(intent);
        });


        if (iniciarSesion(usertext.getText().toString(),password.getText().toString())){
            iniciarSesion.setOnClickListener(v->{
                Intent intent=new Intent(getApplicationContext(), SecondScreen.class);
                activityResultLauncher.launch(intent);
            });
        }else
            Toast.makeText(this,"No se puede iniciar Sesion",Toast.LENGTH_LONG).show();
    }

    /***
     * Metodo que devolvera un booleano que comrpobara si se ha podido iniciar sesion
     * @param username sera el username recogido en el campo de username
     * @param contraseña la contraseña sera la recogida en el campo contraseña
     * @return devuelve un booleano para comprobar el inicio de sesion
     */

    public static boolean iniciarSesion(String username,String contraseña){

        return false;

    }
}