package com.suraj.fragmentrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.suraj.fragmentrecyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Data> list = new ArrayList<>();
        list.add(new Data(Data.VIEW_PAGER, "Hello. This is the View Pager view type", 0));
        list.add(new Data(Data.IMAGE_TYPE, "A view type with Image and TextView", R.drawable.ic_baseline_airline_seat_recline_extra_24));
        list.add(new Data(Data.AUDIO_TYPE, "Hey, A view type with Button and TextView", 0));
        list.add(new Data(Data.SLIDER_TYPE, "Hello. This is the View Pager view type", 0));
        list.add(new Data(Data.IMAGE_TYPE, "Hi again. A view with Image and TextView", R.drawable.ic_baseline_snowboarding_24));

        binding.fragmentRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapter(list,this);
        binding.fragmentRecycler.setAdapter(adapter);

    }
}