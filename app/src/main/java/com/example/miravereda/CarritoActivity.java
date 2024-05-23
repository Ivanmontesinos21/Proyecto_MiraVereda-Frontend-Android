package com.example.miravereda;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miravereda.API.Connector;
import com.example.miravereda.base.BaseActivity;
import com.example.miravereda.base.CallInterface;
import com.example.miravereda.model.AnyadirAlCarro;
import com.example.miravereda.model.ContenidoAudiovisual;
import com.example.miravereda.model.Credenciales;

import java.util.List;

public class CarritoActivity extends BaseActivity implements CallInterface {

    private RecyclerView recyclerView;

    private RecyclerViewAdapterCarrito recyclerViewAdapterCarrito;

    private TextView precio;

    private List<ContenidoAudiovisual> peliculas;

    Credenciales credenciales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrito);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView=findViewById(R.id.recyclerViewcarrito);
        precio=findViewById(R.id.preciocarrito);

        SharedPreferences prefs=getSharedPreferences("usuario", MODE_PRIVATE);
        String mail=prefs.getString("mail",null);
        String contrasenya=prefs.getString("contrasenya",null);
        credenciales = new Credenciales(mail, contrasenya);

        executeCall(this);
    }

    /**
     *
     * @param view Le pasamos una vista que es donde va a funcionar el metodo
     */

    public void comprar(View view) {
        showProgress();
        CarritoActivity activity = this;
        executeCall(new CallInterface() {
            int itemsComprados = 0;

            @Override
            public void doInBackground() {
                itemsComprados = Connector.getConector().post(Integer.class, credenciales, "pagar/");
            }

            @Override
            public void doInUI() {
                if(itemsComprados > 0) {
                    Toast.makeText(activity, "La compra ha sido completada exitosamente", Toast.LENGTH_LONG).show();
                    executeCall(activity);
                }
                else {
                    activity.hideProgress();
                }
            }
        });
    }

    /**
     *
     * @param id le pasamos la id para asi que se al eliminar del carrito vayamos directamente al que queremos por la id
     */

    public void quitarDelCarrito(int id) {
        showProgress();
        CarritoActivity activity = this;
        executeCall(new CallInterface() {
            @Override
            public void doInBackground() {
                AnyadirAlCarro aac = new AnyadirAlCarro(credenciales.getEmail(), credenciales.getContrasenya(), id);
                Connector.getConector().delete2(aac, "carrito/");
            }

            @Override
            public void doInUI() {
                executeCall(activity);
            }
        });
    }

    @Override
    public void doInBackground() {
        peliculas= Connector.getConector().postAsList(ContenidoAudiovisual.class, credenciales, "carrito/ver/");
    }

    @Override
    public void doInUI() {
        hideProgress();
        recyclerViewAdapterCarrito=new RecyclerViewAdapterCarrito(this,peliculas);
        recyclerView.setAdapter(recyclerViewAdapterCarrito);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapterCarrito.notifyDataSetChanged();
        double precioNumber = (double)peliculas.stream().mapToInt(ContenidoAudiovisual::getPrecioConTarifa).sum() / 100.0;
        precio.setText("Total: " + precioNumber + "€");

    }
}