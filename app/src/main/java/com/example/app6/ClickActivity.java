package com.example.app6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ClickActivity extends AppCompatActivity {
    TextView textViewClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        textViewClick = findViewById(R.id.textViewClick);

        loadSettings();

        if(MainActivity.load){
            Intent intent = new Intent(ClickActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
            textViewClick.setText("Click on the screen");
        }
    }

    public void loadSettings() {
        MainActivity.load = getSharedPreferences("setting", MODE_PRIVATE).getBoolean(MainActivity.SAVE_LOAD, false);
    }

    public void onClickStart(View view){
        Intent intent = new Intent(ClickActivity.this, MainActivity.class);
        startActivity(intent);
    }
}