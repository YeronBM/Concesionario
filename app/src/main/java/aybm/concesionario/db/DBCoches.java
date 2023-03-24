package aybm.concesionario.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBCoches extends DBHelper {
    private SQLiteDatabase db = this.getReadableDatabase();

    /**
     * Constructor por defecto de la clase DBCoches.
     *
     * La clase DBCoches contiene los métodos de consulta para la base de datos.
     * @param context
     */
    public DBCoches(@Nullable Context context) {
        super(context);
    }

    /**
     * Método que realiza una consulta a la base de datos y devuelve un Cursor con los valores DISTINCT de la columna 'marca'.
     * @return Cursor
     */
    public Cursor mostrarMarcas() {
        try {
            //db = this.getReadableDatabase();
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

    /**
     * Método que realiza una consulta a la base de datos y devuelve un Cursor con los valores DISTINCT de la columna 'modelo'.
     * @return Cursor
     */
    public Cursor mostrarModelos() {
        try {
            db = this.getReadableDatabase();
            Cursor filas_modelos = db.rawQuery("SELECT DISTINCT modelo from tabla_coches", null);
            if (filas_modelos.moveToFirst()) {
                return filas_modelos;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Método que realiza una consulta a la base de datos y devuelve un Cursor con los valores DISTINCT de la columna 'color'.
     * @return Cursor
     */
    public Cursor mostrarColores() {
        try {
            db = this.getReadableDatabase();
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

    /**
     * Método que recibe los parámetos 'Marca', 'Modelo' y 'Color' y que realiza una consulta aplicando la cláusula Where para cada pareja de columnas y valores.
     *
     *
     * SELECT *  from tabla_coches where tabla_coches.marca='marca' and tabla_coches.modelo='modelo' and tabla_coches.color='color;
     *
     * @param marca -- String que indica el valor de la marca para la consulta.
     * @param modelo -- String que indica el valor del modelo para la consulta.
     * @param color -- String que indica el valor del color para la consulta.
     * @return Cursor -- Que contiene las filas encontradas en la consulta
     */
    public Cursor mostrarCoches_marca_modelo_color_especificos(String marca, String modelo, String color) {
        try {
            db = this.getReadableDatabase();

            Cursor filasConsulta = db.rawQuery("SELECT *  from tabla_coches where tabla_coches.marca='" + marca + "' " +
                    "and tabla_coches.modelo='" + modelo + "' and tabla_coches.color='" + color + "'", null);

            if (filasConsulta.moveToFirst()) {
                return filasConsulta;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Método que recibe por parámetro el id de un coche en la tabla 'tabla_coches' y devuelve un Cursor con las filas cuyo id es el pasado por parámetro.
     *
     * SELECT *  from tabla_coches where  tabla_coches.id='id';
     *
     * @param id  -- Valor de la fila que se busca en la columna id.
     * @return Cursor --  Devuelde los resultados de la consulta
     */
    public Cursor single_Datos_Coche(int id){
        try {
            db = this.getReadableDatabase();

            Cursor filasConsulta = db.rawQuery("SELECT *  from tabla_coches where  tabla_coches.id='" + id + "'", null);

            if (filasConsulta.moveToFirst()) {
                return filasConsulta;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Método que recibe por parámetros los valores de marca, modelo color y dni_propietario y  ejecuta la consulta:
     *
     * insert into tabla_coches(marca, modelo, color, dni_propietario)values('marca','modelo','color','dni_Propietario');
     *
     * @param marca
     * @param modelo
     * @param color
     * @param dni_Propietario
     */
    public void agregarCocheADB(String marca, String modelo, String color, String dni_Propietario){
        db.execSQL("insert into tabla_coches(marca, modelo, color, dni_propietario)values('"+marca+"','"+modelo+"','"+color+"','"+dni_Propietario+"')");
        
    }

}
