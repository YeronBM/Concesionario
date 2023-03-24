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
    /**
     * Método que se ejecuta al iniciar por primera vez la aplicación.
     */
    public void onCreate(SQLiteDatabase db) {
        crearTablasIniciales(db);
        crearInsertsInciales(db);
    }


    /**
     * Método que se ejecuta al cambiar el atributo DB_VERSION en la clase.
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_COCHES);
        onCreate(db);
    }

    /**
     * Método que crea la estructura inicial de la base de datos.
     * @param db
     */
    private void crearTablasIniciales(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_COCHES + "(" +
                "id INTEGER primary key autoincrement NOT NULL," +
                "marca text NOT NULL," +
                "modelo text NOT NULL," +
                "color text NOT NULL," +
                "dni_propietario text not null)");
    }

    /**
     * Método que puebla la base de datos con los datos iniciales.
     * @param db
     */
    private void crearInsertsInciales(SQLiteDatabase db) {
        //https://www.motor.es/marcas/
        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Alfa Romeo','Alfa Romeo Giulia', 'Rojo','39456703L')");
        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Alfa Romeo','Alfa Romeo Stelvio', 'Azul','39456703L')");
        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Alfa Romeo','Alfa Romeo Tonale', 'Verde','39456703L')");

        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Audi','Audi A1', 'Gris','12345678A')");
        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Audi','Audi A3', 'Blanco','45678123C')");
        db.execSQL("INSERT INTO " + TABLA_COCHES + "(marca, modelo, color, dni_propietario) values('Audi','Audi A3', 'Negro','45678123C')");
    }
}
