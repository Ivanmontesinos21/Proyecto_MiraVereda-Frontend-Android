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

import com.example.miravereda.API.Connector;
import com.example.miravereda.API.Conversor;
import com.example.miravereda.base.BaseActivity;
import com.example.miravereda.base.CallInterface;
import com.example.miravereda.model.Credenciales;
import com.example.miravereda.model.NewUsuario;
import com.example.miravereda.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateCuentaActivity extends BaseActivity implements CallInterface {

    private Button update;
    SharedPreferences preferences;

    private TextInputEditText mail;

    private TextInputEditText newpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_cuenta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        update=findViewById(R.id.updatebutton);
        mail=findViewById(R.id.recuperationmail);
        newpassword=findViewById(R.id.newPassword);

        update.setOnClickListener(v->{
            if (newpassword.toString().isEmpty()) {
                Toast.makeText(this, "Porfavor ponga una contraseña", Toast.LENGTH_LONG);
            }else{
                actualizarCuenta();


            }
        });

        ActivityResultLauncher<Intent> activityResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result->{
            if (result.getResultCode()==RESULT_CANCELED)
                Toast.makeText(this,"Volviendo atras",Toast.LENGTH_LONG);
            else if (result.getResultCode()==RESULT_OK) {
                Toast.makeText(this, "Cambiando datos de usuario", Toast.LENGTH_LONG).show();
            }
        });
        update.setOnClickListener(v->{
            executeCall(this);

        });

    }
    @Override
    public void doInBackground() {
        actualizarCuenta();

    }

    @Override
    public void doInUI() {
        Toast.makeText(this,"Usuario Actualizado con exito",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void actualizarCuenta() {
        Credenciales credenciales=new Credenciales(mail.getText().toString(),newpassword.getText().toString());
        Connector.getConector().put(credenciales,"resetpass/");
    }


}