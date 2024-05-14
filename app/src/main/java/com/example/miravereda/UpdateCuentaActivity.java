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

public class UpdateCuentaActivity extends AppCompatActivity {

    private Button update;

    private TextInputEditText newusername;

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
        newusername=findViewById(R.id.newUsername);
        newpassword=findViewById(R.id.newPassword);

        ActivityResultLauncher<Intent> activityResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result->{
            if (result.getResultCode()==RESULT_CANCELED)
                Toast.makeText(this,"Volviendo atras",Toast.LENGTH_LONG);
            else if (result.getResultCode()==RESULT_OK) {
                Toast.makeText(this, "Cambiando datos de usuario", Toast.LENGTH_LONG).show();
            }
        });
        update.setOnClickListener(v->{
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            activityResultLauncher.launch(intent);
        });




    }
}