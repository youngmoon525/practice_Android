package splash;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;

import main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ProgressDialog progressDialog = new ProgressDialog(SplashActivity.this);
        progressDialog.setTitle("지금은 프로젝트1을 로딩중인척하는중이빈다.");
        progressDialog.show();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                progressDialog.dismiss();
                startActivity(mainIntent);
                finish();
            }
        }, 2000);
    }
}