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
        arrayList=new ArrayList<String>();
        while (cursor.moveToNext())
        {
            lover1=cursor.getString(0);
            lover2=cursor.getString(1);
            percentage=cursor.getString(2);
            String historyText=null;
            int result = Integer.parseInt(percentage);
            if (result<30)
            {
                historyText="Need to work hard to get your love.\n";
            }
            else if (result>=30 && result<50)
            {
                historyText="You have a good chance to get your crush.\n";
            }
            else if (result>=50 && result<70)
            {
                historyText="Wow, there is a strong bonding between you.\n";
            }
            else
            {
                historyText="God has made both of you for each other.\n";
            }
            arrayList.add(historyText+"Depth of love between "+lover1+" and "+lover2+" is "+percentage+" %");
        }

        listView=(ListView)view.findViewById(R.id.historyListView);

        arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);


        return view;
    }
}
