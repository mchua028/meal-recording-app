package com.example.mealtracker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class editCalories extends AppCompatActivity {

    private ArrayList<EditCaloriesExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private NavigationView navigationView;

    private EditCaloriesExampleAdapter.OnItemClickListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calories);

        createExampleList();
        buildRecyclerView();

        // Save button
        // TODO: to connect to db to edit meal records
        Button onClickSaveBtn = (Button) findViewById(R.id.saveBtn);
        onClickSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Cancel button
        Button onClickCancelBtn = (Button) findViewById(R.id.cancelBtn);
        onClickCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void removeItem(int position) {
        Log.d("tag: remove item:", "removing item");
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new EditCaloriesExampleItem("Food 1", "XXX kcal", R.drawable.baseline_clear_black_18dp));
        mExampleList.add(new EditCaloriesExampleItem("Food 2", "XXX kcal", R.drawable.baseline_clear_black_18dp));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new EditCaloriesExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        EditCaloriesExampleAdapter.setOnItemClickListener(new EditCaloriesExampleAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    // remove card views
    public void editCaloriesRemoveItem(int position) {
        if (getExampleListSize() != 0) {
            Log.d("remove item","position is" + position);
            mExampleList.remove(position);
            mAdapter.notifyItemRemoved(position);
        }
        else {
            Toast.makeText(this, "No more items to remove!",Toast.LENGTH_SHORT).show();
        }
    }

    // get mExampleList size
    public int getExampleListSize() {
        return mExampleList.size();
    }
}