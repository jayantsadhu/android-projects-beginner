package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ViewAdapterClass viewAdapter;
    List<RecyclerModel> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        String message = "this is the first recycler view made by me";
        String time = "01:55 am";
        contactList = new ArrayList<>();
        contactList.add(new RecyclerModel(R.raw.actress1,"Abhishek",message, time));
        contactList.add(new RecyclerModel(R.raw.actress2,"Sanjeet",message, time));
        contactList.add(new RecyclerModel(R.raw.actress3,"Uma Shankar",message, time));
        contactList.add(new RecyclerModel(R.raw.actress4,"Vishal",message, time));
        contactList.add(new RecyclerModel(R.raw.actress5,"Sourav Bhaiya",message, time));
        contactList.add(new RecyclerModel(R.raw.actress6,"Raju Bhaiya",message, time));
        contactList.add(new RecyclerModel(R.raw.actress7,"Aman Bhaiya",message, time));
        contactList.add(new RecyclerModel(R.raw.actress8,"Subhojit",message, time));
        contactList.add(new RecyclerModel(R.raw.actress9,"Ratul",message, time));
        contactList.add(new RecyclerModel(R.raw.actress10,"Ranjeet",message, time));
        contactList.add(new RecyclerModel(R.raw.actress11,"Akash B",message, time));
        contactList.add(new RecyclerModel(R.raw.actress12,"Anindya",message, time));
        contactList.add(new RecyclerModel(R.raw.actress13,"Arpan",message, time));
        contactList.add(new RecyclerModel(R.raw.actress14,"Akash S",message, time));
        contactList.add(new RecyclerModel(R.raw.pic1,"Ritesh",message, time));
        contactList.add(new RecyclerModel(R.raw.pic2,"Sayantan",message, time));

        /*ArrayList<Integer> imgArray = new ArrayList<>();
        for(int i=1800000 ; i<1800016 ; i++)
            imgArray.add(i);
        int res = R.raw.
        contactList = new ArrayList<>();
        for(int i=0 ; i<10 ; i++){
            RecyclerModel recyclerModel = new RecyclerModel(1800002,friendsArray.get(i),message, time);
            contactList.add(new RecyclerModel(1800002,friendsArray.get(i),message, time));
        }*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        viewAdapter = new ViewAdapterClass(contactList);
        recyclerView.setAdapter(viewAdapter);
        viewAdapter.notifyDataSetChanged();
        /*recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewAdapterClass = new ViewAdapterClass(contactList);
        recyclerView.setAdapter(viewAdapterClass);*/
    }
}