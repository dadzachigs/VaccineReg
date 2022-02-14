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
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import co.zw.trigonsolutes.vaccinereg.R;

public class SignUp extends AppCompatActivity {


    private Button btnLogIn ;
    private TextView have_acc;
    private TextInputLayout txtEmail, txtPassword;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        have_acc= findViewById(R.id.btnCreateAccount);

        txtEmail = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogIn = findViewById(R.id.btnLogin);

        auth=FirebaseAuth.getInstance();

       have_acc.setOnClickListener(new View.OnClickListener() {
               @Override
         public void onClick(View view) {

                   startActivity(new Intent(SignUp.this, LogIn.class));

                    }
           });
        btnLogIn.setOnClickListener((View v) -> {

            if (validateUsername() & validatePassword()) {
                validateUsername();
                validatePassword();



                final String username = this.txtEmail.getEditText().getText().toString();
                final String password = this.txtPassword.getEditText().getText().toString();

                userLogIn(username, password);
            };


        });


    }

    private void userLogIn(String username, String password) {

          auth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {

                  if(task.isSuccessful()){

                      Toast.makeText(SignUp.this,"Reg sucessful", Toast.LENGTH_SHORT ).show();
                      startActivity(new Intent(SignUp.this, LogIn.class));
                  }else{

                      Toast.makeText(SignUp.this,"Reg not sucessful", Toast.LENGTH_SHORT ).show();

                  }

              }
          });



    }

//Method to validate input
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

    //Method to validate input
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