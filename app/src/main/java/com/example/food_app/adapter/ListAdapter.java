package com.example.food_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.ListElement;
import com.example.food_app.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListElement> mData;

    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<ListElement> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;


    }

    @Override
    public int getItemCount() {return mData.size(); }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.menu_list, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListElement> items) {mData = items;}


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView addBtn, subsBtn;
        TextView name, description, anexo;

        ViewHolder(View itemView) {
            super(itemView);
            addBtn = itemView.findViewById(R.id.btnAdd);
            subsBtn = itemView.findViewById(R.id.btnSubs);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            anexo = itemView.findViewById(R.id.anexo);
        }


        void bindData(final ListElement item) {
            name.setText(item.getName());
            description.setText(item.getDescripcion());
            anexo.setText(item.getAnexo());
        }
    }
}
