package com.example.mealtracker.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.EditCaloriesExampleItem;
import com.example.mealtracker.R;

import java.util.ArrayList;

public class EditCaloriesExampleAdapter extends RecyclerView.Adapter<EditCaloriesExampleAdapter.ExampleViewHolder> {
    private ArrayList<EditCaloriesExampleItem> mExampleList;
    private static OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public static void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public ImageView mCancel;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = (TextView) itemView.findViewById(R.id.editCaloriesfoodLabel);
            mTextView2 = (TextView) itemView.findViewById(R.id.editCaloriesfoodCalories);
            mCancel = itemView.findViewById(R.id.editCaloriesRemove);


            mCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public EditCaloriesExampleAdapter(ArrayList<EditCaloriesExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_edit_calories_example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        EditCaloriesExampleItem currentItem = mExampleList.get(position);

        MealRecordManager mealRecordManager = MealRecordManager.getSingleton();
        MealRecord mealRecord = mealRecordManager.getMealRecord();
        ArrayList<Food> foods = mealRecord.getFoods();

        holder.mTextView1.setText(foods.get(position).getName());
        holder.mTextView2.setText(Double.toString(foods.get(position).getTotalCalorie()));

        //holder.mTextView1.setText(currentItem.getText1());
        //holder.mTextView2.setText(currentItem.getText2());
        //holder.mCancel.setImageResource(currentItem.getImageResource());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
