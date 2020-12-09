package com.tencent.mvvm.rxmvvm

import android.os.SystemClock
import com.qingmei2.architecture.core.base.repository.BaseRepositoryBoth
import com.qingmei2.architecture.core.base.repository.BaseRepositoryRemote
import com.qingmei2.architecture.core.base.repository.ILocalDataSource
import com.qingmei2.architecture.core.base.repository.IRemoteDataSource
import com.tencent.mvvm.util.RxSchedulers
import io.reactivex.Observable

class OneRepository(
    remoteDataSource: OneRemoteDataSource
) : BaseRepositoryRemote<OneRemoteDataSource>(
    remoteDataSource
) {

    fun login(username: String, password: String): Observable<String> {
         return remoteDataSource.netlogin(username, password)
    }
}


class OneRemoteDataSource : IRemoteDataSource {
    //真正的网络请求，这里仅仅是模拟
    fun netlogin(username: String, password: String): Observable<String> {
        return Observable.create<String> { emtter ->
            SystemClock.sleep(2000)
            emtter.onNext("网络返回的用户信息，这里用string代替")
            emtter.onComplete()
        }.subscribeOn(RxSchedulers.io)
    }

}

