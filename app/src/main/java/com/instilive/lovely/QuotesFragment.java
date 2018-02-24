package com.instilive.lovely;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Ravi on 2/23/2018.
 */

public class QuotesFragment extends Fragment{

    ListView listView;
    String quotes[]={"I look at you and see the rest of my life in front of my eyes.","The greatest happiness of life is the conviction that we are loved; loved for ourselves, or rather, loved in spite of ourselves.","I'm much more me when I'm with you.","The best and most beautiful things in this world cannot be seen or even heard, but must be felt with the heart.","I saw that you were perfect, and so I loved you. Then I saw that you were not perfect and I loved you even more.","The heart wants what it wants. There's no logic to these things. You meet someone and you fall in love and that's that.","There's no substitute for a great love who says, 'No matter what's wrong with you, you're welcome at this table.","Don’t forget I’m just a girl, standing in front of a boy, asking him to love her.","I choose you. And I'll choose you over and over and over. Without pause, without a doubt, in a heartbeat. I'll keep choosing you.","When you realize you want to spend the rest of your life with somebody, you want the rest of your life to start as soon as possible.","At the touch of love everyone becomes a poet.","I realized I was thinking of you, and I began to wonder how long you'd been on my mind. Then it occurred to me: Since I met you, you've never left.","Being deeply loved by someone gives you strength, while loving someone deeply gives you courage.","When love is not madness it is not love.","I hope you know that every time I tell you to get home safe, stay warm, have a good day, or sleep well what I am really saying is I love you. I love you so damn much that it is starting to steal other words' meanings."};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);

        listView=(ListView)view.findViewById(R.id.listView);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,quotes);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),ShareQuotesActivity.class);
                intent.putExtra("Quote",quotes[position]);
                startActivity(intent);

            }
        });

        return view;
    }
}
