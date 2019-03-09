package com.durzoflint.patanjaliyogasutras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ChapterActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String CHAPTER = "chapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        TextView chapter1 = findViewById(R.id.chapter1);
        chapter1.setOnClickListener(this);
        TextView chapter2 = findViewById(R.id.chapter2);
        chapter2.setOnClickListener(this);
        TextView chapter3 = findViewById(R.id.chapter3);
        chapter3.setOnClickListener(this);
        TextView chapter4 = findViewById(R.id.chapter4);
        chapter4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = new Intent(ChapterActivity.this, SutraActivity.class);
        switch (id) {
            case R.id.chapter1:
                intent.putExtra(CHAPTER, 1);
                break;
            case R.id.chapter2:
                intent.putExtra(CHAPTER, 2);
                break;
            case R.id.chapter3:
                intent.putExtra(CHAPTER, 3);
                break;
            case R.id.chapter4:
                intent.putExtra(CHAPTER, 4);
                break;
        }
        startActivity(intent);
    }
}