package com.example.mealtracker;

import android.os.Build;
import android.util.Log;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyMealsExampleAdapter extends RecyclerView.Adapter<MyMealsExampleAdapter.ExampleViewHolder> {

    private ArrayList<MyMealsExampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.dateTxt);
            mTextView2 = itemView.findViewById(R.id.dayText);
            mTextView3 = itemView.findViewById(R.id.mealRecord);
            mTextView4 = itemView.findViewById(R.id.foodRecordTime);
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
        Log.d("hello","mymeals");
        MealRecordManager mealRecordManager = MealRecordManager.getSingleton();
        ArrayList<MealRecord> mealRecords = mealRecordManager.getMealRecords();
        String foodNames = "";
        if(mealRecords==null){return;}
        for(int i=0;i<mealRecords.size();i++){
            ArrayList<Food> foodRecords = mealRecords.get(i).getFoods();
            for(int j=0;j<foodRecords.size();j++){
                foodNames+= foodRecords.get(j).getName()+"\n"+foodRecords.get(j).getActualIntake()+"g\n"+foodRecords.get(j).getTotalCalorie()+"kcal\n\n";
            }
        }

        String foodTimes = "";
        if(mealRecords==null){return;}
        String time;
        for(int i=0;i<mealRecords.size();i++){
            //Log.d("time",mealRecords.get(i).getTimeString().split("T'")[1]);
              //  time = mealRecords.get(i).getTimeString().split("T'")[1];
            //String hour = Integer.toString(mealRecords.get(i).getTime().getHour());
            //String min = Integer.toString(mealRecords.get(i).getTime().getMinute());
            //String sec = Integer.toString(mealRecords.get(i).getTime().getSecond());

                //foodTimes += hour+":"+min+":"+sec+"\n\n\n\n";
                foodTimes += mealRecords.get(i).getTime().toLocalTime().truncatedTo(ChronoUnit.SECONDS)+"\n\n\n\n";
            }

        /*MealRecord[] mealRecords1 = mealRecordManager.getMealRecords1();
        String foodNames = "";
        if(mealRecords1==null){return;}
        for(int i=0;i<mealRecords1.length;i++){
            ArrayList<Food> foodRecords = mealRecords1[i].getFoods();
            for(int j=0;j<foodRecords.size();j++){
                foodNames+= foodRecords.get(j).getName()+"\n"+foodRecords.get(j).getActualIntake()+"g\n\n";
            }
        }*/
        holder.mTextView3.setText(foodNames);
        LocalDate date = mealRecords.get(0).getTime().toLocalDate();
        holder.mTextView1.setText(date.toString());
        holder.mTextView2.setText(date.getDayOfWeek().name());
        holder.mTextView4.setText(foodTimes);

        //holder.mTextView1.setText((CharSequence) currentItem.getText1());
        //holder.mTextView2.setText((CharSequence) currentItem.getText2());
        //holder.mTextView3.setText((CharSequence) currentItem.getText3());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
