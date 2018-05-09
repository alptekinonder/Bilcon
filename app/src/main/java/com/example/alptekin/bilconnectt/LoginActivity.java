package com.example.alptekin.bilconnectt;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
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

import java.net.InetAddress;

public class LoginActivity extends AppCompatActivity {
    //properties
    private EditText loginEmailText;
    private EditText loginPasswordText;
    private Button loginBtn;
    private Button login_registerBtn;
    private Button login_forgotBtn;

    private FirebaseAuth mAuth;

    private ProgressBar loginProgress;

    //methods

    /*
     *  This method gets called when the activity is first crated.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        mAuth = FirebaseAuth.getInstance();

        loginEmailText    = ( EditText ) findViewById( R.id.login_yourEmail );
        loginPasswordText = ( EditText ) findViewById( R.id.login_yourPass );
        loginBtn          = ( Button ) findViewById( R.id.login_login);
        login_registerBtn = ( Button ) findViewById( R.id.login_register);
        login_forgotBtn   = ( Button ) findViewById( R.id.login_forgot);
        loginProgress     = ( ProgressBar ) findViewById( R.id.login_progress);

        /*
        When clicked takes you to the registration page.
         */
        login_registerBtn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regIntent);
                finish();
            }
        });

        /*
        When clicked tries to login with the given email and password.
         */
        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                String loginEmail    = loginEmailText.getText().toString();
                String loginPassword = loginPasswordText.getText().toString();
                if( !TextUtils.isEmpty( loginEmail ) && !TextUtils.isEmpty( loginPassword ) )
                {
                    loginProgress.setVisibility( View.VISIBLE );
                    mAuth.signInWithEmailAndPassword( loginEmail, loginPassword ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete( @NonNull Task<AuthResult> task ) {
                            if( task.isSuccessful())
                            {
                                sendToMain();
                            }
                            else
                            {
                                String error = task.getException().getMessage();
                                Toast.makeText( LoginActivity.this, "Error: " + error, Toast.LENGTH_LONG ).show();
                            }
                            loginProgress.setVisibility( View.INVISIBLE );
                        }
                    });
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Bum badabim bada bum", Toast.LENGTH_LONG).show();
                }
            }
        });
        login_forgotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent( LoginActivity.this, ForgotActivity.class);
                //startActivity( intent);
                //finish();
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
        if ( currentUser != null)
        {
            sendToMain();
        }
        else
        {

        }
    }

    /*
     *  Helper method to send user to the main activity.
     */
    private void sendToMain()
    {
        Intent intent = new Intent( LoginActivity.this, HomePage.class);
        startActivity(intent);
        finish();
    }
    //Methods
    public final boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}
