package co.zw.trigonsolutes.vaccinereg.NewsFeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import co.zw.trigonsolutes.vaccinereg.R;

public class NewsFeed extends AppCompatActivity {

    private CardView b1,b2;
    private ImageView backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        backarrow = findViewById(R.id.back_arrow);
        b1 = findViewById(R.id.cancer);
        b2 = findViewById(R.id.bp);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewsFeed.this, ArticleFirst.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(ArticlesFinal.this, Article2.class);
               // startActivity(i);
            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent i = new Intent(ArticlesFinal.this, MainActivity.class);
                //startActivity(i);
            }
        });
    }
}