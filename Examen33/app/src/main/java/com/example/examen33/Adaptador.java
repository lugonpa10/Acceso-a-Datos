package com.example.examen33;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {
    ArrayList<Personaje> personajes;
    public Adaptador(ArrayList<Personaje> personajes){
        this.personajes = personajes;
    }

    // Dentro de tu Adaptador
    boolean[] seleccionados;

    public void setSeleccionados(boolean[] seleccionados) {
        this.seleccionados = seleccionados;
        notifyDataSetChanged();
    }

    public boolean[] getSeleccionados() {
        if (seleccionados == null) {
            seleccionados = new boolean[personajes.size()];
        }
        return seleccionados;
    }

    ArrayList<Integer> posMarcadas = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda,parent,false);
        MyViewHolder mvh = new MyViewHolder(elemento);
        return mvh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
Personaje per = this.personajes.get(position);
holder.getNombre().setText(per.getNombre());
holder.getEdad().setText(String.valueOf(per.getEdad()));
holder.getPersonaje().setImageResource(per.getImagen());
if (!posMarcadas.contains(position)){
    holder.itemView.setBackgroundResource(R.color.ColorCelda);
}else {
   int index = posMarcadas.indexOf(position);

   if (index == 0){
       holder.itemView.setBackgroundResource(R.color.colorCeldaPulsada);
   }else if(index == 1){
       holder.itemView.setBackgroundResource(R.color.colorCeldaPulsada2);

   }
}

    }

    @Override
    public int getItemCount() {
        return  this.personajes.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nombre;
        private TextView edad;
         ImageView personaje;
         public MyViewHolder(View viewElemento){
             super(viewElemento);
             this.nombre = viewElemento.findViewById(R.id.txtNombre);
             this.edad = viewElemento.findViewById(R.id.txtEdad);
             this.personaje = viewElemento.findViewById(R.id.imgPersonaje);
             viewElemento.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     int posPulsada = getAdapterPosition();
                     setSelected(posPulsada);



                 }
             });
         }

         public  TextView getNombre(){
             return nombre;
         }
         public  TextView getEdad(){
             return edad;
         }

        public ImageView getPersonaje() {
            return personaje;
        }
    }



    public void setSelected(int nuevaPos) {

        // ------------------------------------------------------
        // a) SI YA ESTÁ MARCADA → DESMARCAR (volver a color normal)
        // ------------------------------------------------------
        if (posMarcadas.contains(nuevaPos)) {
            posMarcadas.remove((Integer) nuevaPos);
            notifyItemChanged(nuevaPos);
            return;
        }

        // ------------------------------------------------------
        // b) SI NO HAY NINGUNA MARCADA → MARCAR BLANCO
        // (es simplemente añadir una posición)
        // ------------------------------------------------------
        if (posMarcadas.size() == 0) {
            posMarcadas.add(nuevaPos);
            notifyItemChanged(nuevaPos);
            return;
        }

        // ------------------------------------------------------
        // c) HAY 1 MARCADA (blanca) → MARCAR OTRA (negro)
        // ------------------------------------------------------
        if (posMarcadas.size() == 1) {
            posMarcadas.add(nuevaPos);
            notifyItemChanged(nuevaPos);
            return;
        }

        // ------------------------------------------------------
        // d) YA HAY 2 MARCADAS → quitar la más antigua y marcar nueva
        // ------------------------------------------------------
        int antigua = posMarcadas.get(0);

        posMarcadas.remove(0);
        notifyItemChanged(antigua);

        posMarcadas.add(nuevaPos);
        notifyItemChanged(nuevaPos);
    }





}
