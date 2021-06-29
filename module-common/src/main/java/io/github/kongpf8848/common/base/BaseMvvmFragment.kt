package io.github.kongpf8848.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class BaseMvvmFragment<VM : BaseViewModel, VDB : ViewDataBinding> : BaseFragment(){

    lateinit var viewModel: VM
    lateinit var binding: VDB
    protected var rootView: View? = null

    protected abstract fun getLayoutId(): Int

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            if(getLayoutId()>0) {
                binding = DataBindingUtil.inflate(inflater, getLayoutId(), null, false)
                rootView = binding.root
                binding.lifecycleOwner = this
            }
            createViewModel()
        } else {
            val parent = rootView!!.parent as? ViewGroup
            parent?.removeView(rootView)
        }
        return rootView
    }

    
    private fun createViewModel() {
        val type = findType(javaClass.genericSuperclass)
        val modelClass = if (type is ParameterizedType) {
            type.actualTypeArguments[0] as Class<VM>
        } else {
            BaseViewModel::class.java as Class<VM>
        }
        viewModel = ViewModelProvider(this).get(modelClass)
    }

    private fun findType(type: Type): Type?{
        return when(type){
            is ParameterizedType -> type
            is Class<*> ->{
                findType(type.genericSuperclass)
            }
            else ->{
                null
            }
        }
    }


}