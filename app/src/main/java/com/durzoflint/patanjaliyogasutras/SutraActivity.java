package com.durzoflint.patanjaliyogasutras;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.durzoflint.patanjaliyogasutras.ChapterActivity.CHAPTER;

public class SutraActivity extends AppCompatActivity implements View.OnClickListener {
    JSONArray chapter;
    int chapterNumber, verseNumber, chapterLength;
    TextView one, two, three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sutra);

        chapterNumber = getIntent().getIntExtra(CHAPTER, 0);

        setTitle("Chapter " + chapterNumber);

        ImageView next = findViewById(R.id.next);
        next.setOnClickListener(this);
        ImageView previous = findViewById(R.id.previous);
        previous.setOnClickListener(this);
        ImageView playPause = findViewById(R.id.play_pause);
        playPause.setOnClickListener(this);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);

        load();
        attachView();
    }

    void attachView() {
        try {
            JSONObject verse = chapter.getJSONObject(verseNumber);

            one.setText(verse.getString("verse"));
            two.setText(verse.getString("translation"));
            three.setText(verse.getString("meaning"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("SutraActivity", e.getMessage());
        }
    }

    void load() {
        verseNumber = 0;
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray chapters = jsonObject.getJSONArray("chapters");
            JSONObject chapterJSONObject = chapters.getJSONObject(chapterNumber - 1);
            chapter = chapterJSONObject.getJSONArray("chapter " + chapterNumber);
            chapterLength = chapter.length();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("SutraActivity", e.getMessage());
        }
    }

    String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.next:
                if (verseNumber < chapterLength) {
                    verseNumber++;
                    attachView();
                }
                break;
            case R.id.previous:
                if (verseNumber > 0) {
                    verseNumber--;
                    attachView();
                }
                break;
            case R.id.play_pause:
                break;
        }
    }
}