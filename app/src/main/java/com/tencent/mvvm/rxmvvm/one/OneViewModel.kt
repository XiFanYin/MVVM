package com.tencent.mvvm.rxmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qingmei2.architecture.core.base.viewmodel.BaseViewModel
import com.tencent.mvvm.base.Errors
import com.tencent.mvvm.ext.addTo
import com.tencent.mvvm.ext.postNext
import com.tencent.mvvm.util.RxSchedulers

class OneViewModel(private val repo: OneRepository) : BaseViewModel() {
    //创建state对象，扔进去默认的值，防止报错，这里吧从仓库获取到的数据，通过state去驱动ui，即保证了屏幕旋转数据保存，也做到了数据驱动UI
    private val _stateLiveData: MutableLiveData<OneViewState> = MutableLiveData(OneViewState.initial())

    //对ui层暴漏liveData对象
    val stateLiveData: LiveData<OneViewState> = _stateLiveData


    //调用登录,验证数据是否正确
    fun login(name: String, password: String) {
        when (name.isEmpty() || password.isEmpty()) {
            true -> {
                _stateLiveData.postNext { state ->
                    state.copy(
                        showLoading = false,
                        userInfo = null,
                        throwable = Errors.UiError()
                    )
                }
            }
            false -> {
                _stateLiveData.postNext { state ->
                    state.copy(showLoading = true, userInfo = null, throwable = null)
                }
                //去调用Repository里边的登录
                repo.login(name, password)
                    .observeOn(RxSchedulers.ui)
                    .subscribe({
                        _stateLiveData.postNext { state ->
                            state.copy(showLoading = false, userInfo = it, throwable = null)
                        }
                    }, {
                        _stateLiveData.postNext { state ->
                            state.copy(showLoading = false, userInfo = null, throwable = it)
                        }
                    }).addTo(getCompositeDisposable())

            }
        }

    }




}


//创建ViewModel对象工厂类：因为要注入
@Suppress("UNCHECKED_CAST")
class OneViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OneViewModel(OneRepository(OneRemoteDataSource())) as T
    }
}