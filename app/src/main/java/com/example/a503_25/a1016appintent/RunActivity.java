package com.example.a503_25.a1016appintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class RunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        final Button btn = (Button)findViewById(R.id.btn);
        final ListView listView = (ListView)findViewById(R.id.listView);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RunActivity.this,SubActivity.class);
                intent.putExtra("title","성공");
                startActivityForResult(intent,10);

            }
        });

    }
}
