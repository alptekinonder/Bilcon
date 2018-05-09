package com.example.alptekin.bilconnectt;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;

public class ForgotActivity extends AppCompatActivity {

    private EditText forgot_email;
    private Button forgot_send;
    private Button forgot_goBack;
    private ProgressBar forgot_progress;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        mAuth = FirebaseAuth.getInstance();
        forgot_send = findViewById( R.id.forgot_send );
        forgot_email = findViewById( R.id.forgot_yourEmail);
        forgot_goBack = findViewById( R.id.forgot_go_Back);
        forgot_progress = findViewById( R.id.forgot_progress);
        forgot_goBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                sendToLogin();
            }
        });
        forgot_send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                forgot_progress.setVisibility( View.VISIBLE );
                final String email = forgot_email.getText().toString();
                if( !TextUtils.isEmpty( email ) ) {
                    mAuth.fetchProvidersForEmail( email ).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                            if( task.isSuccessful() )
                            {
                                FirebaseAuth.getInstance().sendPasswordResetEmail( email ).addOnCompleteListener( new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete( @NonNull Task<Void> task)
                                    {
                                        if ( task.isSuccessful() )
                                        {
                                            Toast.makeText(ForgotActivity.this, "An email send to " + email + " to reset password.", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent( ForgotActivity.this, LoginActivity.class);
                                            mAuth.signOut();
                                            startActivity( intent);
                                            finish();
                                        }
                                        else
                                        {
                                            String error = task.getException().getMessage();
                                            Toast.makeText(ForgotActivity.this, "error: " + error, Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            }
                            else
                            {
                                String error = task.getException().getMessage();
                                Toast.makeText(ForgotActivity.this, "error: " + error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    forgot_progress.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(ForgotActivity.this, "Please enter an email.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /*
     * This method gets called when the application first started and if this activity is active at the moment.
     * Checks if use is looged in acts accordingly.
     */
    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void sendToLogin()
    {
        Intent loginIntent = new Intent (ForgotActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }


}
