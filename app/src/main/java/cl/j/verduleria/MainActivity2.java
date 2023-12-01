package cl.j.verduleria;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    EditText et1, et2;
    Button bt1, bt2 ,bt4;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    double resultado = 0.0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et1 = findViewById(R.id.TxtVerdura);
        et2 = findViewById(R.id.TxtEctarias);
        bt1 = findViewById(R.id.BtCalcular);
        bt2 = findViewById(R.id.BtLimpiar);
        bt4 = findViewById(R.id.BtBoleta);


        // Restaurar los datos ingresados previamente (si hay)
        if (savedInstanceState != null) {
            resultado = savedInstanceState.getDouble("resultado", 0.0);
            et1.setText(savedInstanceState.getString("et1", ""));
            et2.setText(savedInstanceState.getString("et2", ""));
        }

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los valores ingresados por el usuario
                String valor1 = et1.getText().toString();
                String valor2 = et2.getText().toString();

                // Realizar la multiplicación
                resultado = Double.parseDouble(valor1) * Double.parseDouble(valor2);

                // Crear un Intent para pasar los datos a MainActivity3
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("resultado", resultado);

                // Iniciar MainActivity3
                startActivity(intent);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Limpiar los EditText
                et1.setText("");
                et2.setText("");
                resultado = 0.0;  // Reiniciar el resultado
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("resultado", resultado);
        outState.putString("et1", et1.getText().toString());
        outState.putString("et2", et2.getText().toString());

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("Boleta").setValue(bt4.getText().toString());
                databaseReference.child("Boleta").setValue(bt4.getText().toString());

            }
        });
    }
}