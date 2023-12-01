package cl.j.verduleria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        double resultado = getIntent().getDoubleExtra("resultado", 0.0);

        EditText editTextResultado = findViewById(R.id.BtResultados);
        editTextResultado.setText(String.valueOf(resultado));

        bt3 = findViewById(R.id.BtVolver);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}