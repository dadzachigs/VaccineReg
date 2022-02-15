package co.zw.trigonsolutes.vaccinereg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import co.zw.trigonsolutes.vaccinereg.NewsFeed.NewsFeed;
import co.zw.trigonsolutes.vaccinereg.booking.BookVaccine;
import co.zw.trigonsolutes.vaccinereg.bookings.MyBookings;
import co.zw.trigonsolutes.vaccinereg.userAuth.SignUp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public CardView book, bookings,article2,article1,article3;
    DrawerLayout drawer;
    NavigationView mNavigationView;
    ImageView menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawer = findViewById(R.id.drawer1);
        mNavigationView = findViewById(R.id.navigation);
        menu=findViewById(R.id.menu);

        book = findViewById(R.id.booking);
        bookings = findViewById(R.id.mybookings);


        article1 = findViewById(R.id.v1);
        article2 = findViewById(R.id.v2);
        article3 = findViewById(R.id.v3);

        mNavigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.close_drawer, R.string.open_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // open drawer here
                drawer.openDrawer(Gravity.LEFT);

            }
        });


        article1.setOnClickListener(this);
        article2.setOnClickListener(this);
        article3.setOnClickListener(this);
        bookings.setOnClickListener(this);
        book.setOnClickListener(this);

        drawerOpen();

    }

    public void drawerOpen() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {

            //finish();
        }

    }

    @Override


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.booking:
                startActivity(new Intent(MainActivity.this, BookVaccine.class));
                break;

            case R.id.v1:

            case R.id.v2:

            case R.id.v3:
                myIntent(MainActivity.this, NewsFeed.class);
                break;

            case R.id.mybookings:
                startActivity(new Intent(MainActivity.this, MyBookings.class));
                break;
        }
    }

    //overall new intent method
    private void myIntent(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);

    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.signin:

            case R.id.out:
                //logout();
                break;

            case R.id.signup:
                Intent intent2 = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent2);
                break;

            case R.id.book:
                Intent intent3 = new Intent(MainActivity.this, BookVaccine.class);
                startActivity(intent3);
                break;
            case R.id.bookings:
                Intent intent4 = new Intent(MainActivity.this, MyBookings.class);
                startActivity(intent4);
                break;
            case R.id.feeds:
                Intent intent5 = new Intent(MainActivity.this, NewsFeed.class);
                startActivity(intent5);
                break;

            case R.id.action_settings:
                //opening a link
                String url = "www.pathlabs.co.zw";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
        }


        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}