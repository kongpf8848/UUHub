package io.github.kongpf8848.uuhub.mvp.presenter;

import io.github.kongpf8848.uuhub.dao.DaoSession;
import io.github.kongpf8848.uuhub.dao.LocalRepo;
import io.github.kongpf8848.uuhub.dao.LocalUser;
import io.github.kongpf8848.uuhub.dao.Trace;
import io.github.kongpf8848.uuhub.dao.TraceDao;
import io.github.kongpf8848.uuhub.mvp.contract.ITraceContract;
import io.github.kongpf8848.uuhub.mvp.model.Repository;
import io.github.kongpf8848.uuhub.mvp.model.TraceExt;
import io.github.kongpf8848.uuhub.mvp.model.User;
import io.github.kongpf8848.uuhub.mvp.presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by ThirtyDegreesRay on 2017/11/23 10:55:30
 */

public class TracePresenter extends BasePresenter<ITraceContract.View>
        implements ITraceContract.Presenter {

    private ArrayList<TraceExt> traceList;
    private TraceExt removedTrace;
    private int removedPosition;

    @Inject
    public TracePresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void onViewInitialized() {
        super.onViewInitialized();
        loadTraceList(1);
    }

    @Override
    public void loadTraceList(int page) {
        mView.showLoading();
        final ArrayList<TraceExt> tempTraceList = new ArrayList<>();
        daoSession.rxTx()
                .run(() -> {
                    List<Trace> traceListDb = daoSession.getTraceDao().queryBuilder()
                            .orderDesc(TraceDao.Properties.LatestTime)
                            .offset((page - 1) * 30)
                            .limit(page * 30)
                            .list();
                    for (Trace trace : traceListDb) {
                        TraceExt ext = TraceExt.generate(trace);
                        if ("user".equals(trace.getType())) {
                            LocalUser localUser = daoSession.getLocalUserDao().load(ext.getUserId());
                            ext.setUser(User.generateFromLocalUser(localUser));
                        } else {
                            LocalRepo localRepo = daoSession.getLocalRepoDao().load(ext.getRepoId());
                            ext.setRepository(Repository.generateFromLocalRepo(localRepo));
                        }
                        tempTraceList.add(ext);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    if (traceList == null || page == 1) {
                        traceList = tempTraceList;
                    } else {
                        traceList.addAll(tempTraceList);
                    }
                    mView.showTraceList(traceList);
                    mView.hideLoading();
                });
    }

    @Override
    public void removeTrace(int position) {
        removedTrace = traceList.remove(position);
        removedPosition = position;
        rxDBExecute(() -> daoSession.getTraceDao().deleteByKey(removedTrace.getId()));
    }

    @Override
    public void undoRemoveTrace() {
        traceList.add(removedPosition, removedTrace);
        mView.notifyItemAdded(removedPosition);
        rxDBExecute(() -> daoSession.getTraceDao().insert(removedTrace));
    }

    @Override
    public int getFirstItemByDate(long dateTime) {
        for (TraceExt trace : traceList) {
            if (trace.getLatestDate().getTime() == dateTime) {
                return traceList.indexOf(trace);
            }
        }
        return 0;
    }

}
