package com.example.babycare.Proyecto.Actividad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.babycare.R;

import java.util.ArrayList;

public class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.ViewHolder> {

    private ArrayList<Actividad> datos;

    public interface ItemClickListener {
        void onClick(View view, int position, Actividad unaActividad);
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvTitulo;
        private final TextView tvTipo;
        //private final TextView tvBreveDescripcion;
        private final ImageView icono;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvTitulo = (TextView) view.findViewById(R.id.tvTituloActividad);
            icono = (ImageView) view.findViewById(R.id.imgIconoActividad);
            tvTipo = (TextView) view.findViewById(R.id.tvTipoActividad);
            //tvBreveDescripcion = (TextView) view.findViewById(R.id.tvBreveDescrion);
            view.setOnClickListener(this);
        }

        public void setInfo(String titulo, int iconito, String tipo, String breveDescripcion) {
            tvTitulo.setText(titulo);
            icono.setImageResource(iconito);
            tvTipo.setText(tipo);
            //tvBreveDescripcion.setText(breveDescripcion);
        }

        @Override
        public void onClick(View view) {
            // Si tengo un manejador de evento lo propago con el índice
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition(), datos.get(getAdapterPosition()));
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data_RickAndMorty to populate views to be used
     * by RecyclerView.
     */
    public ActividadAdapter(ArrayList<Actividad> dataSet) {
        datos = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_actividad, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Actividad unaActividad = datos.get(position);
        viewHolder.setInfo(unaActividad.getNombre(), unaActividad.getIcono(), unaActividad.getArea_desarrollo(), unaActividad.getDescripcion());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setActividades(ArrayList<Actividad> actividades){
        datos.clear();
        datos.addAll(actividades);
        notifyDataSetChanged();
    }
}