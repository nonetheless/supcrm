package com.supcrm.xinlin.supcrm.Controler;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.supcrm.xinlin.supcrm.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatePickerFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String EXTRA_DATE="com.example.xinlin.date";

    // TODO: Rename and change types of parameters
    private String mParm1;
    private String mParam2;
    private Date mDate;


    public DatePickerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DatePickerFragment newInstance(Date mDate) {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE ,mDate);
        fragment.setArguments(args);
        return fragment;
    }
    
    private void sendResult(int result){
        if(getTargetFragment()==null) {
            Log.d("Null","null");
            return;
        }
        Intent i=new Intent();
        i.putExtra(EXTRA_DATE,mDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(),result,i);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);


        Calendar calendar =Calendar.getInstance();
        calendar.setTime(mDate);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        Log.d("d",year+" "+month+" "+day);
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);
        final DatePicker datePicker=(DatePicker)v.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                mDate = new GregorianCalendar(i,i1,i2).getTime();
                getArguments().putSerializable(EXTRA_DATE,mDate);
            }
        });
        Dialog back=new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("输入日期")
                .setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int i0=datePicker.getDayOfMonth();
                        int i1=datePicker.getMonth();
                        int i2=datePicker.getYear();
                        Calendar b = Calendar.getInstance();
                        b.set(i2,i1,i0,0,0);
                        mDate=b.getTime();
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
        return back;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParm1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date_picker, container, false);
    }

}
