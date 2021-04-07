package com.example.mealtracker.UI;

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

import com.example.mealtracker.EditCaloriesExampleItem;
import com.example.mealtracker.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class editCalories extends AppCompatActivity {

    private ArrayList<EditCaloriesExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private NavigationView navigationView;

    private EditCaloriesExampleAdapter.OnItemClickListener mListener;

    private TextView mText1;
    private TextView mText2;
    private ImageView mCancel;

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

        addMoreCardviews();

    }

    public void addMoreCardviews(){
        int position = getExampleListSize();

        // for each type of food in Today's meals
        for (int i=1; i<3; i++) {   // TODO: insert actual num of datasets instead of dummy number 3
            //int position = Integer.parseInt(editTextInsert.getText().toString());
            insertItem(position);
        }
    }

    // insert card views
    public void insertItem(int position) {
        Log.d("insert item","position is" + position);
        mExampleList.add(position, new EditCaloriesExampleItem(mText1, mText2, mCancel));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        Log.d("tag: remove item:", "removing item");
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new EditCaloriesExampleItem(mText1, mText2, mCancel));
        mExampleList.add(new EditCaloriesExampleItem(mText1, mText2, mCancel));
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