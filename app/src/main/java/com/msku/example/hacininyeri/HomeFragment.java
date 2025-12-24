package com.msku.example.hacininyeri;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HomeFragment extends Fragment {

    LinearLayout generaterecipe;
    LinearLayout favorites;
    LinearLayout search;
    CardView addRecipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        generaterecipe = view.findViewById(R.id.generate_recipe);
        search =   view.findViewById(R.id.search);
        favorites =view.findViewById(R.id.favorites);
        addRecipe =view.findViewById(R.id.btn_addNewRecipe);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        generaterecipe.setOnClickListener(v ->{
            startActivity(new Intent(requireContext(), GenerateActivity.class));

        });


        search.setOnClickListener(v ->{
            startActivity(new Intent(requireContext(), SearchActivity.class));
        });

        favorites.setOnClickListener(v ->{
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.framentContainer, new RecipeListFragment(new Favorites()))
                    .commit();
        });

        addRecipe.setOnClickListener(v ->{
            startActivity(new Intent(requireContext(), AddRecipeActivity.class));

        });
    }
}