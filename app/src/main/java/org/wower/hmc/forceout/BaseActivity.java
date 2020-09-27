package org.wower.hmc.forceout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("org.wower.hmc.broadcastbestpractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    class ForceOfflineReceiver extends BroadcastReceiver{
        private static final String TAG = "ForceOfflineReceiver";

        /**
         * 这个onreceive方法还是没有接收到这个广播！！！！！！！！！！！！！！！！！！！！！！！！！
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: 222222222222222222");
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. please try to login again");
            builder.setCancelable(false);  // 点击返回键没用
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();  // 销毁所有活动
                    Intent intent = new Intent(BaseActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
