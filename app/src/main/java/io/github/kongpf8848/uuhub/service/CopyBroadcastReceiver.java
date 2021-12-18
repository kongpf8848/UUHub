package io.github.kongpf8848.uuhub.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import io.github.kongpf8848.uuhub.util.AppUtils;

/**
 * Created by ThirtyDegreesRay on 2017/12/28 16:35:33
 */

public class CopyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Uri uri = intent.getData();
        if(uri != null){
            String content = uri.toString();
            AppUtils.copyToClipboard(context, content);
        }
    }

}
