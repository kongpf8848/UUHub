

package io.github.kongpf8848.uuhub.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import androidx.annotation.NonNull;

import io.github.kongpf8848.uuhub.common.AppEventBus;
import io.github.kongpf8848.uuhub.common.Event;
import io.github.kongpf8848.uuhub.util.NetHelper;

/**
 * Created by ThirtyDegreesRay on 2016/8/25 16:07
 */

public class NetBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, @NonNull Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            int preNetStatus = NetHelper.INSTANCE.getNetStatus();
            NetHelper.INSTANCE.checkNet();
            int curNetStatus = NetHelper.INSTANCE.getNetStatus();
            AppEventBus.INSTANCE.getEventBus().post(new Event.NetChangedEvent(preNetStatus, curNetStatus));
        }
    }

}
