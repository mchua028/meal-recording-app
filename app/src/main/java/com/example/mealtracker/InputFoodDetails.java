package com.example.mealtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class InputFoodDetails extends AppCompatActivity {
    private ArrayList<FoodDetailsExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button insertBtn;
    private Button removeBtn;

    private TextInputLayout mText1;
    private TextInputLayout mText2;

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
        mExampleList.add(new FoodDetailsExampleItem(mText1, mText2));
    }

    // for recycle view
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new FoodDetailsExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    // insert card views
    public void insertItem(int position) {
        Log.d("insert item","position is" + position);
        mExampleList.add(position, new FoodDetailsExampleItem(mText1, mText2));
        mAdapter.notifyItemInserted(position);
    }

    // remove card views
    public void removeItem(int position) {
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