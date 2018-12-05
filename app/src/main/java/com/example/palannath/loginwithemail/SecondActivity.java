package com.example.palannath.loginwithemail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button)findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Intent intent3 = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent3);
            }
        });
    }
}
