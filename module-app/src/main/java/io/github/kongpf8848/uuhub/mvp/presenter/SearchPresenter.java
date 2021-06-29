

package io.github.kongpf8848.uuhub.mvp.presenter;

import androidx.annotation.NonNull;

import com.thirtydegreesray.dataautoaccess.annotation.AutoAccess;
import io.github.kongpf8848.uuhub.dao.DaoSession;
import io.github.kongpf8848.uuhub.mvp.contract.ISearchContract;
import io.github.kongpf8848.uuhub.mvp.model.SearchModel;
import io.github.kongpf8848.uuhub.mvp.presenter.base.BasePresenter;
import io.github.kongpf8848.uuhub.util.PrefUtils;
import io.github.kongpf8848.uuhub.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Created by ThirtyDegreesRay on 2017/8/25 14:56:23
 */

public class SearchPresenter extends BasePresenter<ISearchContract.View>
        implements ISearchContract.Presenter {

    @AutoAccess ArrayList<SearchModel> searchModels;

    @Inject
    public SearchPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void onViewInitialized() {
        super.onViewInitialized();
        if (searchModels == null) {
            createSearchModels();
        } else {
            mView.showSearches(searchModels);
        }
    }

    private void createSearchModels() {
        searchModels = new ArrayList<>();
        searchModels.add(new SearchModel(SearchModel.SearchType.Repository));
        searchModels.add(new SearchModel(SearchModel.SearchType.User));
    }

    public ArrayList<SearchModel> getSearchModels() {
        return searchModels;
    }

    @Override
    public ArrayList<SearchModel> getQueryModels(@NonNull String query) {
        for (SearchModel searchModel : searchModels) {
            searchModel.setQuery(query);
        }
        return searchModels;
    }

    @Override
    public SearchModel getSortModel(int page, int sortId) {
        return searchModels.get(page).setSortId(sortId);
    }

    @NonNull
    @Override
    public ArrayList<String> getSearchRecordList() {
        String records = PrefUtils.getSearchRecords();
        ArrayList<String> recordList = new ArrayList<>();
        if (!StringUtils.isBlank(records)) {
            String[] recordArray = records.split("\\$\\$");
            Collections.addAll(recordList, recordArray);
        }
        return recordList;
    }

    @Override
    public void addSearchRecord(@NonNull String record) {
        if(record.contains("$")){
            return;
        }
        int MAX_SEARCH_RECORD_SIZE = 30;
        ArrayList<String> recordList = getSearchRecordList();
        if(recordList.contains(record)){
            recordList.remove(record);
        }
        if(recordList.size() >= MAX_SEARCH_RECORD_SIZE){
            recordList.remove(recordList.size() - 1);
        }
        recordList.add(0, record);
        StringBuilder recordStr = new StringBuilder("");
        String lastRecord = recordList.get(recordList.size() - 1);
        for(String str : recordList){
            recordStr.append(str);
            if(!str.equals(lastRecord)){
                recordStr.append("$$");
            }
        }
        PrefUtils.set(PrefUtils.SEARCH_RECORDS, recordStr.toString());
    }

}
