package com.example.wagba_app.Activities;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

        import androidx.appcompat.app.AppCompatActivity;

        import com.example.wagba_app.Activities.LoginActivity;
        import com.example.wagba_app.Activities.RegisterActivity;
        import com.example.wagba_app.R;

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
