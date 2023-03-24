package aybm.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import aybm.concesionario.R;
import aybm.concesionario.db.DBCoches;
import aybm.concesionario.entidades.Coche;

public class CocheDetallado extends AppCompatActivity {

    DBCoches dbCoches;
    SQLiteDatabase db;

    Coche coche = new Coche();

    private String marca, modelo, color, dni_propietario;
    TextView xml_cochedetallado_tv_nombreCoche, tv_Marca_value, tv_Modelo_value, tv_Color_value, tv_DNIPropietario_value;
    ImageView imagen_coche;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coche_detallado);

        dbCoches = new DBCoches(getApplicationContext());
        db = dbCoches.getReadableDatabase();

        xml_cochedetallado_tv_nombreCoche = findViewById(R.id.xml_cochedetallado_tv_nombreCoche);
        tv_Marca_value = findViewById(R.id.tv_Marca_value);
        tv_Modelo_value = findViewById(R.id.tv_Modelo_value);
        tv_Color_value = findViewById(R.id.tv_Color_value);
        tv_DNIPropietario_value = findViewById(R.id.tv_DNIPropietario_value);
        imagen_coche = findViewById(R.id.xml_cochedetallado_iv_imagenCoche);

        tomarDatosdeDB(tomarDatosIntent());
        poblar_activity();
    }

    private int tomarDatosIntent() {
        Bundle bundle_datos = getIntent().getExtras();
        int id_coche = bundle_datos.getInt("valor_id_coche");
        return id_coche;
    }

    private void tomarDatosdeDB(int id_coche) {
        Cursor cursor = dbCoches.single_Datos_Coche(id_coche);

        if (cursor.moveToFirst()) {
            do {
                coche.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                coche.setMarca(cursor.getString(cursor.getColumnIndexOrThrow("marca")));
                coche.setModelo(cursor.getString(cursor.getColumnIndexOrThrow("modelo")));
                coche.setColor(cursor.getString(cursor.getColumnIndexOrThrow("color")));
                coche.setDni_propietario(cursor.getString(cursor.getColumnIndexOrThrow("dni_propietario")));
            } while (cursor.moveToNext());
        }
    }

    private void poblar_activity() {
        String nombre_coche = coche.getModelo();
        xml_cochedetallado_tv_nombreCoche.setText(nombre_coche);

        tv_Marca_value.setText(coche.getMarca());
        tv_Modelo_value.setText(coche.getModelo());
        tv_Color_value.setText(coche.getColor());
        tv_DNIPropietario_value.setText(coche.getDni_propietario());

        poblarImagen(coche.getId());


    }

    private void poblarImagen(int id) {
        switch (id) {
            case 1:
                imagen_coche.setImageResource(R.drawable.alfa_romeo_giulia_rojo);
                break;
            case 2:
                imagen_coche.setImageResource(R.drawable.alfa_romeo_stelvio_azul);
                break;
            case 3:
                imagen_coche.setImageResource(R.drawable.alfa_romeo_tonale_verde);
                break;
            case 4:
                imagen_coche.setImageResource(R.drawable.audi_a1_gris);
                break;
            case 5:
                imagen_coche.setImageResource(R.drawable.audi_a3_azul);
                break;
            case 6:
                imagen_coche.setImageResource(R.drawable.alfa_romeo_giulia_rojo);
                break;
        }
    }


}