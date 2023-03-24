package aybm.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import aybm.concesionario.db.DBCoches;

public class AgregarCoche extends AppCompatActivity {

    private EditText et_marca, et_modelo, et_color, et_dniPropietario;
    private Button bt_agregarCocheDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_coche);

        et_marca = findViewById(R.id.xml_agregarCoche_et_marca);
        et_modelo = findViewById(R.id.xml_agregarCoche_et_modelo);
        et_color = findViewById(R.id.xml_agregarCoche_et_color);
        et_dniPropietario = findViewById(R.id.xml_agregarCoche_et_dniPropietario);

        bt_agregarCocheDB = findViewById(R.id.xml_agregarCoche_bt_agregarCocheDB);

        bt_agregarCocheDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valor_editText_marcas = et_marca.getText().toString();
                String valor_editText_modelo = et_modelo.getText().toString();
                String valor_editText_colores = et_color.getText().toString();
                String valor_editText_dniPropietario = et_dniPropietario.getText().toString();

                DBCoches dbCoches = new DBCoches(getApplicationContext());
                dbCoches.agregarCocheADB(valor_editText_marcas, valor_editText_modelo, valor_editText_colores, valor_editText_dniPropietario);

                String mensaje = "Coche agregado a la base de datos";
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();


            }
        });

    }
}