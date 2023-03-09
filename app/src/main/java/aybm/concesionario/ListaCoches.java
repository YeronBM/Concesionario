package aybm.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ListaCoches extends AppCompatActivity {

    ListView listaCoches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_coches);

        listaCoches = findViewById(R.id.listview_listaCoches);
    }

    public void llenarListaCoches(){


    }
}