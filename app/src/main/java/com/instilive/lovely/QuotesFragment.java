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
import android.widget.TextView;
import android.widget.Toast;

public class QuotesFragment extends Fragment{

    ListView listView;
    String quotes[]={"I look at you and see the rest of my life in front of my eyes.",
            "The greatest happiness of life is the conviction that we are loved; loved for ourselves, or rather, loved in spite of ourselves.",
            "I'm much more me when I'm with you.",
            "The best and most beautiful things in this world cannot be seen or even heard, but must be felt with the heart.",
            "I saw that you were perfect, and so I loved you. Then I saw that you were not perfect and I loved you even more.",
            "The heart wants what it wants. There's no logic to these things. You meet someone and you fall in love and that's that.",
            "There's no substitute for a great love who says, 'No matter what's wrong with you, you're welcome at this table.",
            "Don’t forget I’m just a girl, standing in front of a boy, asking him to love her.",
            "I choose you. And I'll choose you over and over and over. Without pause, without a doubt, in a heartbeat. I'll keep choosing you.",
            "When you realize you want to spend the rest of your life with somebody, you want the rest of your life to start as soon as possible.",
            "At the touch of love everyone becomes a poet.",
            "I realized I was thinking of you, and I began to wonder how long you'd been on my mind. Then it occurred to me: Since I met you, you've never left.",
            "Being deeply loved by someone gives you strength, while loving someone deeply gives you courage.",
            "When love is not madness it is not love.",
            "I hope you know that every time I tell you to get home safe, stay warm, have a good day, or sleep well what I am really saying is I love you. I love you so damn much that it is starting to steal other words' meanings.",
            "I'm selfish, impatient and a little insecure. I make mistakes, I am out of control and at times hard to handle. But if you can't handle me at my worst, then you sure as hell don't deserve me at my best.",
            "You don’t need someone to complete you. You only need someone to accept you completely.",
            "You don’t need someone to complete you. You only need someone to accept you completely.",
            "The best love is the kind that awakens the soul; that makes us reach for more, that plants the fire in our hearts and brings peace to our minds. That’s what I hope to give you forever.",
            "It’s always nice to have someone in your life. That makes you smile even when they’re not around.",
            "Love means never having to say you’re sorry.",
            "When someone loves you, they don’t have to say it. You can tell by the way they treat you.",
            "Go for someone who is proud to have you. Not because you’re pretty, but because they value you as a person",
            "Where is great love there are always miracles.",
            "Without respect, love is lost. Without caring, love is boring. Without honestly, Love is unhappy. Without trust, love is unstable.",
            "Love doesn’t grow on trees like apples in Eden – it’s something you have to make.",
            "Because of a great love, one is courageous.",
            "Love is not about how many days, months, or years you’ve been together. Love is about how much you love each other every day.",
            "I still haven’t figured out how to sit across from you, and not be madly in love with everything you do.",
            "I would rather share one lifetime with you than face all the ages of this world alone.",
            "And when you smile, I find a reason to live a little more…",
            "There is no remedy for love, but to love more.",
            "I would not wish any companion in the world but you.",
            "Love has nothing to do with what you are expecting to give – which is everything.",
            "Love. What is love? No one can define it, it’s something so great, only God could design it. Yes, love is beyond, what man can define, for love is immortal, and God’s gift is divine.",
            "Love is friendship that has caught fire. It is quiet understanding, mutual confidence, sharing and forgiving. It is loyalty through good and bad times. It settles for less than perfection and makes allowances for human weaknesses.",
            "Love is enjoying the simple things in life, together.",
            "Love is a more powerful force than magic. You can trick the mind and even the heart, but never the soul. When a person is not free to love with their soul, that is not love and that is why a love spell can never truly work.” ― Nikki Jefford, Entangled.Love is a meeting of two souls, fully accepting the dark and the light within each other, bound by the courage to grow through struggle into bliss.",
            "At the beginning love is a fact, at the end it’s a theory",
            "Love is an unconditional commitment to an imperfect person. To love somebody isn’t just a strong feeling. It is a decision, a judgment and a promise.",
            "If fear is the great enemy of intimacy, love is its true friend.",
            "Love is not an obsession or a delusion. Love exists in longing and appreciation.",
            "What is love? In chemistry: a reaction. In physics: a formula. In my life: you.",
            "Love ? Love is when you are ready to do every possible and positive thing for someone.",
            "Love is when you meet someone who tells you something new about yourself.",
            "Love is a treasure which can never pay. The only way to keep it is to give it away.",
            "I don’t want anything from you at all. I just want to be the person you choose to sit next to in a room full with all the people you have known.",
            "Love is when one person knows all of you secrets, your deepest, darkest, most dreadful secrets of which no one else in the world knows… and yet in the end, that one person does not think any less of you; even if the rest of the world does.",
            "I didn’t choose to love you. It just happened.",
            "I have completely fallen for you. Everything you do, everything you say, everything you are. You’re my first thought in the morning, you’re my last thoughts before I fall asleep, and you’re almost every thought in between.",
            "If I could give you one thing in life, I would give you the ability to see yourself through my eyes, only then would you realize how special you are to me.",
            "I swear I couldn’t love you more than I do right now, and yet I know I will tomorrow.",
            "My heart is perfect because you are in it."
            };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);

        listView=(ListView)view.findViewById(R.id.listView);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.quotes_text_layout,quotes);
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
