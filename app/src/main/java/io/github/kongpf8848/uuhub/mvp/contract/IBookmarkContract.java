package io.github.kongpf8848.uuhub.mvp.contract;

import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseContract;
import io.github.kongpf8848.uuhub.mvp.contract.base.IBaseListContract;
import io.github.kongpf8848.uuhub.mvp.model.BookmarkExt;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2017/11/22 15:58:04
 */

public interface IBookmarkContract {

    interface View extends IBaseContract.View, IBaseListContract.View{
        void showBookmarks(ArrayList<BookmarkExt> bookmarks);
        void notifyItemAdded(int position);
    }

    interface Presenter extends IBaseContract.Presenter<IBookmarkContract.View>{
        void loadBookmarks(int page);
        void removeBookmark(int position);
        void undoRemoveBookmark();
    }

}
