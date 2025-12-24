package com.msku.example.hacininyeri;

import com.msku.example.hacininyeri.models.Category;
import com.msku.example.hacininyeri.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {

    public static List<Meal> getMealList() {
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal("2443f43","Köfte", "40 mins", "4.5", "https://i.nefisyemektarifleri.com/2023/04/17/sodali-kofte-2.jpg","Et Yemekleri","AÇIKLAMA"));
        meals.add(new Meal("2f43r32","Kebap", "50 mins", "4.8", "https://die-frau-am-grill.de/wp-content/uploads/adana-kebap-rezept.jpg","Et Yemekleri","AÇIKLAMA"));
        meals.add(new Meal("f324fr42","Manti", "35 mins", "4.2", "https://i.nefisyemektarifleri.com/2023/05/26/hazir-manti-pisirme-tarifi-5.jpg","Et Yemekleri","AÇIKLAMA"));


        return meals;
    }


    public static List<Category> getCategoryList() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Tatlı", R.drawable.dessert));
        categories.add(new Category("Balık Ürünü", R.drawable.fish));
        categories.add(new Category("Et Yemekleri", R.drawable.meat));
        categories.add(new Category("Sebze Yemekleri", R.drawable.vegetable));
        categories.add(new Category("Hamur İşleri", R.drawable.pastry));
        categories.add(new Category("Çorba", R.drawable.soup));
        categories.add(new Category("Salata", R.drawable.salad));

        return categories;
    }

    public static ArrayList<Meal> getMyRecipeList() {
        ArrayList<Meal> myRecipes = new ArrayList<>();



        myRecipes.add(new Meal("fr24fr42","Baklava", "60 mins", "5.0","https://www.asiltat.com/upload/productsImage/2000x1333/fstkl_baklava.jpeg","Tatlı","AÇIKLAMA"));
        myRecipes.add(new Meal("2fr4fr42","Sütlaç", "40 mins", "4.5","https://i0.wp.com/yemektarifial.com/wp-content/uploads/2022/10/Iki-Kisilik-Sutlac-Tarifi.jpg","Tatlı","AÇIKLAMA"));
        myRecipes.add(new Meal("f2r43fr42","İskender", "40 mins", "4","https://d17wu0fn6x6rgz.cloudfront.net/img/w/tarif/mgt/iskender.webp", "Et Yemekleri","AÇIKLAMA"));


        return myRecipes;
    }
}
