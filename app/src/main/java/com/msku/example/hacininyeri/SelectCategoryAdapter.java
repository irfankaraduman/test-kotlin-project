package com.msku.example.hacininyeri;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.msku.example.hacininyeri.models.Category;

import java.util.List;

public class SelectCategoryAdapter extends RecyclerView.Adapter<SelectCategoryAdapter.CategoryViewHolder> {
    private Category selectedCategory;
    private List<Category> categoryList;
    private OnCategoryClickListener mContext;

    public SelectCategoryAdapter(OnCategoryClickListener mContext, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.mContext = mContext;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_categories, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.categoryName.setText(category.getName());
        holder.categoryImage.setImageResource(category.getImage());

        // Check if the category is selected and set the background color accordingly
        if (selectedCategory != null && selectedCategory.equals(category)) {
            holder.lineer.setBackgroundColor(Color.DKGRAY);
            holder.categoryName.setTextColor(Color.WHITE);
        } else {
            holder.lineer.setBackgroundColor(Color.WHITE);
            holder.categoryName.setTextColor(Color.BLACK);
        }

        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        TextView categoryName;
        LinearLayout lineer;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.iv_categoryImage);
            categoryName = itemView.findViewById(R.id.tv_categoryName);
            lineer = itemView.findViewById(R.id.lineer_card);
        }

        public void bind(Category category) {
            itemView.setOnClickListener(view ->{
                mContext.onCategoryClick(categoryName.getText().toString());

                // Update the selected category and notify the adapter
                selectedCategory = category;
                notifyDataSetChanged();
            });
        }
    }
}
