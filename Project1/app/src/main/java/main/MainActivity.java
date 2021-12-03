package main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;

import service.FileDownService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main:MainActivity";
    Button btn_service ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_service = findViewById(R.id.btn_service);
        btn_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "aaa";
                Log.d(TAG, "onClick: name => " + name);

                Intent intent = new Intent
                        (getApplicationContext(), FileDownService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", name);
                startService(intent);
            }
        });
    }
}