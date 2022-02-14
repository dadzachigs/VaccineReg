package co.zw.trigonsolutes.vaccinereg.userAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import co.zw.trigonsolutes.vaccinereg.MainActivity;
import co.zw.trigonsolutes.vaccinereg.R;

public class LogIn extends AppCompatActivity {

    private Button btnLogIn ;
    private TextView have_acc;
    private TextInputLayout txtEmail, txtPassword;

    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        txtEmail = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogIn = findViewById(R.id.btnLogin);

        auth= FirebaseAuth.getInstance();


        btnLogIn.setOnClickListener((View v) -> {

            if (validateUsername() & validatePassword()) {
                validateUsername();
                validatePassword();


                final String username = this.txtEmail.getEditText().getText().toString();
                final String password = this.txtPassword.getEditText().getText().toString();

                userSignin(username, password);
            };


        });


    }

    private void userSignin(String username, String password) {

        auth.signInWithEmailAndPassword(username,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Toast.makeText(LogIn.this, "Login sucessful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LogIn.this, MainActivity.class));
                finish();
            }
        });



    }


    private boolean validateUsername() {
        String val = txtEmail.getEditText().getText().toString();
        String checkspaces = "Aw{1,20}z";

        if (val.isEmpty()) {
            txtEmail.setError("Enter valid phone number");
            return false;

        } else {
            txtEmail.setError(null);
            txtEmail.setActivated(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = txtPassword.getEditText().getText().toString();
        //String checkspaces = "Aw{1,20}z";

        if (val.isEmpty()) {
            txtPassword.setError("Password Cannot be Empty");
            return false;
        } else if (val.length()<4) {
            txtPassword.setError("incomplete  Password!");
            return false;
        } else {
            txtPassword.setError(null);
            txtPassword.setActivated(false);
            return true;
        }

    }
}