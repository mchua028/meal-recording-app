package com.example.mealtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MyMealInformationExampleAdapter extends RecyclerView.Adapter<MyMealInformationExampleAdapter.ExampleViewHolder> {

    private ArrayList<MyMealInformationExampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.foodLabel);
            mTextView2 = itemView.findViewById(R.id.foodNameTxt);
            mTextView3 = itemView.findViewById(R.id.nutrientName);
            mTextView4 = itemView.findViewById(R.id.nutrientWeight);
        }
    }

    public MyMealInformationExampleAdapter(ArrayList<MyMealInformationExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_meal_information_example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        MyMealInformationExampleItem currentItem = mExampleList.get(position);

        //holder.mTextView1.setText(currentItem.getText1());
        //holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
