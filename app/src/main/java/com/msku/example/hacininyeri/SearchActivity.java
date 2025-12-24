package com.msku.example.hacininyeri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.msku.example.hacininyeri.models.Category;
import com.msku.example.hacininyeri.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements OnCategoryClickListener {

    Boolean searchButonunaBasildi = false;

    RecyclerView recyclerViewCategory;
    EditText editText;
    String selectedCategory ="";

    CardView cv_search;
    FrameLayout fl_searchResult;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        List<Category> categoryList = FirebaseHelper.getCategoryList();
        editText = findViewById(R.id.et_search);
        cv_search = findViewById(R.id.cv_search);

        fl_searchResult = findViewById(R.id.fl_searchResult);

        recyclerViewCategory = findViewById(R.id.recyclerView_category);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        SelectCategoryAdapter categoryAdapter = new SelectCategoryAdapter(this,  categoryList);
        recyclerViewCategory.setAdapter(categoryAdapter);

        cv_search.setOnClickListener(view ->{
            searchButonunaBasildi =true;
            fl_searchResult.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_searchResult, new RecipeListFragment(new Search(editText.getText().toString(),selectedCategory)))
                    .commit();

        });

    }






    @Override
    public void onCategoryClick(String categoryName) {
        selectedCategory = categoryName;
    }

    @Override
    public void onBackPressed() {
        if(searchButonunaBasildi){
            fl_searchResult.setVisibility(View.INVISIBLE);
            searchButonunaBasildi =false;
        }else{
            super.onBackPressed();
        }

    }
}