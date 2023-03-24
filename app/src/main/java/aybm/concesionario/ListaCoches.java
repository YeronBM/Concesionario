package aybm.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aybm.concesionario.db.DBCoches;
import aybm.concesionario.entidades.Coche;

public class ListaCoches extends AppCompatActivity {

    private ListView listaCoches;

    private DBCoches dbCoches;

    ArrayList<Integer> lista_id = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_coches);

        listaCoches = findViewById(R.id.listview_listaCoches);

        dbCoches = new DBCoches(ListaCoches.this);
        SQLiteDatabase db = dbCoches.getReadableDatabase();

        tomarDatosIntentyLlenarLv(listaCoches);

        listaCoches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListaCoches.this, CocheDetallado.class);
                Bundle bundleDatos = new Bundle();
                bundleDatos.putInt("valor_id_coche", lista_id.get(position));
                intent.putExtras(bundleDatos);
                startActivity(intent);

            }
        });

    }


    /**
     * Usando los valores pasados en el Bundle, ejecuta el método 'mostrarCoches_marca_modelo_color_especificos' de la clase DBCoche se puebla el Listview.
     * @param listview
     */
    public void tomarDatosIntentyLlenarLv(ListView listview) {
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
                lista_id.add(coche.getId());
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "No se han encontrado Coincidencias", Toast.LENGTH_SHORT).show();
        }
        Adapter adapter = new Adapter(this, R.layout.single_item_listacoches, listaCoches);
        listview.setAdapter(adapter);


    }//tomarDatosIntent
}