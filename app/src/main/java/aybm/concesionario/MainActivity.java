package aybm.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aybm.concesionario.db.DBCoches;
import aybm.concesionario.entidades.Coche;

public class MainActivity extends AppCompatActivity {
    Spinner spinner_marcas, spinner_modelo, spinner_color;

    Button botonBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_marcas = findViewById(R.id.xml_spinner_marcas);
        spinner_modelo = findViewById(R.id.xml_spinner_modelo);
        spinner_color = findViewById(R.id.xml_spinner_color);

        botonBuscar = findViewById(R.id.boton_Buscar);

        //List<Coche> listaCoches = llenarSpinnerMarcas();
        //ArrayAdapter<Coche> adapterMarcas = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCoches);
        List<String> listaMarcas = llenarSpinnerMarcas2();
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
                Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Prescincidble
    private List<Coche> llenarSpinnerMarcas() {
        List<Coche> listaCoches = new ArrayList<>();
        DBCoches dbCoches = new DBCoches(MainActivity.this);
        Cursor cursor = dbCoches.mostrarMarcas();
        if (cursor != null) {
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
            }
        }
        dbCoches.close();
        return listaCoches;
    }

    private List<String> llenarSpinnerMarcas2() {
        List<String> listaMarcas = new ArrayList<>();
        DBCoches dbCoches = new DBCoches(MainActivity.this);
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
    }//llenarSpinnerMarcas2

    private List<String> llenarSpinnerModelo() {
        List<String> listaModelos = new ArrayList<>();
        DBCoches dbCoches = new DBCoches(MainActivity.this);
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

    private List<String> llenarSpinnerColor() {
        List<String> listaColores = new ArrayList<>();
        DBCoches dbCoches = new DBCoches(MainActivity.this);
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

