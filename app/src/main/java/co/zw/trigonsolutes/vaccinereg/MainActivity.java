package co.zw.trigonsolutes.vaccinereg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

import co.zw.trigonsolutes.vaccinereg.booking.BookVaccine;
import co.zw.trigonsolutes.vaccinereg.bookings.MyBookings;

public class MainActivity extends AppCompatActivity {

    public CardView book, bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        book = findViewById(R.id.booking);
        bookings = findViewById(R.id.mybookings);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BookVaccine.class));
            }
        });

        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyBookings.class));
            }
        });



    }

}