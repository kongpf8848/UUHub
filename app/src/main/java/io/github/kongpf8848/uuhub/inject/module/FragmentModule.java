

package io.github.kongpf8848.uuhub.inject.module;

import android.content.Context;

import io.github.kongpf8848.uuhub.inject.FragmentScope;
import io.github.kongpf8848.uuhub.ui.fragment.base.BaseFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 2017/7/18.
 *
 * @author ThirtyDegreesRay
 */
@Module
public class FragmentModule {

    private BaseFragment mFragment;

    public FragmentModule(BaseFragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public BaseFragment provideFragment(){
        return mFragment;
    }

    @Provides
    @FragmentScope
    public Context provideContext(){
        return mFragment.getActivity();
    }
}
