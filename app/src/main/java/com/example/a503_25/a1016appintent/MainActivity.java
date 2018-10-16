package com.example.a503_25.a1016appintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //여기에 변수를 선언하면 클래스 내의 모든곳에서 변수를 사용할 수 있습니다.
    Button contact;
    Button camera;
    Button voice;
    Button map;
    Button browser;
    Button call;

    ImageView imageView;
    TextView resultView;

    //이미지 출력 크기를 위한 변수
    int reqWidth;
    int reqHeight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //여기에 변수를 만들면 final을 붙이지 않는 이상
        //다른 클래스나 메소드 안에서 사용이 안됩니다.


        contact = (Button)findViewById(R.id.contact);
        camera = (Button)findViewById(R.id.camera);
        voice = (Button)findViewById(R.id.voice);
        map = (Button)findViewById(R.id.map);
        browser = (Button)findViewById(R.id.browser);
        call = (Button)findViewById(R.id.call);

        reqWidth = getResources().getDimensionPixelOffset(R.dimen.request_image_width);
        reqHeight = getResources().getDimensionPixelOffset(R.dimen.request_image_height);

        imageView = (ImageView)findViewById(R.id.imageView);
        resultView = (TextView)findViewById(R.id.resultView);

        //버튼들의 이벤트를 처리할
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.contact:
                        Log.e("연락처클릭","실행");
                        //연락처 앱 실행
                        Intent contactintent = new Intent(Intent.ACTION_PICK);
                        //contactintent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                        //위에는 전화번호까지 나오고 밑에꺼는 이름만 나온다.
                        contactintent.setData(ContactsContract.Contacts.CONTENT_URI);

                        //실행
                        startActivityForResult(contactintent,10);
                        break;

                    case R.id.camera:
                        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraintent,11);
                        break;
                    case R.id.voice:
                        Intent voiceIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                        voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                        voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"음성인식 테스트");
                        startActivityForResult(voiceIntent, 12);
                        break;
                    case R.id.map:
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.56629,126.9889441"));
                        startActivity(mapIntent);
                        break;
                    case R.id.browser:
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.daum.net"));
                        startActivity(browserIntent);
                        break;
                    case R.id.call:
                        //전화기능은 퍼미션을 확인하고 수행해야 합니다. 있으면 바로 실행하고 없으면 else문에서 물어보자
                    if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                            Intent callIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-3333-3333"));
                            startActivity(callIntent);
                    }else{
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},100);
                    }

                        break;
                }
            }
        };

        //버튼의 이벤트 핸들러를 연결
        contact.setOnClickListener(listener);
        camera.setOnClickListener(listener);
        voice.setOnClickListener(listener);
        map.setOnClickListener(listener);
        browser.setOnClickListener(listener);
        call.setOnClickListener(listener);

        //satarActivityForREsult로 인텐트를 실행한 후
        //인텐트의 액티비티가 종료되었을 때 호출되는 메소드
        //requestCode는 호출할 때 설정한 코드이고(호출한 Activity 구분)
        //resultCode는 출력된 액티비티에서 전달해준 코드(응답 구분)


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("resultCode",requestCode+"");
        if(requestCode == 10 && resultCode == RESULT_OK){
            String result = data.getDataString();
            resultView.setText(result);

            //카메라가 없어지고 result_ok로 받아오는거다. 이미지뷰로 받아온다.
        }else if(requestCode == 11 && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }


    }

}
