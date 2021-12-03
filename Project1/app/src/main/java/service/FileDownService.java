package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import main.MainActivity;

public class FileDownService extends Service {
    private static final String TAG = "main:MyService";
    Thread thread ;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: 호출됨");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called");

        if (intent == null) {
            return Service.START_STICKY; //서비스가 종료되어도 자동으로 다시 실행시켜줘!
        } else {
            // intent가 null이 아니다.
            // 액티비티에서 intent를 통해 전달한 내용을 뽑아낸다.(if exists)
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");
            // etc..
            processCommand(intent);
            Log.d(TAG, "전달받은 데이터: " + command+ ", " +name);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    for (int i = 0 ; i<100; i++){
                        MainActivity.setFileProgress(i);
                        Thread.sleep(1000);
                        Log.d(TAG, "onStartCommand() called");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 호출됨");
        thread.interrupt();
    }
}