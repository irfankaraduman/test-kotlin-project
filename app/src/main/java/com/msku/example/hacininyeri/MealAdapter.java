package com.msku.example.hacininyeri;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.msku.example.hacininyeri.models.Meal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    private List<Meal> mealList;
    private Context mContext;

    public MealAdapter(Context mContext, List<Meal> mealList) {
        this.mealList = mealList;
        this.mContext = mContext;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personalized_recipes, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        Meal meal = mealList.get(position);

        holder.mealName.setText(meal.getName());
        holder.preparationTime.setText(meal.getPreparationTime());
        holder.ratingBar.setText(meal.getRating());

        holder.handleOnClick(meal);

        Glide.with(mContext).load(meal.getImageUrl()).into(holder.mealImage);
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {

         ImageView mealImage;
         TextView mealName;
         TextView preparationTime;
        TextView ratingBar;
         CardView saveButton;

        public MealViewHolder(View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.iv_personalized_meal);
            mealName = itemView.findViewById(R.id.tv_meal_name);
            preparationTime = itemView.findViewById(R.id.tv_time);
            ratingBar = itemView.findViewById(R.id.tv_rating);
            saveButton = itemView.findViewById(R.id.btn_save);


        }

        public void handleOnClick(Meal meal) {
            mealImage.setOnClickListener(view -> {

                Intent intent = new Intent(mContext,RecipeDetailActivity.class);
                intent.putExtra("category", meal.getCategory());
                intent.putExtra("rating", meal.getRating());
                intent.putExtra("preparation_time", meal.getPreparationTime());
                intent.putExtra("name", meal.getName());
                intent.putExtra("image", meal.getImageUrl());
                intent.putExtra("preparation", meal.getPreparation());

                mContext.startActivity(intent);
            });

            saveButton.setOnClickListener(view -> {
                saveToFavorites(meal.getId());
            });
        }
    }

    private void saveToFavorites(String mealId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> data = new HashMap<>();
        data.put("mealId", mealId);

        CollectionReference userRecipesRef = db.collection("users").document(Constants.userId).collection("favoriteRecipes");

        DocumentReference documentReference = userRecipesRef.document(mealId);

        documentReference.set(data).addOnSuccessListener(task->{
            Toast.makeText(mContext, "Kaydedildi", Toast.LENGTH_SHORT).show();
        });

    }

    public void addItem(Meal meal){
        mealList.add(meal);
        notifyItemInserted(mealList.size()-1);
    }
}
