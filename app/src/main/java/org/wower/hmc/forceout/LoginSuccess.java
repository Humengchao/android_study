package org.wower.hmc.forceout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginSuccess extends AppCompatActivity {
    private static final String TAG = "LoginSuccess";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        Button forceOffline = (Button) findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("org.wower.hmc.broadcastbestpractice.FORCE_OFFLINE");
                intent.setPackage("org.wower.hmc.forceout");
                sendBroadcast(intent);
                Log.d(TAG, "onClick: 11111111111111111111111111111111111111111111111111111");
            }
        });

    }
}