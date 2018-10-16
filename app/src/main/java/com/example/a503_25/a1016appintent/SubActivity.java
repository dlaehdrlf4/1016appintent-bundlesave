package com.example.a503_25.a1016appintent;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    int value = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        final TextView textView = (TextView)findViewById(R.id.count);
        Button btn = (Button)findViewById(R.id.btn);
        EditText editText = (EditText)findViewById(R.id.text);

        Intent abc = getIntent();
        String text = abc.getStringExtra("title");
        editText.setText(text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + 1;
                textView.setText(value + "");

            }
        });
    }
    //오버라이드 재정의
    //액티비티가 종료되기 직전에 호출되는 메소드 복원할 데이터 저장
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //데이터 저장
        outState.putInt("value",value);
    }
    // 액티비티가 시작할 때 호출되는 메소드
    // 데이터를 복원을 합니다.
    @Override
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        int value = bundle.getInt("value");
        this.value = value;
        TextView textView = (TextView)findViewById(R.id.count);
        textView.setText(value + "");

    }
}
