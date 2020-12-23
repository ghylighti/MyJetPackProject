package com.ghy.myview.activity.listener;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.Nullable;

public interface MyAdapter  {

//     void registerDataSetObserver(DataSetObserver observer);


//     void unregisterDataSetObserver(DataSetObserver observer);


     int getCount();


     Object getItem(int position) ;


     long getItemId(int position) ;


     View getView(int position, View convertView, ViewGroup parent);

}
