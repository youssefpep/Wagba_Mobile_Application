package com.example.wagba_app;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.view.WindowManager;

        import androidx.appcompat.app.AppCompatActivity;

public class Splash_Screen extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
    }

    public void registerRedirect(View view){
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

    }

    public void loginRedirect(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

    }


}
