package com.example.administrator.glidetest.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.administrator.glidetest.base.BaseRepository

/**
 * Created by moge on 2018/7/23.
 */
open class BaseViewModel(var repository: BaseRepository):ViewModel(){


    override fun onCleared() {
        super.onCleared()
        this.repository.destory()
    }

}