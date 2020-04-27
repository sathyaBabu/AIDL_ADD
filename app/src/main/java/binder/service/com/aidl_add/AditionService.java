package binder.service.com.aidl_add;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class AditionService extends Service {
    public AditionService() {
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "In Service onCreate Now...", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service destroyed...", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

//      return   new IaddService.Stub() {
//
//          @Override
//          public int add(int value1, int value2) throws RemoteException {
//              return value1 + value2 ;
//          }
//      };
//        //


        return  new IaddService.Stub() {

            @Override
            public int add(int value1, int value2) throws RemoteException {
                return value1 + value2;
            }

            // STICKY_SERVICE  : Meaning This service will never die....
        };
    }


}
