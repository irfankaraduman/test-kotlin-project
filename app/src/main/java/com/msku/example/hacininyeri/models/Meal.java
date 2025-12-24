package com.msku.example.hacininyeri.models;

public class Meal {
    private String id;
    private String name;
    private String preparationTime;
    private String rating;
    private String imageUrl;
    private String category;
    private String preparation;


    public Meal(String id, String name, String preparationTime, String rating, String imageUrl, String category, String preparation) {
        this.id =id;
        this.name = name;
        this.preparationTime = preparationTime;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.category = category;
        this.preparation = preparation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}