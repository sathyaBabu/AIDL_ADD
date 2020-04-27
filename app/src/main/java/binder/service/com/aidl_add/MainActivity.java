package binder.service.com.aidl_add;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    IaddService service ;
    AdditionServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ////////////////////////
//        int pid = android.os.Process.myPid();
//        android.os.Process.killProcess(pid);
        int pid = android.os.Process.myPid();


        Log.d("tag","in activity AIDL "+pid); // 744 int

        //  adb shell "set "ps | grep proj.dept.cog.com.aidl_atm"; kill -9 $2"
        //    adb shell pidof proj.dept.cog.com.aidl_atm
        // adb shell kill 4778  ( works )


        // kill process in unix
        // ps ax | grep proj.dept.cog.com.aidl_atm
        // it displays some thing

        initService();   // doBind(...);


    }

    public void  initService() {


        connection = new AdditionServiceConnection();

        Intent i = new Intent();
        i.setClassName("binder.service.com.aidl_add", binder.service.com.aidl_add.AditionService.class.getName());

        boolean ret = bindService(i, connection, Context.BIND_AUTO_CREATE);

        Log.d("tag", "initService() bound with " + ret);
    }

    /** Unbinds this activity from the service. Called in onDestroy() */
    private void releaseService() {

        unbindService(connection);

        connection = null;
        Log.d("tag", "releaseService() unbound.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag", "onDestroy() @ MainActivity.");

        releaseService();
    }



    class AdditionServiceConnection implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            service = IaddService.Stub.asInterface(iBinder);
            // we gave our IBinder to our interface..

            //service.add(5,10);

            Toast.makeText(MainActivity.this, "Connected...", Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            service = null;

        }


    }



    public void myClick(View v) {

        int v1, v2, res = -1;
        Log.d("tag", "myClick() @ MainActivity.");

        TextView result = (TextView) findViewById(R.id.result);

        EditText value1 = (EditText) findViewById(R.id.value1);
        EditText value2 = (EditText) findViewById(R.id.value2);

        v1 = Integer.parseInt(value1.getText().toString());
        v2 = Integer.parseInt(value2.getText().toString());

        Log.d("tag", value1.getText().toString()) ;
        Log.d("tag", value2.getText().toString()) ;

        try {
            //   res = 737;
            // res = service.add(4, 6);
            res = service.add(v1,v2);

        } catch (RemoteException e) {
            Log.d("tag", "onClick failed with: " + e);
            e.printStackTrace();
        }

        result.setText(Integer.toString(res));


        //   Intent intnt = new Intent(Process.SIGNAL_KILL);

//       int pid = android.os.Process.myPid();
//       android.os.Process.killProcess(pid);

    }


}
