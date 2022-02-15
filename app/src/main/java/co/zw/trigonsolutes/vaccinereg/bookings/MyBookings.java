package co.zw.trigonsolutes.vaccinereg.bookings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.zw.trigonsolutes.vaccinereg.R;

public class MyBookings extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<BookingsModel> bookingResponses;
    private BookingAdapter myAdapter;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        recyclerView = findViewById(R.id.recycler_view_selfscreen);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookingResponses= new ArrayList<>();

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

       root = db.getReference().child("Vaccination Centers").child("Gweru");

       /* FirebaseRecyclerOptions<BookingsModel> options= new FirebaseRecyclerOptions.Builder<BookingsModel>()
                        .setQuery(root,BookingsModel.class)
                        .build();*/

        //myAdapter= new BookingAdapter(options);
        //recyclerView.setAdapter(myadapter);

        myAdapter = new BookingAdapter(this, bookingResponses);
        recyclerView.setAdapter(myAdapter);


       root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){

                    BookingsModel model = ds.getValue(BookingsModel.class);
                    bookingResponses.add(model);

                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}