package com.example.baithuchanh1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Hiển thị thông báo khi báo thức được kích hoạt
        Toast.makeText(context, "Báo thức đã kêu!", Toast.LENGTH_LONG).show();
    }
}
