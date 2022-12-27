package com.example.wagba_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText cnfPassword;
    private EditText phone;
    private Button registerBtn;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private ProgressDialog progressDialog;
    private UserDatabase mUserDatabase;
    private UserDao mUserDao;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        mUserDatabase = UserDatabase.getDatabase(getApplicationContext());
        mUserDao = mUserDatabase.userDao();


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cnfPassword = findViewById(R.id.confirmpassword);
        phone = findViewById(R.id.phone);
        registerBtn = findViewById(R.id.registrationbtn);
        progressDialog = new ProgressDialog(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();
                String confirmPassInput = cnfPassword.getText().toString().trim();
                String usernameInput = username.getText().toString().trim();
                String phoneInput = phone.getText().toString().trim();

                if (usernameInput.isEmpty()){
                    username.setError("Username is required");
                    username.requestFocus();
                    return;
                }else if(emailInput.isEmpty()){
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
                    email.setError("Please enter a valid email");
                    email.requestFocus();
                    return;
                }else if(passwordInput.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }else if(passwordInput.length() < 6){
                    password.setError("Passwords must be 6 or more characters");
                    password.requestFocus();
                    return;
                }else if (!passwordInput.equals(confirmPassInput)){
                    cnfPassword.setError("Passwords don't match");
                    cnfPassword.requestFocus();
                }else if(phoneInput.isEmpty()){
                    phone.setError("Phone Number is required");
                    phone.requestFocus();
                    return;
                }else {
                    progressDialog.setMessage("Please wait while registration is being completed .....");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                }


                auth.createUserWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            sendUserToNextActivity();
                            Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    mUserDao.deleteAll();
                                    User mUser = new User(usernameInput, emailInput, phoneInput);
                                    mUserDao.insert(mUser);
                                }
                            }).start();

                        }else{
                            System.out.println("Error"+task.getException().getMessage());
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Failed to register! Try Again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        });
    }

    public void sendUserToNextActivity(){
        Intent intent = new Intent (RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}