package com.instilive.lovely;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShareQuotesActivity extends AppCompatActivity {

    private Bundle bundle;
    private EditText etQuotes;
    private Button btnShare;
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_quotes);

        etQuotes=(EditText)findViewById(R.id.quotes);
        btnShare=(Button)findViewById(R.id.shareQuoteBtn);

        bundle=getIntent().getExtras();
        etQuotes.setText(bundle.getString("Quote"));
        etQuotes.setSelection(etQuotes.getText().length());
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote=etQuotes.getText().toString();
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,quote);
                startActivity(Intent.createChooser(intent,"Share via"));
            }
        });

    }
}
