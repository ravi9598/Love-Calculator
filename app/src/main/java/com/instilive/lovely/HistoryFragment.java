package com.instilive.lovely;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ravi on 2/23/2018.
 */

public class HistoryFragment extends Fragment{

    private SQLiteDatabase database;
    private ListView listView;
    private ArrayList arrayList;
    private ArrayAdapter arrayAdapter;
    private String lover1,lover2,percentage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        database=getActivity().openOrCreateDatabase("DATABASE",android.content.Context.MODE_PRIVATE ,null);
        Cursor cursor=database.rawQuery("select * from loveHistory",null);

        while (cursor.moveToNext())
        {
            lover1=cursor.getString(0);
            lover2=cursor.getString(1);
            percentage=cursor.getString(2);
        }


        listView=(ListView)view.findViewById(R.id.historyListView);
        arrayList=new ArrayList<String>();
        arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        arrayList.add(lover1+" "+lover2+" "+percentage);
        arrayAdapter.notifyDataSetChanged();

        return view;
    }
}
