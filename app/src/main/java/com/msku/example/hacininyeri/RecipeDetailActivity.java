package com.msku.example.hacininyeri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msku.example.hacininyeri.models.Meal;

public class RecipeDetailActivity extends AppCompatActivity {

    // ***** YUSUF YILDIZ ******

    ImageView mainImage;
    TextView tv_star;
    TextView tv_time;
    TextView tv_preparation;
    TextView tv_title;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        intent = getIntent();

        mainImage = findViewById(R.id.iv_main_image);
        tv_star = findViewById(R.id.tv_rating_details);
        tv_time = findViewById(R.id.tv_time_details);
        tv_preparation = findViewById(R.id.tv_preparation);
        tv_title = findViewById(R.id.tv_recipetitle);

        Glide.with(this).load(intent.getStringExtra("image")).into(mainImage);
        tv_star.setText(intent.getStringExtra("rating"));  // Use "rating" instead of "image"
        tv_time.setText(intent.getStringExtra("preparation_time"));
        tv_title.setText(intent.getStringExtra("name"));
        tv_preparation.setText(intent.getStringExtra("preparation"));
        //tv_preparation.setText(myMeal.());
    }
}
