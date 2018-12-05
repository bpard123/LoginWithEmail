package com.example.palannath.loginwithemail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {


    private EditText UserName;
    private  EditText UserPassword;
    private  EditText UserEmail;
    private Button register;
    private TextView UserLogin;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        UserName = (EditText)findViewById(R.id.etUserName);
        UserPassword = (EditText)findViewById(R.id.etUserPassword);
        UserEmail = (EditText)findViewById(R.id.etUserEmail);
        register = (Button)findViewById(R.id.btnRegister);
        UserLogin = (TextView)findViewById(R.id.tvUserLogin);

        firebaseAuth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validate()) {
                    String user_email = UserEmail.getText().toString().trim();
                    String user_password = UserPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                SendEmailVerification();;
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Registration is Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private  Boolean Validate()
    {
        Boolean result = false;

        String name = UserName.getText().toString();
        String password = UserPassword.getText().toString();
        String email = UserEmail.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty())
        {
            Toast.makeText(RegistrationActivity.this,"Please Enter All The Details",Toast.LENGTH_LONG).show();
        }
        else
        {
            result = true;
        }
        return  result;
    }


    private void SendEmailVerification()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(RegistrationActivity.this,"Verification Email; is Sent",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        Intent intent4 = new Intent(RegistrationActivity.this,MainActivity.class);
                        startActivity(intent4);
                    }
                    else
                    {
                        Toast.makeText(RegistrationActivity.this,"Internet Problem",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
