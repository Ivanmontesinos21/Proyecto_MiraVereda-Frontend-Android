package com.example.miravereda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.miravereda.API.Connector;
import com.example.miravereda.base.BaseActivity;
import com.example.miravereda.base.CallInterface;
import com.example.miravereda.base.MyProgressBar;
import com.example.miravereda.model.ContenidoAudiovisual;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.PropertyReference0Impl;

public class SecondScreen extends BaseActivity implements View.OnClickListener, CallInterface {

    ActivityResultLauncher<Intent> activityResultLauncher;
    private RecyclerView recyclerView;

    private ImageButton imageButton;



    private RecyclerViewAdapterCartelera recyclerViewAdapterCartelera;

    private List<ContenidoAudiovisual> contenidosAudiovisuales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result->{
            if (result.getResultCode()==RESULT_CANCELED)
                Toast.makeText(this,"No se ha podido entar a los detalles de las peliculas",Toast.LENGTH_LONG).show();

        });
        recyclerView=findViewById(R.id.recyclerCartelera);

        recyclerView.setOnClickListener(this);
        imageButton=findViewById(R.id.ircarrito);
        imageButton.setOnClickListener(v->{
            Intent intent=new Intent(getApplicationContext(), CarritoActivity.class);
            activityResultLauncher.launch(intent);
        });

        executeCall(this);
    }

    @Override
    public void doInUI() {
        hideProgress();
        recyclerView=findViewById(R.id.recyclerCartelera);
        recyclerViewAdapterCartelera=new RecyclerViewAdapterCartelera(this, contenidosAudiovisuales);
        recyclerView.setAdapter(recyclerViewAdapterCartelera);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void doInBackground() {
        contenidosAudiovisuales = Connector.getConector().getAsList(ContenidoAudiovisual.class, "pelicula/");
        System.out.println(contenidosAudiovisuales.size());

    }

    public void showProgress(){
        progressBar.show();
    }

    public void hideProgress(){
        progressBar.hide();
    }

    /***
     * Metodo que te enviara a la actividad de ver las peliculas en mas detalle
     * @param v La vista que se ha elegido.
     */

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(), VistaPeliculaActivity.class);
        activityResultLauncher.launch(intent);

    }
}