package com.msku.example.hacininyeri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private List<String> ingredientList;

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItem;

        public IngredientsViewHolder(View view) {
            super(view);
            textViewItem = view.findViewById(R.id.textViewItem);
        }
    }

    public IngredientsAdapter(List<String> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredent, parent, false);

        return new IngredientsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        String ingredient = ingredientList.get(position);
        holder.textViewItem.setText(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}

