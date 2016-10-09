package com.silverfox.avi.datastructures;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class AccountActivity extends AppCompatActivity {

    Button tv;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        tv = (Button) findViewById(R.id.textView);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.i("Firebase: ", firebaseAuth.toString());
                if(firebaseAuth.getCurrentUser() == null) {
                    Log.i("FirebaseNull: ", firebaseAuth.toString());
                    startActivity(new Intent(AccountActivity.this, Register.class));
                } else {
                    Log.i("FirebaseNotNull: ", firebaseAuth.toString());
                }
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountActivity.this, "Logged Out", Toast.LENGTH_LONG).show();
                mAuth.signOut();
            }
        });
    }
}
