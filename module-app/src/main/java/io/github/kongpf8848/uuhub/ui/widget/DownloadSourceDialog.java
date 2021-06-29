package io.github.kongpf8848.uuhub.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.github.kongpf8848.uuhub.R;
import io.github.kongpf8848.uuhub.mvp.model.DownloadSource;
import io.github.kongpf8848.uuhub.mvp.model.Release;
import io.github.kongpf8848.uuhub.mvp.model.ReleaseAsset;
import io.github.kongpf8848.uuhub.ui.adapter.DownloadSourcesAdapter;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/9/16 23:21:22
 */

public class DownloadSourceDialog {

    public static void show(Context context, String repoName, String tagName, Release release){
        DownloadSourcesAdapter adapter = new DownloadSourcesAdapter(context, repoName, tagName);
        adapter.setData(getDownloadSources(context, release));

        final RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        new AlertDialog.Builder(context)
                .setCancelable(true)
                .setTitle(R.string.download)
                .setView(recyclerView)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private static ArrayList<DownloadSource> getDownloadSources(Context context, Release release){
        ArrayList<DownloadSource> sources = new ArrayList<>();
        for(ReleaseAsset asset : release.getAssets()){
            sources.add(new DownloadSource(asset.getDownloadUrl(), false, asset.getName(), asset.getSize()));
        }
        sources.add(new DownloadSource(release.getZipballUrl(), true,
                context.getString(R.string.source_code_zip)));
        sources.add(new DownloadSource(release.getTarballUrl(), true,
                context.getString(R.string.source_code_tar)));
        return sources;
    }

}
