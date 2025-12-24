package com.msku.example.hacininyeri;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.msku.example.hacininyeri.models.Category;
import com.msku.example.hacininyeri.models.Meal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ExploreFragment extends Fragment {

    OnCategoryClickListener categoryClickListener;

    MealAdapter mealAdapter;
    CategoryAdapter categoryAdapter;
    MyRecipeAdapter myRecipeAdapter;
    public ExploreFragment(OnCategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        getMyRecipes();
        getRandomRecipes();

        List<Meal> mealList = new ArrayList<>();
        List<Category> categoryList = FirebaseHelper.getCategoryList();
        ArrayList<Meal> myRecipeList = new ArrayList<>();


        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView2);
        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerView3);
        RecyclerView recyclerView3 = view.findViewById(R.id.recyclerView4);


        LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(requireContext());

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);


        mealAdapter   =  new MealAdapter(requireContext(), mealList);
        categoryAdapter = new CategoryAdapter(requireContext(),categoryClickListener,categoryList);
        myRecipeAdapter = new MyRecipeAdapter(requireContext(), myRecipeList);

        recyclerView1.setAdapter(mealAdapter);
        recyclerView2.setAdapter(categoryAdapter);
        recyclerView3.setAdapter(myRecipeAdapter);

        return view;
    }

    private void getMyRecipes() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("recipes").whereEqualTo("userId",Constants.userId).get().addOnSuccessListener(documentSnapshots->{
            if(!documentSnapshots.isEmpty()){
                for (QueryDocumentSnapshot documentSnapshot : documentSnapshots) {
                    myRecipeAdapter.addItem(new Meal(
                            documentSnapshot.getString("id"),
                            documentSnapshot.getString("recipeName"),
                            documentSnapshot.getString("time"),
                            documentSnapshot.getString("rating") != null ? documentSnapshot.getString("rating") : "-",
                            documentSnapshot.getString("imageUrl"),
                            documentSnapshot.getString("category"),
                            documentSnapshot.getString("preparation")
                    ));
                }
            }
        });
    }

    private void getRandomRecipes() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("recipes").get().addOnSuccessListener(documentSnapshots->{
            if(!documentSnapshots.isEmpty()){
                List<DocumentSnapshot> allRecipes = documentSnapshots.getDocuments();
                int totalRecipes = allRecipes.size();

                if (totalRecipes > 0) {
                    int recipesToAdd = Math.min(totalRecipes, 3);

                    List<DocumentSnapshot> selectedRecipes = new ArrayList<>();

                    Set<Integer> selectedIndices = new HashSet<>();

                    Random random = new Random();
                    while (selectedRecipes.size() < recipesToAdd) {
                        int randomIndex = random.nextInt(totalRecipes);

                        if (!selectedIndices.contains(randomIndex)) {
                            selectedIndices.add(randomIndex);
                            selectedRecipes.add(allRecipes.get(randomIndex));
                        }
                    }


                    for (DocumentSnapshot documentSnapshot : selectedRecipes) {
                        mealAdapter.addItem(new Meal(
                                documentSnapshot.getString("id"),
                                documentSnapshot.getString("recipeName"),
                                documentSnapshot.getString("time"),
                                documentSnapshot.getString("rating") != null ? documentSnapshot.getString("rating") : "-",
                                documentSnapshot.getString("imageUrl"),
                                documentSnapshot.getString("category"),
                                documentSnapshot.getString("preparation")
                        ));
                    }
                }
            }
        });
    }



}