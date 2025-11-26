package com.example.examen33;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Personaje> personajes;


    RecyclerView.LayoutManager miLayoutManager;
    RecyclerView rv;
    Adaptador adaptador;
Toolbar tb;
    private boolean esHorizontal = false; // false = vertical, true = horizontal




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        personajes = (ArrayList<Personaje>) Personaje.getDatos();

        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.toolbar_title));
        actionBar.setSubtitle("Personajes: " + personajes.size());
        rv = findViewById(R.id.rvPersonajes);
        adaptador = new Adaptador(personajes);
        rv.setAdapter(adaptador);
        rv.setLayoutManager(new LinearLayoutManager(this));








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){

            int id = data.getIntExtra("id", 0);
            String nombreCompleto = data.getStringExtra("nombreCompleto");
            String descripcion = data.getStringExtra("descripcion");
            String sexo = data.getStringExtra("sexo");
            String nombre = data.getStringExtra("nombre");
            int edad = data.getIntExtra("edad", 0);
            String nacionalidad = data.getStringExtra("nacionalidad");
            int imagen = data.getIntExtra("imagen", R.drawable.homer);



            Personaje p = new Personaje( id,  nombre,  nombreCompleto,  edad,
             sexo,  nacionalidad,  descripcion,  imagen);

            personajes.add(p);
            adaptador.notifyItemInserted(personajes.size() -1);


        }
    }


        @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.insertar){
            Intent intent = new Intent(MainActivity.this,insertar.class);
            startActivityForResult(intent,1);
            return true;
        }else if (id==R.id.borrar){
int marcado = adaptador.posMarcadas.get(0);
personajes.remove(marcado);
adaptador.notifyItemRemoved(marcado);
getSupportActionBar().setSubtitle("Personajes: " + personajes.size());

            return true;
        }else if (id==R.id.listar){


            return true;
        }else if (id == R.id.hor){
            // Alternar orientación
            esHorizontal = !esHorizontal;

            LinearLayoutManager miLayoutManager;
            if (esHorizontal) {
                miLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            } else {
                miLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            }

            rv.setLayoutManager(miLayoutManager);
            return true;
        }

            return super.onOptionsItemSelected(item);
    }
}
