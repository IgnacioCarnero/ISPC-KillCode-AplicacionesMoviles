package com.example.food_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.R;
import com.example.food_app.database.entity.comidaBebida;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<comidaBebida> mData;
    private LayoutInflater mInflater;
    private Context context;

    // Agrega una interfaz de Listener
    public interface OnQuantityChangeListener {
        // Implementación del método de la interfaz OnItemQuantityChangedListener
        void onQuantityChanged(comidaBebida comida);

        void onQuantityChanged(comidaBebida comida, int newQuantity);
    }

    // Añade una variable de listener en el adapter
    private OnQuantityChangeListener onQuantityChangeListener;

    public ListAdapter(List<comidaBebida> itemList, Context context, OnQuantityChangeListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.onQuantityChangeListener = listener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.menu_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<comidaBebida> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void updateData(List<comidaBebida> comidaBebidaList) {
        mData.clear(); // Limpia la lista actual
        mData.addAll(comidaBebidaList); // Agrega los nuevos datos
        notifyDataSetChanged(); // Notifica al RecyclerView para que se actualice
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView addBtn, subsBtn;
        TextView nombre, tipo, precio, descripcion;
        EditText cantidad;

        ViewHolder(View itemView) {
            super(itemView);
            addBtn = itemView.findViewById(R.id.btnAdd);
            cantidad = itemView.findViewById(R.id.editTextNumberSigned);
            subsBtn = itemView.findViewById(R.id.btnSubs);
            nombre = itemView.findViewById(R.id.nombre);
            tipo = itemView.findViewById(R.id.tipo);
            precio = itemView.findViewById(R.id.precio);
            descripcion = itemView.findViewById(R.id.descripcion);

            // Inicializar la cantidad en 0
            cantidad.setText("0");
        }

        void bindData(final comidaBebida comida) {
            nombre.setText(comida.getNombre());
            tipo.setText(comida.getTipo());
            precio.setText(comida.getPrecio().toString());
            descripcion.setText(comida.getDescripcion());

            // Listener para incrementar cantidad
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentCantidad = Integer.parseInt(cantidad.getText().toString());
                    currentCantidad += 1;
                    cantidad.setText(String.valueOf(currentCantidad));
                    if (onQuantityChangeListener != null) {
                        onQuantityChangeListener.onQuantityChanged(comida, currentCantidad);
                    }
                }
            });

            // Listener para decrementar cantidad, asegurando que no sea menor a 0
            subsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentCantidad = Integer.parseInt(cantidad.getText().toString());
                    if (currentCantidad > 0) {
                        currentCantidad -= 1;
                        cantidad.setText(String.valueOf(currentCantidad));
                        if (onQuantityChangeListener != null) {
                            onQuantityChangeListener.onQuantityChanged(comida, currentCantidad);
                        }
                    }
                }
            });
        }
    }
}