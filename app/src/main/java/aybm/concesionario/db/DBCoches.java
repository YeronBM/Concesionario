package aybm.concesionario.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DBCoches extends DBHelper {
    public DBCoches(@Nullable Context context) {
        super(context);
    }

    public Cursor mostrarMarcas() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            //Cursor filas_marcas = db.rawQuery("SELECT * FROM " + TABLA_COCHES + "", null);
            Cursor filas_marcas = db.rawQuery("SELECT DISTINCT marca from tabla_coches", null);
            if (filas_marcas.moveToFirst()) {
                return filas_marcas;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Cursor mostrarModelos(String marca){
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor filas_modelos = db.rawQuery("SELECT DISTINCT modelo from tabla_coches where marca = '"+marca+"'", null);
            if (filas_modelos.moveToFirst()) {
                return filas_modelos;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Cursor mostrarColores(){
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor filas_colores = db.rawQuery("SELECT DISTINCT color from tabla_coches", null);
            if (filas_colores.moveToFirst()) {
                return filas_colores;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
