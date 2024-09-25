package com.example.androidapp_practice10;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button newImageButton;
    private CatApiService catApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.catImageView);
        newImageButton = findViewById(R.id.newImageButton);
        catApiService = RetrofitClient.getRetrofitInstace().create(CatApiService.class);

        newImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchImageCat();
            }
        });


    }

    private void fetchImageCat(){
        Call<List<Cat>> call = catApiService.getCats();
        call.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    String imageUrl = response.body().get(0).getUrl();
                    Picasso.get().load(imageUrl).into(imageView);
                }else{
                    Toast.makeText(MainActivity.this,"Couldnt get Image",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                Toast.makeText(MainActivity.this , "Failed to fetch cate",Toast.LENGTH_SHORT).show();
            }
        });
    }
}