package aybm.concesionario.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_concesionario.db";
    private static final int DB_VERSION = 1;
    protected static final String TABLA_COCHES = "tabla_coches";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        crearTablasiniciales(db);
        crearInsertsinciales(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_COCHES);
        onCreate(db);
    }

    private void crearTablasiniciales(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_COCHES + "(" +
                "id INTEGER primary key autoincrement NOT NULL," +
                "marca text NOT NULL," +
                "modelo text NOT NULL," +
                "color text NOT NULL," +
                "dni_propietario text not null)");
    }

    private void crearInsertsinciales(SQLiteDatabase db) {
        //https://www.motor.es/marcas/
        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Alfa Romeo','Alfa Romeo Giulia', 'Rojo','39456703L')");
        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Alfa Romeo','Alfa Romeo Stelvio', 'Azul','39456703L')");
        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Alfa Romeo','Alfa Romeo Tonale', 'Verde','39456703L')");

        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Audi','Audi A1', 'Gris','12345678A')");
        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Audi','Audi A3', 'Blanco','45678123C')");
        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Audi','Audi A3', 'Negro','45678123C')");
    }
}
