package com.msku.example.hacininyeri;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.msku.example.hacininyeri.models.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddRecipeActivity extends AppCompatActivity implements OnCategoryClickListener {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;
    private FirebaseFirestore mFirestore;
    private StorageReference mStorageRef;

    String selectedCategory = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        List<Category> categoryList = FirebaseHelper.getCategoryList();

        RecyclerView recyclerViewCategory = findViewById(R.id.rv_add_categories);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        SelectCategoryAdapter categoryAdapter = new SelectCategoryAdapter(this, categoryList);
        recyclerViewCategory.setAdapter(categoryAdapter);

        mFirestore = FirebaseFirestore.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference("recipe_images");

        Button btnSelectImage = findViewById(R.id.btnSelectImage);
        ImageView imageViewSelected = findViewById(R.id.imageViewSelected);

        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
            }
        });

        findViewById(R.id.buttonShareRecipe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                saveRecipeToFirestore2("Brownie", "200g çikolata, 150g tereyağı, 200g şeker, 3 yumurta, 100g un", "Çikolata ve tereyağını eritin. Diğer malzemeleri ekleyip karıştırın. 180°C'de 25 dk pişirin.", "25 dk", "https://www.karaca.com/blog/wp-content/uploads/2023/09/brownie2.jpg", "Tatlı");
//                saveRecipeToFirestore2("Fırın Somon", "4 somon filetosu, zeytinyağı, tuz, karabiber, limon", "Somonları zeytinyağı, tuz, karabiber ve limon suyu ile marine edin. 200°C'de 20 dk fırınlayın.", "20 dk", "https://baliktarifi.com/wp-content/uploads/2017/02/firinda-baharatli-biberli-somon-fileto-tarifi-500x375.jpg", "Balık Ürünü");
//                saveRecipeToFirestore2("Fırında Tavuk But", "4 tavuk but, sarımsak, tuz, kekik, limon suyu", "Tavuk butları baharatlarla marine edin. 180°C'de 40 dk fırınlayın. Ara sıra çevirin.", "40 dk", "https://cdn.ye-mek.net/App_UI/Img/out/650/2021/11/firinda-tavuk-but-resimli-yemek-tarifi(16).jpg?w=650&h=487", "Et Yemekleri");
//                saveRecipeToFirestore2("Kabak Yemegi", "3 kabak, soğan, domates, sıvı yağ, tuz, baharatlar", "Soğanı kavurun, kabakları ekleyin. Domates ve baharatları ekleyip pişirin.", "30 dk", "https://cdn.yemek.com/mnresize/940/940/uploads/2019/07/kabak-yemegi-onecikan.jpg", "Sebze Yemekleri");
//                saveRecipeToFirestore2("Pizza", "Pizza hamuru, domates sosu, rendelenmiş peynir, sucuk, mantar", "Hamuru açın, sosu sürün. Malzemeleri ekleyip 220°C'de 15 dk pişirin.", "15 dk", "https://cdn.yemek.com/mnresize/1250/833/uploads/2022/03/ev-usulu-pizza-yemekcom.jpg", "Hamur İşleri");
//                saveRecipeToFirestore2("Mercimek Çorbası", "1 su bardağı kırmızı mercimek, soğan, havuç, tuz, karabiber", "Sebzeleri doğrayın, mercimeği ekleyin. 1 lt su ekleyip kaynayana kadar pişirin.", "30 dk", "https://image.hurimg.com/i/hurriyet/75/0x0/5e8f208dc9de3d0c68f0638a.jpg", "Çorba");
//                saveRecipeToFirestore2("Cezar Salatası", "Marullar, tavuk göğsü, kruton, parmesan peyniri, Cezar sos", "Marulları doğrayın, tavuk göğsünü ızgara yapın. Malzemeleri karıştırıp sos ile servis edin.", "15 dk", "https://glossy.espreso.co.rs/data/images/2021/08/03/15/325706_cezarsalatashutterstock-1078415420_ff.jpg", "Salata");
//                saveRecipeToFirestore2("Cheesecake", "200g labne peyniri, 200g bisküvi, 100g tereyağı, 150g şeker", "Bisküvileri ezin, tereyağı ile karıştırın. Kalıba yayıp labne karışımını ekleyin. 4 saat buzdolabında bekletin.", "4 saat", "https://i.nefisyemektarifleri.com/2022/04/27/limonlu-cheesecake-tarifi.jpg", "Tatlı");
//                saveRecipeToFirestore2("Izgara Levrek", "2 levrek fileto, zeytinyağı, limon, tuz, kekik", "Levrek filetolarını marine edin. Izgarada her iki tarafını da 5'er dk pişirin.", "10 dk", "https://www.sosy.co/wp-content/uploads/2020/07/12-Sebzeli-Levrek-Izgara.jpg", "Balık Ürünü");
//                saveRecipeToFirestore2("Kırmızı Et Güveç", "500g dana eti, patates, havuç, soğan, domates, tuz, baharatlar", "Tüm malzemeleri doğrayın, güvece yerleştirin. 180°C'de 1 saat pişirin.", "1 saat", "https://image.hurimg.com/i/hurriyet/75/750x422/5b0bc4878f1ed701f40f7d25.jpg", "Et Yemekleri");

               if (selectedImageUri == null) {
                   Toast.makeText(AddRecipeActivity.this, "Lütfen bir resim seçin", Toast.LENGTH_SHORT).show();
                   return;
               }

               String recipeName = ((EditText) findViewById(R.id.editTextRecipeName)).getText().toString();
               String contentTitle = ((EditText) findViewById(R.id.editTextContents)).getText().toString();
               String preparation = ((EditText) findViewById(R.id.editTextPreparation)).getText().toString();
               String time = ((EditText) findViewById(R.id.editTextTime)).getText().toString();

               if(selectedCategory==""){
                   Toast.makeText(AddRecipeActivity.this, "Lütfen bir kategori seçin", Toast.LENGTH_SHORT).show();
                   return;
               }

               uploadImageAndSaveRecipe(recipeName, contentTitle, preparation, time);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            ImageView imageViewSelected = findViewById(R.id.imageViewSelected);
            imageViewSelected.setVisibility(View.VISIBLE);
            imageViewSelected.setImageURI(selectedImageUri);
        }
    }

    //    @Override


    private void uploadImageAndSaveRecipe(final String recipeName, final String contentTitle, final String preparation, final String time) {
        final StorageReference imageRef = mStorageRef.child(System.currentTimeMillis() + ".jpg");

        imageRef.putFile(selectedImageUri).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();
                    saveRecipeToFirestore(recipeName, contentTitle, preparation, time, imageUrl);
                });
            } else {
                Toast.makeText(AddRecipeActivity.this, "Resim yükleme hatası", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveRecipeToFirestore(String recipeName, String contentTitle, String preparation, String time, String imageUrl) {
        Map<String, Object> recipeMap = new HashMap<>();
        recipeMap.put("recipeName", recipeName);
        recipeMap.put("contentTitle", contentTitle);
        recipeMap.put("preparation", preparation);
        recipeMap.put("time", time);
        recipeMap.put("imageUrl", imageUrl);
        recipeMap.put("category", selectedCategory);
        recipeMap.put("userId", Constants.userId);

        mFirestore.collection("recipes")
                .add(recipeMap)

                .addOnSuccessListener(documentReference -> {
                    documentReference.update("id",documentReference.getId());
                    Toast.makeText(AddRecipeActivity.this, "Tarif başarıyla kaydedildi", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(AddRecipeActivity.this, "Firestore kayıt hatası", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onCategoryClick(String categoryName) {
        selectedCategory = categoryName;
    }



}
