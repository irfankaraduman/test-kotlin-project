package com.msku.example.hacininyeri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

 // *********** YUSUF YILDIZ ***********

public class HomeActivity extends AppCompatActivity implements OnCategoryClickListener{
    ImageView home;
    ImageView explore;
    ImageView favorites;
    ImageView profile;

    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        home = findViewById(R.id.iv_home);
        explore = findViewById(R.id.iv_explore);
        favorites = findViewById(R.id.iv_favorite);
        profile = findViewById(R.id.iv_profile);
        fragmentContainer = findViewById(R.id.framentContainer);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framentContainer, new HomeFragment())
                .commit();



        home.setOnClickListener(view ->{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framentContainer, new HomeFragment()) //ENES ALĞAN
                    .commit();

        });



        explore.setOnClickListener(view ->{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framentContainer, new ExploreFragment(this)) //ENES ALĞAN
                    .commit();

        });



        favorites.setOnClickListener(view ->{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framentContainer, new RecipeListFragment(new Favorites())) //YUSUF YILDIZ
                    .commit();

        });



        profile.setOnClickListener(view ->{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framentContainer, new ProfileFragment())
                    .commit();
        });


    }

    @Override
    public void onCategoryClick(String categoryName) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framentContainer, new RecipeListFragment(new Category(categoryName)))
                .commit();
    }
}