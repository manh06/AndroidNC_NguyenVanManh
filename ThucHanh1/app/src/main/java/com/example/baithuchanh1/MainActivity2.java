package com.example.baithuchanh1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    private TextView tvThread1, tvThread2, tvThread3;
    private Button btnThread1, btnThread2, btnThread3;
    private Handler handler;
    private boolean thread1Running = true, thread2Running = true, thread3Running = true;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvThread1 = findViewById(R.id.tvThread1);
        tvThread2 = findViewById(R.id.tvThread2);
        tvThread3 = findViewById(R.id.tvThread3);

        btnThread1 = findViewById(R.id.btnThread1);
        btnThread2 = findViewById(R.id.btnThread2);
        btnThread3 = findViewById(R.id.btnThread3);

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        tvThread1.setText("Thread 1: " + msg.obj);
                        break;
                    case 2:
                        tvThread2.setText("Thread 2: " + msg.obj);
                        break;
                    case 3:
                        tvThread3.setText("Thread 3: " + msg.obj);
                        break;
                }
            }
        };

        // Start threads after 2s
        new Handler().postDelayed(this::startThreads, 2000);

        // Button listeners to start/stop threads
        btnThread1.setOnClickListener(view -> thread1Running = !thread1Running);
        btnThread2.setOnClickListener(view -> thread2Running = !thread2Running);
        btnThread3.setOnClickListener(view -> thread3Running = !thread3Running);
    }

    private void startThreads() {
        // Thread 1: Random numbers from 50 to 100
        new Thread(() -> {
            Random random = new Random();
            while (true) {
                if (thread1Running) {
                    int num = random.nextInt(51) + 50;
                    handler.obtainMessage(1, num).sendToTarget();
                    try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        }).start();

        // Thread 2: Odd numbers increasing from 1
        new Thread(() -> {
            int num = 1;
            while (true) {
                if (thread2Running) {
                    handler.obtainMessage(2, num).sendToTarget();
                    num += 2;
                    try { Thread.sleep(2500); } catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        }).start();

        // Thread 3: Increasing numbers from 0
        new Thread(() -> {
            int num = 0;
            while (true) {
                if (thread3Running) {
                    handler.obtainMessage(3, num).sendToTarget();
                    num++;
                    try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        }).start();
    }
}