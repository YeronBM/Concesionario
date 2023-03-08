package aybm.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import aybm.concesionario.db.DBCoches;
import aybm.concesionario.entidades.Coche;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.xml_spinner_marcas);
        //List<Coche> listaCoches = llenarSpinnerMarcas();
        List<String> listaMarcas = llenarSpinnerMarcas2();

        //ArrayAdapter<Coche> adapterMarcas = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCoches);
        ArrayAdapter<String> adapterMarcas = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaMarcas);
        spinner.setAdapter(adapterMarcas);
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
    }

    private void llenarSpinnerModelo(){}
    private void llenarSpinnerColor(){}
}

