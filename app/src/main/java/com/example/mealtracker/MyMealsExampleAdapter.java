package com.example.mealtracker;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.DAO.MealRecord;

import java.lang.reflect.Array;
import java.time.LocalDate;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        MyMealsExampleItem currentItem = mExampleList.get(position);
        MealRecordManager mealRecordManager = MealRecordManager.getSingleton();
        MealRecord[] mealRecords = mealRecordManager.getMealRecords();
        String foodNames = "";
        for(int i=0;i<mealRecords.length;i++){
            ArrayList<Food> foodRecords = mealRecords[i].getFoods();
            for(int j=0;j<foodRecords.size();j++){
                foodNames+= foodRecords.get(j).getName()+"\n";
            }
        }
        holder.mTextView3.setText(foodNames);
        LocalDate date = mealRecords[0].getTime().toLocalDate();
        holder.mTextView1.setText(date.toString());
        holder.mTextView2.setText(date.getDayOfWeek().name());

        //holder.mTextView1.setText((CharSequence) currentItem.getText1());
        //holder.mTextView2.setText((CharSequence) currentItem.getText2());
        //holder.mTextView3.setText((CharSequence) currentItem.getText3());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
