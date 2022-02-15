package co.zw.trigonsolutes.vaccinereg.bookings;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.zw.trigonsolutes.vaccinereg.MainActivity;
import co.zw.trigonsolutes.vaccinereg.R;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    private Context context;

        private ArrayList<BookingsModel> bookingResponses;


    /*public BookingAdapter(  @NonNull FirebaseRecyclerOptions<BookingsModel> options) {

        super(options);


    }*/

   public BookingAdapter( Context context, ArrayList<BookingsModel> BookingsModel) {
        this.context = context;
        this.bookingResponses = BookingsModel;


    }





    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, int position)
    {


        BookingsModel incomingData = bookingResponses.get(position);


            holder.textViewName.setText(incomingData.getRegCenter());
            holder.textViewDate.setText(incomingData.getRegDate());



    }

   /* @Override
    protected void onBindViewHolder(@NonNull BookingViewHolder holder, int position, @NonNull BookingsModel bookingsModel) {

        holder.textViewName.setText(bookingsModel.getRegCenter());
        holder.textViewDate.setText(bookingsModel.getRegDate());
        holder.warning2.setText(bookingsModel.getUId());
    }*/


    @NonNull
    @Override
    public BookingAdapter.BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View v= LayoutInflater.from(context).inflate(R.layout.results_list,parent,false);

        return new BookingViewHolder(v);
    }
    @Override
    public int getItemCount()
    {
        return bookingResponses.size();
    }





    //RecyclerView View Holder
    public static class BookingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewName;
        private TextView textViewDate;
        private TextView textViewResult, warning,warning2;
        private Button getTested, symptoms;
        ImageView testImage;

       public  BookingViewHolder(@NonNull View itemView) {


            super(itemView);

            textViewName = itemView.findViewById(R.id.name_of_user);
            textViewDate = itemView.findViewById(R.id.date);
            textViewResult = itemView.findViewById(R.id.result);

            warning2 = itemView.findViewById(R.id.textView_in_not);
            getTested = itemView.findViewById(R.id.find_test_center);
            testImage = itemView.findViewById(R.id.result_image);
            symptoms = itemView.findViewById(R.id.find_smptoms);

            getTested.setOnClickListener(this);
            symptoms.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.find_test_center:
                case R.id.find_smptoms:


                    Intent intent = new Intent (v.getContext(), MainActivity.class);
                    // put all that you need in intent
                    v.getContext().startActivity(intent);

                    break;
                // put all that you need in intent


                default:
                    break;
            }
        }

    }


}
