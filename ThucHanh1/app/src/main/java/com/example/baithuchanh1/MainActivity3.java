package com.example.baithuchanh1;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;

import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity {

    private TimePicker timePicker;
    private EditText etMinutes;
    private Button btnSetAlarm, btnStopAlarm;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        timePicker = findViewById(R.id.timePicker);
        etMinutes = findViewById(R.id.etMinutes);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        btnStopAlarm = findViewById(R.id.btnStopAlarm);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        // Sự kiện khi nhấn nút Set Alarm
        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UnspecifiedImmutableFlag")
            @Override
            public void onClick(View view) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                XmlPullParser edtMinutes;
                int delayMinutes = Integer.parseInt(etMinutes.getText().toString());

                // Lấy thời điểm hiện tại và đặt thời gian báo thức
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);

                // Đặt báo thức với khoảng thời gian đã chọn
                long triggerTime = calendar.getTimeInMillis() + (delayMinutes * 60 * 1000);

                // Ý định cho báo thức (gọi đến AlarmReceiver)
                Intent intent = new Intent(MainActivity3.this, AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(MainActivity3.this, 0, intent, 0);

                // Đặt báo thức
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
            }
        });

        // Sự kiện khi nhấn nút Stop Alarm
        btnStopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pendingIntent != null) {
                    alarmManager.cancel(pendingIntent);
                }
            }
        });
    }

}