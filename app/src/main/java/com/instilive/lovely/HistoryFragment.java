package com.instilive.lovely;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryFragment extends Fragment{

    private SQLiteDatabase database;
    private ListView listView;
    private ArrayList arrayList;
    private ArrayAdapter arrayAdapter;
    private String lover1,lover2,percentage,quote;
    private FloatingActionButton fab;
    private TextView tvNoHistory;

    private SharedPrefManager sharedPrefManager;
    private static final String TABLE_NAME="loveHistory";
    private static final String DATABASE_NAME="DATABASE";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        sharedPrefManager=new SharedPrefManager(getActivity());

        fab=(FloatingActionButton)view.findViewById(R.id.history_fab);
        tvNoHistory=(TextView)view.findViewById(R.id.no_history_text);
        fab.setImageResource(R.drawable.ic_action_delete);
        arrayList=new ArrayList<String>();

        if(sharedPrefManager.isTableCreated())
        {
            database=getActivity().openOrCreateDatabase(DATABASE_NAME,android.content.Context.MODE_PRIVATE ,null);
            Cursor cursor=database.rawQuery("select * from loveHistory",null);

            long count = DatabaseUtils.queryNumEntries(database, "loveHistory");
            if(count==0)
            {
                tvNoHistory.setVisibility(View.VISIBLE);
            }

            while (cursor.moveToNext())
            {
                lover1=cursor.getString(0);
                lover2=cursor.getString(1);
                percentage=cursor.getString(2);
                quote=cursor.getString(3);
                arrayList.add(lover1+" + "+lover2+" = "+percentage+" %"+"\n"+quote);
            }

            listView=(ListView)view.findViewById(R.id.historyListView);
            arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(arrayAdapter);
        }
        else
        {
            tvNoHistory.setVisibility(View.VISIBLE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Delete all");
                builder.setMessage("Do you want to delete all history?");
                builder.setIcon(R.drawable.ic_action_delete);
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.delete("loveHistory",null,null);
                        arrayAdapter.notifyDataSetChanged();
                        listView.setAdapter(arrayAdapter);
                        tvNoHistory.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        return view;
    }
}
