package com.example.mealtracker.UI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.DAO.Nutrient;
import com.example.mealtracker.R;

import java.util.ArrayList;

public class MyMealInformationExampleAdapter extends RecyclerView.Adapter<MyMealInformationExampleAdapter.ExampleViewHolder> {

    private ArrayList<MyMealInformationExampleItem> mExampleList=null;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.foodLabel);
            mTextView2 = itemView.findViewById(R.id.foodWeight);
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
        Log.d("position",Integer.toString(position));
        MealRecordManager mealRecordManager = MealRecordManager.getSingleton();
        Food food = mealRecordManager.getMealRecord().getFoods().get(position);
        holder.mTextView1.setText(food.getName());
        holder.mTextView2.setText(Double.toString(food.getActualIntake()));
        String nutritionAmt = "";
        //nutritionAmt+=Double.toString(food.getNutrients().getCaloriePer100g())+"kcal";
        nutritionAmt+=Double.toString(food.getTotalCalorie())+" kcal";
        Log.d("nutritionAmt",nutritionAmt);
        nutritionAmt+="\n"+String.format("%.2f", food.getNutrients().getFat()*food.getActualIntake()/100)+" g";
        nutritionAmt+="\n"+String.format("%.2f", food.getNutrients().getCholesterol()*food.getActualIntake()/100)+" mg";
        nutritionAmt+="\n"+String.format("%.2f", food.getNutrients().getSodium()*food.getActualIntake()/100)+" mg";
        nutritionAmt+="\n"+String.format("%.2f", food.getNutrients().getPotassium()*food.getActualIntake()/100)+" mg";
        nutritionAmt+="\n"+String.format("%.2f", food.getNutrients().getSugar()*food.getActualIntake()/100)+" g";
        nutritionAmt+="\n"+String.format("%.2f", food.getNutrients().getDietaryFibre()*food.getActualIntake()/100)+" g";
        nutritionAmt+="\n"+String.format("%.2f", food.getNutrients().getProtein()*food.getActualIntake()/100)+" g";
        nutritionAmt+="\n"+String.format("%.2f", food.getNutrients().getCalcium()*food.getActualIntake()/100)+" mg";
        nutritionAmt+="\n"+String.format("%.2f", food.getNutrients().getVitaminC()*food.getActualIntake()/100)+" mg";
        nutritionAmt+="\n"+String.format("%.2f", food.getNutrients().getIron()*food.getActualIntake()/100)+" mg";
        nutritionAmt+="\n"+String.format("%.2f", food.getNutrients().getMagnesium()*food.getActualIntake()/100)+" mg";
        holder.mTextView4.setText(nutritionAmt);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
