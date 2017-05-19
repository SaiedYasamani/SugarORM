package sematecandroidfridays.sugarorm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Saied on 19/05/2017.
 */

public class CallReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Incoming Call",Toast.LENGTH_LONG).show();
    }
}
