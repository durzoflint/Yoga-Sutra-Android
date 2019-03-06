package com.durzoflint.patanjaliyogasutras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            int c = 0;
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("chapters");
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                JSONArray chapterTemp = jo_inside.getJSONArray("chapter " + (i + 1));

                for (int j = 0; j < chapterTemp.length(); j++) {
                    JSONObject verseTemp = chapterTemp.getJSONObject(j);

                    String verse = verseTemp.getString("verse");
                    c++;
                    Log.d("Abhinav: " + i + ":" + j, "<" + verse + ">");
                }
            }
            Log.d("Abhinav", "total: " + c);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void test() {

    }

    String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}