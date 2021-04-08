package com.example.mealtracker.UI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealtracker.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class InputFoodDetailsExampleAdapter extends RecyclerView.Adapter<InputFoodDetailsExampleAdapter.ExampleViewHolder> {

    private ArrayList<InputFoodDetailsExampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextInputLayout mTextView1;
        public TextInputLayout mTextView2;

        public EditText editInputFoodName, editInputFoodWeight;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.txtFood);
            mTextView2 = itemView.findViewById(R.id.txtFoodWeight);

            editInputFoodName = itemView.findViewById(R.id.editFood);
            editInputFoodWeight = itemView.findViewById(R.id.editFoodWeight);
        }
    }

    public InputFoodDetailsExampleAdapter(ArrayList<InputFoodDetailsExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.input_food_details_example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        InputFoodDetailsExampleItem currentItem = mExampleList.get(position);


        Log.d("size of lise",Integer.toString(mExampleList.size()));

        //Log.d("in adapter",currentItem.getText1().getEditText().getText().toString());


//        holder.mTextView1.setText(currentItem.getText1());
//        holder.mTextView2.setText(currentItem.getText2());

       // TextInputLayout text1 = itemView.findViewById(R.id.editFood);
       // String editText = textInputLayout.getEditText().getText();

        //get string from input
      //  String email = editEmail.getText().toString().trim();

        //Log.d("currentItem.getText1",currentItem.getText1());
        //Log.d("currentItem.getText2",currentItem.getText2());

        //holder.mTextView1.setText(currentItem.getText1());
        //holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
