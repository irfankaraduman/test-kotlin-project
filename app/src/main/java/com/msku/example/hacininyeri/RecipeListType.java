package com.msku.example.hacininyeri;

public class RecipeListType {

}

class Search extends RecipeListType {
    private String word;
    private String categoryName;

    public Search(String word, String categoryName) {
        this.word = word;
        this.categoryName = categoryName;
    }

    public String getWord() {
        return word;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

class Category extends RecipeListType {
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

class Favorites extends RecipeListType {
}
