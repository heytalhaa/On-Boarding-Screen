package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fooddelivery.databinding.ActivityMainBinding;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    OnBoardingAdapter onBoardingAdapter;
    OnBoardingData onBoardingData;
    ArrayList<OnBoardingData> onBoardingDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onBoardingDataArrayList = new ArrayList<>();

        onBoardingData = new OnBoardingData();
        onBoardingData.setImg(R.drawable.amico);
        onBoardingData.setTitle("Fresh Meals");
        onBoardingData.setDetails("Discover fresh, healthy meals delivered straight to your door.");
        onBoardingDataArrayList.add(onBoardingData);

        onBoardingData = new OnBoardingData();
        onBoardingData.setImg(R.drawable.pana);
        onBoardingData.setTitle("Quick Delivery");
        onBoardingData.setDetails("Get your meals delivered quickly and conveniently");
        onBoardingDataArrayList.add(onBoardingData);

        onBoardingData = new OnBoardingData();
        onBoardingData.setImg(R.drawable.pana1);
        onBoardingData.setTitle("Start Today!");
        onBoardingData.setDetails("Start your culinary journey with us today!");
        onBoardingDataArrayList.add(onBoardingData);

        onBoardingAdapter = new OnBoardingAdapter(this, onBoardingDataArrayList);
        binding.viewPager.setAdapter(onBoardingAdapter);

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changedIndicator(position);
            }
        });

        binding.skipBtn.setOnClickListener(view -> {
            startActivity(new Intent(OnBoardingActivity.this, HomeActivity.class));
            finish();
        });
        // here is the code for carousal item
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (binding.viewPager.getCurrentItem()+1<onBoardingAdapter.getItemCount()){
                    binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1);
                }else {
                    binding.viewPager.setCurrentItem(0);
                }
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
        // carousal code end here


        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.viewPager.getCurrentItem() +1 <onBoardingAdapter.getItemCount()){
                    binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1);
                }else {
                    startActivity(new Intent(OnBoardingActivity.this, HomeActivity.class));
                    finish();
                }
            }
        });
    }

    private void changedIndicator(int position) {
        switch (position){
            case 0:
                binding.c1.setCardBackgroundColor(getResources().getColor(R.color.brightOrange));
                binding.c2.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                binding.c3.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
            case 1:
                binding.c1.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                binding.c2.setCardBackgroundColor(getResources().getColor(R.color.brightOrange));
                binding.c3.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
            case 2:
                binding.c1.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                binding.c2.setCardBackgroundColor(getResources().getColor(R.color.lightGray));
                binding.c3.setCardBackgroundColor(getResources().getColor(R.color.brightOrange));
                break;
            default:
                Toast.makeText(this, "Invalid Case", Toast.LENGTH_SHORT).show();
        }
        if (position == 2){
            binding.nextBtn.setText("Start");
        }else {
            binding.nextBtn.setText("Next");
        }
    }
}