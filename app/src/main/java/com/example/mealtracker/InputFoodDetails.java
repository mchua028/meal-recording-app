package com.example.mealtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class InputFoodDetails extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button insertBtn;
    private Button removeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_food_details);

        createExampleList();
        buildRecyclerView();

        insertBtn = findViewById(R.id.insertBtn);
        removeBtn = findViewById(R.id.removeBtn);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getExampleListSize();
                //int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getExampleListSize()-1;
                //int position = Integer.parseInt(editTextInsert.getText().toString());
                removeItem(position);
            }
        });

        Button onClickCountCaloriesBtn = (Button) findViewById(R.id.countCaloriesBtn);
        onClickCountCaloriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(v.getContext(), countCalories.class));
            }
        });

        Button onClickCancelBtn = (Button) findViewById(R.id.cancelBtn);
        onClickCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    // for recycle view
    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem("Line 1", "Line 2"));
        mExampleList.add(new ExampleItem("Line 3", "Line 4"));
        mExampleList.add(new ExampleItem("Line 5", "Line 6"));
    }

    // for recycle view
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    // insert card views
    public void insertItem(int position) {
        Log.d("insert item","position is" + position);
        mExampleList.add(position, new ExampleItem("New item at position" + position, "This is Line 2"));
        mAdapter.notifyItemInserted(position);
    }

    // remove card views
    public void removeItem(int position) {
        Log.d("remove item","position is" + position);
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    // get mExampleList size
    public int getExampleListSize() {
        return mExampleList.size();
    }
}