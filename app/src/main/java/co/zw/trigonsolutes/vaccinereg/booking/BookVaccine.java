package co.zw.trigonsolutes.vaccinereg.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Calendar;
import java.util.HashMap;

import co.zw.trigonsolutes.vaccinereg.MainActivity;
import co.zw.trigonsolutes.vaccinereg.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookVaccine extends AppCompatActivity {

    private TextInputLayout txtFirstName, txtLastName, txtFonumber, txtDob, txtMsisdn, txtGenderWrap,txtIdNum;
    private MaterialSpinner spnrCenters;
    private EditText txtDob2;
    private TextView haveAcc;
    private Button btnRegister;
    private String[] genderArr = {"PARIRENYATWA", "HUTANO CENTER", "Gweru", "Machach"};
    final Calendar myCalendar = Calendar.getInstance();

    private static final String FILE_NAME = "example.txt";
    public  FirebaseAuth UId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_vaccine);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener( ) {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                txtDob2.setText( year +"-"+  dayFormatter(monthOfYear +1) +  "-"+ dayFormatter(dayOfMonth));



            }

        };


        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtFonumber = findViewById(R.id.txtPassword);
        spnrCenters = findViewById(R.id.spnrCenters);
        txtDob = findViewById(R.id.txtDob);
        txtDob2 = findViewById(R.id.txtDob2);
        txtIdNum = findViewById(R.id.txtIdNum);
        txtMsisdn = findViewById(R.id.txtMsisdn);
        btnRegister = findViewById(R.id.button_sign_up);




        txtDob2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(BookVaccine.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, genderArr);
        spnrCenters.setAdapter(adapter);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                startActivity(new Intent(BookVaccine.this, MainActivity.class));
            }
        });

    }

    private String  dayFormatter(int day){
        if (day<10){
            return "0" + day ;
        }else {
            return String.valueOf(day);
        }
    }

    private boolean validatePhoneNumber() {
        String val = txtMsisdn.getEditText().getText().toString();
        String checkspaces = "Aw{1,20}z";

        if (val.isEmpty()) {
            txtMsisdn.setError("Enter valid phone number");
            return false;
        } else if (val.length()<10) {
            txtMsisdn.setError("entent 10 digit phone number!");
            return false;
        } else {
            txtMsisdn.setError(null);
            txtMsisdn.setActivated(false);
            return true;
        }
    }
    private void registerUser() {

        final String firstName = this.txtFirstName.getEditText().getText().toString();
        final String lastName = this.txtLastName.getEditText().getText().toString();
        final String centers = genderArr[this.spnrCenters.getSelectedIndex()];
        final String phone = this.txtFonumber.getEditText().getText().toString();
        final String dob = this.txtDob.getEditText().getText().toString();
        final String gender = this.txtMsisdn.getEditText().getText().toString();
        final String email =this.txtMsisdn.getEditText().getText().toString();
        final String natId = this.txtIdNum.getEditText().getText().toString();

        if (validateName()) {
            validateName();
        }

        if (validateSurname()) {
            validateSurname();
        }

        if (TextUtils.isEmpty(centers)) {
            txtGenderWrap.setError("Please select you gender");
            txtGenderWrap.requestFocus();
            return;
        }

        if (validatePassword()) {
            validatePassword();
        }

        if (validateAge()) {
            validateAge();
        }

        if (validatePhoneNumber() ) {
            validatePhoneNumber();
        }

        if (validateId()) {
            validateId();
        }

        UId  = FirebaseAuth.getInstance();
        UId.getCurrentUser();

        FirebaseUser mUser =UId.getCurrentUser();

        String mUserUid = mUser.getUid();

        HashMap<String, Object> map = new HashMap<>();
        map.put("gender",gender );
        map.put("idNum",natId );
        map.put("phoneNum", phone);
        map.put("regDate",dob);
        map.put("regCenter",centers);


        FirebaseDatabase.getInstance().getReference().child("Vaccination Centers").child(centers).child(firstName + ""+ lastName)
        .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Booking successful", Toast.LENGTH_LONG).show();
            }
        });



    }
    private boolean validatePassword() {
        String val =txtFonumber.getEditText().getText().toString();
        //String checkspaces = "Aw{1,20}z";

        if (val.isEmpty()) {
            txtFonumber.setError("Password Cannot be Empty");
            return false;
        } else if (val.length()<4) {
            txtFonumber.setError("entent 4 digit Password!");
            return false;
        } else {
            txtFonumber.setError(null);
            txtFonumber.setActivated(false);
            return true;
        }
    }
    private boolean validateAge() {
        //DatePicker calaz = new DatePicker();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = myCalendar.get(Calendar.YEAR);
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 14) {
            //Toast.makeText(this, "You are too yong to register", Toast.LENGTH_SHORT).show();
            return false;
        }

       /*// DatePicker calaz = new DatePicker();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
       // int userAge = userDob;
       // int isAgeValid = currentYear - userAge;

        if (userDob < 16) {

            return false;
        }*/ else
            return true;
    }



    private boolean validateId() {
        String val = txtIdNum.getEditText().getText().toString();
        String checkId = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                //"(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                //".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            txtIdNum.setError("Field can not be empty");
            return false;
        } else if (val.matches(checkId)) {
            txtIdNum.setError("ID should have Numbers, one letter and without space in between ");
            return false;
        }

        else if (val.length()<10 | val.length()>14) {
            txtIdNum.setError("Your ID is invalid!");
            return false;
        } else {
            txtIdNum.setError(null);
            txtIdNum.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateName() {
        String val = txtFirstName.getEditText().getText().toString();
        String checkId = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                //".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            txtFirstName.setError("Field can not be empty");
            return false;
        } else if (val.matches(checkId)) {
            txtFirstName.setError("Name should contain letters only ");
            return false;
        }

        else if (val.length()<1 | val.length()>20) {
            txtFirstName.setError("Invalid Name!");
            return false;
        } else {
            txtFirstName.setError(null);
            txtFirstName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateSurname() {
        String val = txtLastName.getEditText().getText().toString();
        String checkId = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                //"(?=S+$)" +           //no white spaces
                //".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            txtLastName.setError("Field can not be empty");
            return false;
        } else if (val.matches(checkId)) {
            txtLastName.setError("Surname should contain letters only ");
            return false;
        }

        else if (val.length()<1 | val.length()>20) {
            txtLastName.setError("Invalid Surname!");
            return false;
        } else {
            txtLastName.setError(null);
            txtLastName.setErrorEnabled(false);

            return true;
        }
    }




}