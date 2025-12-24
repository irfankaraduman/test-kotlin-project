package com.msku.example.hacininyeri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GenerateActivity extends AppCompatActivity {

    private List<String> ingredientList = new ArrayList<>();
    private RecyclerView recyclerView;
    private IngredientsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new IngredientsAdapter(ingredientList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        EditText editText = findViewById(R.id.editTextIngredients);
        ImageView addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(v -> {
            String ingredient = editText.getText().toString().trim();
            if (!ingredient.isEmpty()) {
                ingredientList.add(ingredient);
                adapter.notifyItemInserted(ingredientList.size() - 1);
                editText.getText().clear();
            }
        });
    }
}