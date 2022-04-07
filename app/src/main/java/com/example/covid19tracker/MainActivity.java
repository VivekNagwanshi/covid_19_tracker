package com.example.covid19tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

 /*   String prevStarted = "yes";
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        } else {
            moveToSecondary();
        }
    }

    private void moveToSecondary() {
        Intent intent = new Intent(this,Verify.class);
        startActivity(intent);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputmobile = findViewById(R.id.et_phone);
        Button getotp = findViewById(R.id.GetOtp);
        final ProgressBar progressBar = findViewById(R.id.progressBar);

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputmobile.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Plese Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (inputmobile.getText().toString().length() == 10) {
                    progressBar.setVisibility(View.VISIBLE);
                    getotp.setVisibility(View.INVISIBLE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + inputmobile.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            MainActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(PhoneAuthCredential credential) {
                                    progressBar.setVisibility(View.GONE);
                                    getotp.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    getotp.setVisibility(View.VISIBLE);
                                    Toast.makeText(MainActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBar.setVisibility(View.GONE);
                                    getotp.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(getApplicationContext(), GetOtp.class);
                                    intent.putExtra("Mobile", inputmobile.getText().toString());
                                    intent.putExtra("verification", verificationId);
                                    startActivity(intent);
                                }
                            }
                    );
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}




