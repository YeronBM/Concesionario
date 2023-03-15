package aybm.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aybm.concesionario.db.DBCoches;
import aybm.concesionario.entidades.Coche;
import aybm.concesionario.entidades.CocheDetallado;

public class ListaCoches extends AppCompatActivity {

    ListView listaCoches;

    DBCoches dbCoches;

    ListAdapter adapter_lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_coches);

        listaCoches = findViewById(R.id.listview_listaCoches);

        dbCoches = new DBCoches(ListaCoches.this);
        SQLiteDatabase db = dbCoches.getReadableDatabase();

        tomarDatosIntent(listaCoches);

        listaCoches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListaCoches.this, CocheDetallado.class);
                startActivity(intent);

            }
        });

    }

    public void llenarListaCoches() {


    }

    public void tomarDatosIntent(ListView listview) {
        Bundle intent = getIntent().getExtras();
        String valorSpinner1 = intent.getString("valor_spinner_marcas");
        String valorSpinner2 = intent.getString("valor_spinner_modelo");
        String valorSpinner3 = intent.getString("valor_spinner_colores");

        List<Coche> listaCoches = new ArrayList<>();


        dbCoches = new DBCoches(ListaCoches.this);
        SQLiteDatabase db = dbCoches.getReadableDatabase();
        Cursor cursor = dbCoches.mostrarCoches_marca_modelo_color_especificos(valorSpinner1, valorSpinner2, valorSpinner3);
        if (cursor.moveToFirst()) {
            do {
                Coche coche = new Coche();
                coche.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                coche.setMarca(cursor.getString(cursor.getColumnIndexOrThrow("marca")));
                coche.setModelo(cursor.getString(cursor.getColumnIndexOrThrow("modelo")));
                coche.setColor(cursor.getString(cursor.getColumnIndexOrThrow("color")));
                coche.setDni_propietario(cursor.getString(cursor.getColumnIndexOrThrow("dni_propietario")));
                listaCoches.add(coche);
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "No se han encontrado Coincidencias", Toast.LENGTH_SHORT).show();
        }
        Adapter adapter = new Adapter(this, R.layout.single_item_listacoches, listaCoches);
        listview.setAdapter(adapter);


    }//tomarDatosIntent
}