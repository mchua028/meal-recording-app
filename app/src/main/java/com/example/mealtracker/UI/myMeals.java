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
import java.util.Arrays;
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
        editDate.setText(null);
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
                //int searchYear = datePicker.getYear();

                String date = editDate.getText().toString();


                Log.d("editdate",date+"hiii");
                Log.d("got","datefromdatepicker");
                int  searchDay = Integer.parseInt(date.split("/")[0]);
                int searchMonth = Integer.parseInt(date.split("/")[1])-1;
                Log.d("searchMonth",Integer.toString(searchMonth)+"hiii");
                int searchYear = Integer.parseInt(date.split("/")[2]);
                //int searchMonth = datePicker.getMonth();
                //int searchDay = datePicker.getDayOfMonth();
                Calendar calendar = Calendar.getInstance();
                calendar.set(searchYear, searchMonth, searchDay);
                Log.d("calendar",calendar.getTime()+"hiii");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String formatedDate = sdf.format(calendar.getTime());
                Log.d("formtaeddate",formatedDate+"hiii");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate startDate = LocalDate.parse(formatedDate,formatter);
                LocalDate endDate = startDate;
                //Toast.makeText(getActivity(),"date chosen:"+startDate.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dategotten",startDate.toString()+"hiii");
                ArrayList<MealRecord> mealRecords = new ArrayList<MealRecord>();
                MealRecord[] mealRecords1=null;
                try {
                    Log.d("inside","try");
                    mealRecords = Database.getSingleton().queryByDate(startDate);
                    //mealRecords1 = Database.getSingleton().queryByDate(startDate,endDate);
                    /*MealRecord mealRecord1 = new MealRecord();
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    LocalDateTime mealRecordDateTime = LocalDateTime.parse("09-04-2021 08:14",formatter2);
                    LocalDate mealRecordDate = mealRecordDateTime.toLocalDate();
                    mealRecord1.setTime(mealRecordDateTime);
                    ArrayList<Food> foods = new ArrayList<Food>();
                    Food food1 = new Food();
                    food1.setName("egg");
                    food1.setActualIntake(100);
                    Food food2 = new Food();
                    food2.setName("chicken");
                    food2.setActualIntake(200);
                    foods.add(food1);
                    foods.add(food2);
                    mealRecord1.setFoods(foods);
                    Log.d("newdate",startDate.toString());
                    Log.d("mealrecroddate",mealRecord1.getTime().toLocalDate().toString());
                    if(mealRecordDate.equals(startDate)) {
                        mealRecords.add(mealRecord1);
                    }
                    Log.d("queryfrom","databasebydate");
                    */
                    mealRecordManager.setMealRecords(mealRecords);

                    //mealRecordManager.setMealRecords1(mealRecords1);
                    Log.d("set","mealrecords");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("after","trycatch");
                if(mealRecords.size()==0){
                    Toast.makeText(getActivity(),"There are no meal records for the chosen date",Toast.LENGTH_SHORT).show();
                    return;
                }
                //if(mealRecords1.length==0){
                 //   Toast.makeText(getActivity(),"There are no meal records for the chosen date",Toast.LENGTH_SHORT).show();
                //}
                Log.d("before","addMoreCardViews");
                addMoreCardviews(1);
                //addMoreCardviews(mealRecords1.length);
                Log.d("after","addMoreCardViews");

            }
        });
        //mSearchView = v.findViewById(R.id.searchView);
        //Log.d("before","editsearchdate");
        //EditText editSearchDate = null;
        //Log.d("before2","editsearchdate");

        //editSearchDate.setText(mSearchView.getQuery());
        //Log.d("after","editsearchdate");


        //get string from input
        //String searchDate = editSearchDate.getText().toString().trim();

        /*mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //mSearchView = v.findViewById(R.id.searchView);
                //String searchDate = mSearchView.getQuery().toString();

                Log.d("searchDate",searchDate);
                ArrayList<MealRecord> mealRecords = new ArrayList<MealRecord>();
                //MealRecord[] mealRecords1 = null;
                Log.d("onQueryTextSubmit", "called");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate startDate = LocalDate.parse(searchDate,formatter);
                LocalDate endDate = LocalDate.parse(searchDate,formatter);
                Log.d("dategotten",startDate.toString()+"hiii");
                try {
                    Log.d("inside","try");
                    //mealRecords = Database.getSingleton().queryByDate(startDate,endDate);
                    MealRecord mealRecord1 = new MealRecord();
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    LocalDateTime mealRecordDateTime = LocalDateTime.parse("09-04-2021 08:14",formatter2);
                    LocalDate mealRecordDate = mealRecordDateTime.toLocalDate();
                    mealRecord1.setTime(mealRecordDateTime);
                    ArrayList<Food> foods = new ArrayList<Food>();
                    Food food1 = new Food();
                    food1.setName("egg");
                    food1.setActualIntake(100);
                    Food food2 = new Food();
                    food2.setName("chicken");
                    food2.setActualIntake(200);
                    foods.add(food1);
                    foods.add(food2);
                    mealRecord1.setFoods(foods);
                    Log.d("newdate",startDate.toString());
                    Log.d("mealrecroddate",mealRecord1.getTime().toLocalDate().toString());
                    if(mealRecordDate.equals(startDate)) {
                        mealRecords.add(mealRecord1);
                    }
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
                Log.d("before","addMoreCardViews");
                addMoreCardviews(mealRecords.size());
                Log.d("after","addMoreCardViews");

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/
        //addMoreCardviews(mealRecords.length);



        return v;
    }



    public void addMoreCardviews(int noOfRecords){
        int position = getExampleListSize();

        // for each meal record in existence
        for (int i=0; i<noOfRecords; i++) {   // TODO: insert actual num of datasets instead of dummy number 3
            //int position = Integer.parseInt(editTextInsert.getText().toString());
            insertItem(position);
        }
    }

    // insert card views
    public void insertItem(int position) {
        Log.d("insert item","position is" + position);
        if(position>0) {
            mExampleList.remove(position - 1);
        }
        mExampleList.add(position, new MyMealsExampleItem(mText1, mText2, mText3));

        mAdapter.notifyItemInserted(position);
    }
    /*public void removeIteem(int position) {
        mExampleList.remove(mAdapter.getAdapterPosition());
        mAdapter.notifyItemRemoved(holder.getAdapterPosition());
        mAdapter.notifyItemRangeChanged(holder.getAdapterPosition(), mExampleList.size());
    }*/

    // get mExampleList size
    public int getExampleListSize() {
        return mExampleList.size();
    }
}