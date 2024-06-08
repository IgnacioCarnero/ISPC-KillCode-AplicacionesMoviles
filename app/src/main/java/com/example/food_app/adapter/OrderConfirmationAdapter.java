package com.example.food_app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.R;
import com.example.food_app.database.entity.comidaBebida;

import java.util.List;

public class OrderConfirmationAdapter extends RecyclerView.Adapter<OrderConfirmationAdapter.ViewHolder> {
    private List<comidaBebida> mData;
    private LayoutInflater mInflater;
    private Context context;

    private static final String TAG = "OrderConfirmationAdapter";

    public OrderConfirmationAdapter(List<comidaBebida> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mData.size());
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: inflating order_products");
        View view = mInflater.inflate(R.layout.order_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        comidaBebida comida = mData.get(position);
        Log.d(TAG, "onBindViewHolder: binding data for position " + position + ", item: " + comida.getNombre());
        holder.bindData(comida);
        holder.deleteButton.setOnClickListener(v -> {
            Log.d(TAG, "onBindViewHolder: removing item at position " + position);
            mData.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mData.size());
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, tipo, precio, descripcion;
        Button deleteButton;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            tipo = itemView.findViewById(R.id.tipo);
            precio = itemView.findViewById(R.id.precio);
            descripcion = itemView.findViewById(R.id.descripcion);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }

        void bindData(comidaBebida comida) {
            Log.d(TAG, "bindData: setting data for " + comida.getNombre());
            nombre.setText(comida.getNombre());
            tipo.setText(comida.getTipo());
            precio.setText(comida.getPrecio().toString());
            descripcion.setText(comida.getDescripcion());
        }
    }
}
