package com.example.mealtracker.UI;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.DAO.Database;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.DAO.MealRecord;
//import com.example.mealtracker.Exceptions.DateValidatorUsingLocalDate;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.MyMealsExampleAdapter;
import com.example.mealtracker.MyMealsExampleItem;
import com.example.mealtracker.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Displays the meal records
 */
public class myMeals extends Fragment {
    private ArrayList<MyMealsExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private TextView mText4;
    //private SearchView mSearchView;

    DatePickerDialog datePicker;
    Button searchMealBtn;
    EditText editDate;


    MealRecordManager mealRecordManager = MealRecordManager.getSingleton();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_meals, container, false);
        Log.d("inside","oncreateview");
        getActivity().setTitle("My Meals");
        //datePicker=(DatePicker)v.findViewById(R.id.datePicker);
        searchMealBtn=(Button)v.findViewById(R.id.searchMealsBtn);
        searchMealBtn.setVisibility(View.GONE);

        //simpleDatePicker.setSpinnersShown(false);

        mExampleList = new ArrayList<>();
        //mExampleList.add(new MyMealsExampleItem(mText1, mText2, mText3));

        mRecyclerView = v.findViewById(R.id.myMealsRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mAdapter = new MyMealsExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        editDate=(EditText) v.findViewById(R.id.editDate);
        editDate.setInputType(InputType.TYPE_NULL);
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePicker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editDate.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
                searchMealBtn.setVisibility(View.VISIBLE);
            }
        });

        searchMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("inside","onClickListener");
                String date = editDate.getText().toString();
                Log.d("editdate",date+"hiii");
                Log.d("got","datefromdatepicker");
                int  searchDay = Integer.parseInt(date.split("/")[0]);
                int searchMonth = Integer.parseInt(date.split("/")[1])-1;
                Log.d("searchMonth",Integer.toString(searchMonth)+"hiii");
                int searchYear = Integer.parseInt(date.split("/")[2]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(searchYear, searchMonth, searchDay);
                Log.d("calendar",calendar.getTime()+"hiii");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String formatedDate = sdf.format(calendar.getTime());
                Log.d("formtaeddate",formatedDate+"hiii");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate startDate = LocalDate.parse(formatedDate,formatter);
                Log.d("dategotten",startDate.toString()+"hiii");
                ArrayList<MealRecord> mealRecords = new ArrayList<MealRecord>();
                try {
                    Log.d("inside","try");
                    mealRecords = Database.getSingleton().queryByDate(startDate);
                    Log.d("queryfrom","databasebydate");
                    mealRecordManager.setMealRecords(mealRecords);
                    Log.d("set","mealrecords");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("after","trycatch");
                if(mealRecords.size()==0){
                    Toast.makeText(getActivity(),"There are no meal records for the chosen date",Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("before", "addMoreCardViews");
                    addMoreCardviews(1);
                    Log.d("after", "addMoreCardViews");
                }
            }
        });
        return v;
    }



    public void addMoreCardviews(int noOfRecords){
        int position = getExampleListSize();
        insertItem(position);
    }

    // insert card views
    public void insertItem(int position) {
        Log.d("insert item","position is" + position);
        if(position>0) {
            mExampleList.remove(position - 1);
        }
        mExampleList.add(position, new MyMealsExampleItem(mText1, mText2, mText3,mText4));

        mAdapter.notifyItemInserted(position);
    }

    // get mExampleList size
    public int getExampleListSize() {
        return mExampleList.size();
    }
}