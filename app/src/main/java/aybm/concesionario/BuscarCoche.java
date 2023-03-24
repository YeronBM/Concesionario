package aybm.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import aybm.concesionario.db.DBCoches;

public class BuscarCoche extends AppCompatActivity {
    private Spinner spinner_marcas, spinner_modelo, spinner_color;

    Button botonBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Elementos del xml
        spinner_marcas = findViewById(R.id.xml_spinner_marcas);
        spinner_modelo = findViewById(R.id.xml_spinner_modelo);
        spinner_color = findViewById(R.id.xml_spinner_color);

        botonBuscar = findViewById(R.id.boton_Buscar);

        //Poblar Spinner
        List<String> listaMarcas = llenarSpinnerMarcas();
        ArrayAdapter<String> adapterMarcas = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaMarcas);
        spinner_marcas.setAdapter(adapterMarcas);

        List<String> listaModelos = llenarSpinnerModelo();
        ArrayAdapter<String> adapterModelos = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaModelos);
        spinner_modelo.setAdapter(adapterModelos);

        List<String> listaColores = llenarSpinnerColor();
        ArrayAdapter<String> adapterColores = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaColores);
        spinner_color.setAdapter(adapterColores);


        //OnClick
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valor_spinner_marcas = spinner_marcas.getSelectedItem().toString();
                String valor_spinner_modelo = spinner_modelo.getSelectedItem().toString();
                String valor_spinner_colores = spinner_color.getSelectedItem().toString();

                Intent intent = new Intent(BuscarCoche.this, ListaCoches.class);
                Bundle bundleDatos = new Bundle();
                bundleDatos.putString("valor_spinner_marcas", valor_spinner_marcas);
                bundleDatos.putString("valor_spinner_modelo", valor_spinner_modelo);
                bundleDatos.putString("valor_spinner_colores", valor_spinner_colores);

                intent.putExtras(bundleDatos);

                startActivity(intent);

            }
        });

    }


    /**
     * Llama al método llenar marcas de la clase DBCoches y llena un ArrayList con los valores del Cursor que devuelve.
     * @return List<String> -- Lista que contiene las diferentes marcas.
     */
    private List<String> llenarSpinnerMarcas() {
        List<String> listaMarcas = new ArrayList<>();
        listaMarcas.add("Todos");
        DBCoches dbCoches = new DBCoches(BuscarCoche.this);
        Cursor cursor = dbCoches.mostrarMarcas();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String marca = cursor.getString(cursor.getColumnIndexOrThrow("marca"));
                    if (!listaMarcas.contains(marca)) {
                        listaMarcas.add(marca);
                    }


                } while (cursor.moveToNext());
            }
        }
        dbCoches.close();
        return listaMarcas;
    }//llenarSpinnerMarcas


    /**
     * Llama al método llenarModelo de la clase DBCoches y crea un List con esos valores.
     * @return List<String>
     */
    private List<String> llenarSpinnerModelo() {
        List<String> listaModelos = new ArrayList<>();
        DBCoches dbCoches = new DBCoches(BuscarCoche.this);
        Cursor cursor = dbCoches.mostrarModelos();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String modelo = cursor.getString(cursor.getColumnIndexOrThrow("modelo"));
                    if (!listaModelos.contains(modelo)) {
                        listaModelos.add(modelo);
                    }
                } while (cursor.moveToNext());
            }
        }
        dbCoches.close();
        return listaModelos;

    }

    /**
     *  Llama al método llenarColor de la clase DBCoches y crea un List con esos valores.
     * @return
     */
    private List<String> llenarSpinnerColor() {
        List<String> listaColores = new ArrayList<>();
        DBCoches dbCoches = new DBCoches(BuscarCoche.this);
        Cursor cursor = dbCoches.mostrarColores();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String color = cursor.getString(cursor.getColumnIndexOrThrow("color"));
                    if (!listaColores.contains(color)) {
                        listaColores.add(color);
                    }
                } while (cursor.moveToNext());
            }
        }
        dbCoches.close();
        return listaColores;
    }
}

