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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    //Properties

    private EditText reg_email;
    private EditText reg_password;
    private EditText reg_confirm;
    private Button reg_goBack;
    private Button reg_btn;
    private ProgressBar reg_progress;
    private FirebaseAuth mAuth;

    // methods

    // this method works when the activity is first created.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        reg_email = ( EditText ) findViewById( R.id.reg_yourEmail );
        reg_password = ( EditText ) findViewById( R.id.reg_yourPass );
        reg_confirm = ( EditText ) findViewById( R.id.reg_confirmPass );
        reg_goBack = ( Button ) findViewById( R.id.reg_go_Back );
        reg_btn = ( Button ) findViewById( R.id.reg_register);
        reg_progress = ( ProgressBar ) findViewById( R.id.reg_progress );

        // Sends you back to the login page.
        reg_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent (RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        // When clicked it tries to register the given email to the app.
        reg_btn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                String email = reg_email.getText().toString();
                String pass = reg_password.getText().toString();
                String confirm_pass = reg_confirm.getText().toString();

                if( !TextUtils.isEmpty( email ) && !TextUtils.isEmpty( pass ) && !TextUtils.isEmpty( confirm_pass ) )
                {
                    if( pass.equals( confirm_pass ) )
                    {
                        mAuth.createUserWithEmailAndPassword( email, pass ).addOnCompleteListener( new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if( task.isSuccessful() )
                                {
                                    sendToMain();
                                }
                                else
                                {
                                    String error = task.getException().getMessage();
                                    Toast.makeText( RegisterActivity.this, "error: " + error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText( RegisterActivity.this, "Confirm Password and Password field does not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    /*
     *  When the application first starts and if this activity is active this method gets called.
     *  This method checks if the user logged in or not and acts accordingly
     */
    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if( currentUser != null )
        {
            sendToMain();
        }
    }

    /*
     * A helper method to send the user to the main.
     */
    private void sendToMain()
    {
        Intent mainIntent = new Intent( RegisterActivity.this, MainActivity.class );
        startActivity( mainIntent );
        finish();
    }
}
