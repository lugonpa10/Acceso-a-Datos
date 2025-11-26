package com.example.examen33;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class insertar extends AppCompatActivity {

    boolean muestra=false;

    Toolbar tb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        final String[] provincias=getResources().getStringArray(R.array.nacionalidades);
      Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, provincias);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int i, long l) {
// Se usa la variable booleana para impedir que muestre el resultado
// seleccionado por defecto cuando se accede al activity. Solo mostramos
// cuando seleccionamos una opción.
                if (!muestra) muestra=true;
                else {
                    Toast.makeText(getApplicationContext(), "Has selecionado: " +
                                    adapterView.getItemAtPosition(i).toString(),
                            Toast.LENGTH_SHORT).show();
                    setTitle(adapterView.getItemAtPosition(i).toString());
                }
            }
            // Se ejecuta cuando se pulsa fuera del Spinner: cuandono se selecciona nada
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        tb2 = findViewById(R.id.toolbar2);
        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(tb2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_insertar,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.atras){
           onBackPressed();
            return true;
        }else if (id==R.id.guardar) {
            EditText editNombre = findViewById(R.id.editNombre);
            EditText editEdad = findViewById(R.id.editEdad);
            Spinner spinner = findViewById(R.id.spinner);
            Random random = new Random();
            int idPersonaje = random.nextInt(1000);
            String nombreCompleto = "NombreAleatorio" + random.nextInt(100);
            String descripcion = "Sin descripción";
            String sexo = "hombre";

            String nombre = editNombre.getText().toString();
            int edad =  Integer.parseInt(editEdad.getText().toString());
            String nacionalidad = spinner.getSelectedItem().toString();

            Intent intentSec = new Intent(insertar.this,MainActivity.class);
            intentSec.putExtra("nombre",nombre);
            intentSec.putExtra("edad",edad);
            intentSec.putExtra("nacionalidad",nacionalidad);
            intentSec.putExtra("nombreCompleto",nombreCompleto);
            intentSec.putExtra("descripcion",descripcion);
            intentSec.putExtra("sexo",sexo);
            setResult(RESULT_OK,intentSec);
            finish();


            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

