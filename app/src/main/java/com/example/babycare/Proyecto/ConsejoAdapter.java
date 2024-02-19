package com.example.babycare.Proyecto;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.babycare.R;

import java.util.ArrayList;

public class ConsejoAdapter extends RecyclerView.Adapter<ConsejoAdapter.ViewHolder>{

    private ArrayList<Consejo> datos;
    private static final double COLOR_RANGE = 254;

    public interface ItemClickListener {
        void onClick(View view, int position, Consejo consejo);
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvTitulo;
        private final ImageView icono;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvTitulo = (TextView) view.findViewById(R.id.tvTituloConsejo);
            icono = (ImageView) view.findViewById(R.id.imgIconoConsejo);
            view.setOnClickListener(this);
        }

        public void setInfo(String titulo, int iconoCons) {
            tvTitulo.setText(titulo);
            icono.setImageResource(iconoCons);
        }

        @Override
        public void onClick(View view) {
            // Si tengo un manejador de evento lo propago con el índice
            if (clickListener != null)
                clickListener.onClick(view, getAdapterPosition(), datos.get(getAdapterPosition()));
        }
    }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data_RickAndMorty to populate views to be used
         * by RecyclerView.
         */
        public ConsejoAdapter(ArrayList<Consejo> dataSet) {
            datos = new ArrayList<Consejo>();
            add(dataSet);
        }

        public ConsejoAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_consejo, viewGroup, false);

            view.setBackgroundColor(Color.rgb(
                    (int)(Math.random()*COLOR_RANGE),
                    (int)(Math.random()*COLOR_RANGE),
                    (int)(Math.random()*COLOR_RANGE)
            ));
            return new ConsejoAdapter.ViewHolder(view);
        }

        public void onBindViewHolder(ConsejoAdapter.ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            Consejo consejo = datos.get(position);
            viewHolder.setInfo(consejo.getNombre(), consejo.getId());//TODO cambiar esto por icono si fuera necesario

        }

        public int getItemCount() {
            return datos.size();
        }

        public void add(ArrayList<Consejo> dataSet){
            datos.addAll(dataSet);
            notifyDataSetChanged();
        }
}
