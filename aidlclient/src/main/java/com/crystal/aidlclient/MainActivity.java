package com.crystal.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.aidlserver.service.ICalculate;
import com.crystal.aidlserver.service.IGetMsg;

public class MainActivity extends AppCompatActivity {

    //private IGetMsg getMsg;
    private ICalculate calculate;

    private Button btn_get;
    private TextView tv_get;

    /*private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            getMsg = IGetMsg.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            getMsg = null;
        }
    };*/

    private ServiceConnection mCalServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            calculate = ICalculate.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            calculate = null;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_get = (Button) findViewById(R.id.btn_get);
        tv_get = (TextView) findViewById(R.id.tv_get);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    /*String msg = getMsg.getMsg("aaaaaaaaaaaaa");
                    tv_get.setText(msg);*/
                    String result = "计算结果：" + calculate.calSum(1 , 2);
                    tv_get.setText(result);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Intent service = new Intent();
        service.setAction("com.crystal.aidl.POST_MSG_SERVICE");
        bindService(service, mCalServiceConn, BIND_AUTO_CREATE);
        Toast.makeText(MainActivity.this, "绑定服务成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
