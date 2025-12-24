package com.msku.example.hacininyeri;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.msku.example.hacininyeri.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> categoryList;
    private Context mContext;
    OnCategoryClickListener categoryClickListener;

    public CategoryAdapter(Context mContext, OnCategoryClickListener categoryClickListener, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.mContext = mContext;
        this.categoryClickListener =categoryClickListener;
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
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

         ImageView categoryImage;
         TextView categoryName;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.iv_categoryImage);
            categoryName = itemView.findViewById(R.id.tv_categoryName);
            itemView.setOnClickListener(view ->{
                categoryClickListener.onCategoryClick(categoryName.getText().toString());
                //mContext.startActivity(new Intent(mContext,RecipeListActivity.class));
            });
        }
    }
}
