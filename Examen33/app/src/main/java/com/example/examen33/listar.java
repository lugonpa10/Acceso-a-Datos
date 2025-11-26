package com.example.examen33;



import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class listar extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<Personaje> personajes;
    ArrayAdapter<String> adapter;
    ArrayList<String> nombresNacionalidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        toolbar = findViewById(R.id.toolbarListar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Listar Personajes");

        listView = findViewById(R.id.listViewPersonajes);

        // Traemos la lista de personajes, incluyendo los insertados
        personajes = (ArrayList<Personaje>) Personaje.getDatos();

        // Creamos lista de strings "Nombre - Nacionalidad"
        nombresNacionalidades = new ArrayList<>();
        for (Personaje p : personajes) {
            nombresNacionalidades.add(p.getNombre() + " - " + p.getNacionalidad());
        }

        // Adapter para el ListView
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice,
                nombresNacionalidades);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Restaurar items previamente seleccionados si vienen de MainActivity
        boolean[] seleccionados = getIntent().getBooleanArrayExtra("seleccionados");
        if (seleccionados != null) {
            for (int i = 0; i < seleccionados.length; i++) {
                listView.setItemChecked(i, seleccionados[i]);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.volver) {
            // Volver sin guardar
            finish();
            return true;
        } else if (item.getItemId() == R.id.guardar) {
            // Guardar selección y volver a MainActivity
            boolean[] seleccion = new boolean[personajes.size()];
            for (int i = 0; i < personajes.size(); i++) {
                seleccion[i] = listView.isItemChecked(i);
            }

            Intent intent = new Intent();
            intent.putExtra("seleccionados", seleccion);
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
