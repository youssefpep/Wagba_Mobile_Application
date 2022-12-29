package com.example.wagba_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wagba_app.Interfaces.UserDao;
import com.example.wagba_app.Models.User;
import com.example.wagba_app.Models.UserDatabase;
import com.example.wagba_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText emailText, passwordText;
    private Button login;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private ProgressDialog progressDialog;
    private UserDatabase mUserDatabase;
    private UserDao mUserDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        emailText = findViewById(R.id.loginEmail);
        passwordText = findViewById(R.id.loginPassword);
        login = findViewById(R.id.loginBtn);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        progressDialog = new ProgressDialog(this);
        mUserDatabase = UserDatabase.getDatabase(getApplicationContext());
        mUserDao = mUserDatabase.userDao();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                if(email.isEmpty()){
                    emailText.setError("Email is required");
                    emailText.requestFocus();
                    return;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailText.setError("Please enter a valid email");
                    emailText.requestFocus();
                    return;
                }else if(password.isEmpty()){
                    passwordText.setError("Password is required");
                    passwordText.requestFocus();
                    return;
                }else {
                    progressDialog.setMessage("Checking Credentials .....");
                    progressDialog.setTitle("Logging In");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                }
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    mUserDao.deleteAll();
                                    User mUser = new User(null, email, null);
                                    mUserDao.insert(mUser);
                                }
                            }).start();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Failed to log in! Please check credentials and try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void homeRedirect (View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onClick(View view) {

    }
}