package com.msku.example.hacininyeri;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.msku.example.hacininyeri.models.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeListFragment extends Fragment {
    RecipeListType listType;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerView;
    MyRecipeAdapter myRecipeAdapter;

    ArrayList<Meal> mealList = new ArrayList<>();

    public RecipeListFragment(RecipeListType listType) {
        this.listType=listType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        switch (listType.getClass().getSimpleName()) {
            case "Search":
                Search search = (Search) listType;
                getSearchResult(search.getWord(), search.getCategoryName());  //ENES ALĞAN
                break;

            case "Category":
                Category category = (Category) listType;
                getCategoryMeals(category.getCategoryName());
                System.out.println("Category: " + category.getCategoryName());  //YUSUF YILDIZ
                break;

            case "Favorites":
                System.out.println("Favorites");
                getFavoriteMeals();
                break;

            default:
                // Handle default case or throw an exception
                throw new IllegalArgumentException("Unsupported listType: " + listType.getClass().getSimpleName());
        }

        System.out.println("--------- >>> "+listType.getClass() +"--"+ Constants.userId);
        View view =  inflater.inflate(R.layout.fragment_recipe_list, container, false);


        recyclerView = view.findViewById(R.id.rv_recipes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);


        myRecipeAdapter = new MyRecipeAdapter(requireContext(), mealList);
        recyclerView.setAdapter(myRecipeAdapter);




        return view;
    }

    private void getCategoryMeals(String categoryName) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("recipes")
                .whereEqualTo("category",categoryName).get().addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                        myRecipeAdapter.addItem(new Meal(
                                queryDocumentSnapshot.getString("id"),
                                queryDocumentSnapshot.getString("recipeName"),
                                queryDocumentSnapshot.getString("time"),
                                queryDocumentSnapshot.getString("rating") != null ? queryDocumentSnapshot.getString("rating") : "-",
                                queryDocumentSnapshot.getString("imageUrl"),
                                queryDocumentSnapshot.getString("category"),
                                queryDocumentSnapshot.getString("preparation")
                        ));
                    }
                });
    }


    private void getFavoriteMeals() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userRecipesRef = db.collection("users").document(Constants.userId).collection("favoriteRecipes");

        userRecipesRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
            fetchFavoriteMealsData(queryDocumentSnapshots);
        });
    }

    private void fetchFavoriteMealsData(QuerySnapshot favoriteDocumentSnapshots) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference mealsCollectionRef = db.collection("recipes");
        for (QueryDocumentSnapshot favoriteDoc : favoriteDocumentSnapshots) { // favoriye eklediğim dökümanlar
            mealsCollectionRef.document(favoriteDoc.getId()).get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    updateUIWithMealData(documentSnapshot);
                }
            });
        }
    }

    private void updateUIWithMealData(DocumentSnapshot mealData) {
        myRecipeAdapter.addItem(new Meal(
                mealData.getString("id"),
                mealData.getString("recipeName"),
                mealData.getString("time"),
                mealData.getString("rating") != null ? mealData.getString("rating") : "-",
                mealData.getString("imageUrl"),
                mealData.getString("category"),
                mealData.getString("preparation")
        ));
    }



    public void getSearchResult(String input, String selectedCategory) { // ENES ALĞAN
        db.collection("recipes")
                .whereGreaterThanOrEqualTo("recipeName", input)
                .whereLessThan("recipeName", input + "\uf8ff")
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (queryDocumentSnapshots != null) {
                        if(!selectedCategory.equals("")){  // kategori sçilmiş
                            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                String category = document.getString("category");
                                if(category.equals(selectedCategory)){
                                    myRecipeAdapter.addItem(new Meal(
                                            document.getString("id"),
                                            document.getString("recipeName"),
                                            document.getString("time"),
                                            document.getString("rating") != null ? document.getString("rating") : "-",
                                            document.getString("imageUrl"),
                                            document.getString("category"),
                                            document.getString("preparation")
                                    ));
                                }
                            }
                        }else{ // kategori seçilmemis
                            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                myRecipeAdapter.addItem(new Meal(
                                        document.getString("id"),
                                        document.getString("recipeName"),
                                        document.getString("time"),
                                        document.getString("rating") != null ? document.getString("rating") : "-",
                                        document.getString("imageUrl"),
                                        document.getString("category"),
                                        document.getString("preparation")
                                ));
                            }

                            System.out.println("------SİZEE --- >> " + selectedCategory + "-" + input + "-" + queryDocumentSnapshots.size());
                        }




                    }
                });
    }
}