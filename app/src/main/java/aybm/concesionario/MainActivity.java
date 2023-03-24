package aybm.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bt_buscarCoche,  bt_agregarCoche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bt_buscarCoche = findViewById(R.id.xml_main_bt_buscarCoche);
        bt_agregarCoche = findViewById(R.id.xml_main_bt_agregarCoche);

        bt_buscarCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BuscarCoche.class);
                startActivity(intent);
            }
        });

        bt_agregarCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgregarCoche.class);
                startActivity(intent);
            }
        });

    }
}