package com.tencent.mvvm.rxmvvm.two

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qingmei2.architecture.core.base.viewmodel.BaseViewModel
import com.tencent.mvvm.PAGING_SIZE
import com.tencent.mvvm.ext.addTo
import com.tencent.mvvm.ext.postNext
import com.tencent.mvvm.util.RxSchedulers

class TwoViewModel(val repo: TwoRepository) : BaseViewModel() {

    private val _stateLiveData: MutableLiveData<TwoViewState> =
        MutableLiveData(TwoViewState.initial())

    //对ui层暴漏liveData对象
    val stateLiveData: LiveData<TwoViewState> = _stateLiveData


    //请求的页码数默认是1
    var page = 1
        get() {
            return field
        }
        set(value) {
            if (value >= 1) {
                field = value
                getRemote(value)
            }
        }


    init {
        getLocal()
        getRemote(page)
    }


    private fun getLocal() {
        repo.getLocalPage(page)
            .observeOn(RxSchedulers.ui)
            .subscribe({
                //这里表示如果没有缓存，不改变数据，也就不会改变ui
                if (it.size != 0) {
                    _stateLiveData.postNext { state -> state.copy(allData = it) }
                }
            }, {

            }).addTo(getCompositeDisposable())
    }


    private fun getRemote(page: Int) {
        repo.getRemoteData(page)
            .observeOn(RxSchedulers.ui)
            .subscribe({
                _stateLiveData.postNext { state ->
                    if (page == 1) {
                        state.copy(allData = it, startIndex = 0)
                    } else {
                        val oldAllData = state.allData!!
                        val startIndex = oldAllData.size
                        oldAllData.addAll(it)
                        state.copy(startIndex = startIndex)
                    }
                }
            }, {
                _stateLiveData.postNext { state ->
                    state.copy(throwable = it)
                }
            }).addTo(getCompositeDisposable())
    }


}


@Suppress("UNCHECKED_CAST")
class TwoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TwoViewModel(TwoRepository(TwoRemoteDataSource(), TwoLocalDataSource())) as T
    }


}




