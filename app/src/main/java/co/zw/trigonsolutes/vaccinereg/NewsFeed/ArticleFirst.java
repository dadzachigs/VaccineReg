package co.zw.trigonsolutes.vaccinereg.NewsFeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;

import co.zw.trigonsolutes.vaccinereg.MainActivity;
import co.zw.trigonsolutes.vaccinereg.R;

public class ArticleFirst extends AppCompatActivity {

    private PDFView pdfView;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_first);
        pdfView = findViewById(R.id.pdfView);
        backButton = findViewById(R.id.back_arrow);


        pdfView.fromAsset("zim_rankings.pdf").load();


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ArticleFirst.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}