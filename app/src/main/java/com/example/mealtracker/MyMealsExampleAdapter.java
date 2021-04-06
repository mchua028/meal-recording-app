package com.example.mealtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyMealsExampleAdapter extends RecyclerView.Adapter<MyMealsExampleAdapter.ExampleViewHolder> {

    private ArrayList<MyMealsExampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.dateTxt);
            mTextView2 = itemView.findViewById(R.id.dayText);
            mTextView3 = itemView.findViewById(R.id.mealRecord);
        }
    }

    public MyMealsExampleAdapter(ArrayList<MyMealsExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_meals_example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        MyMealsExampleItem currentItem = mExampleList.get(position);

        //holder.mTextView1.setText((CharSequence) currentItem.getText1());
        //holder.mTextView2.setText((CharSequence) currentItem.getText2());
        //holder.mTextView3.setText((CharSequence) currentItem.getText3());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
