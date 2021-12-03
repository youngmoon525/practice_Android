package main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;

import service.FileDownService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main:MainActivity";
    Button btn_service ;
    public static ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("서비스 이용해보기");
        btn_service = findViewById(R.id.btn_service);
        progressBar = findViewById(R.id.progress_bar);
        btn_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });
    }

    private void showMessage() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("종료하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "예 버튼이 눌렸습니다 => " + which;

                Intent intent = new Intent
                        (getApplicationContext(), FileDownService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", message);

                startService(intent);
            }
        });

        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "아니요 버튼이 눌렸습니다 => " + which;
                Intent intent = new Intent
                        (getApplicationContext(), FileDownService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", message);

                stopService(intent);
            }
        });

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "취소 버튼이 눌렸습니다 => " + which;

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public static void setFileProgress(int i){
        progressBar.setProgress(i);
    }

}