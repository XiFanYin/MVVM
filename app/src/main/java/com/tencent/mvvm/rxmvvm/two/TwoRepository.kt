package com.tencent.mvvm.rxmvvm.two

import android.os.SystemClock
import com.qingmei2.architecture.core.base.repository.BaseRepositoryBoth
import com.qingmei2.architecture.core.base.repository.ILocalDataSource
import com.qingmei2.architecture.core.base.repository.IRemoteDataSource
import com.tencent.mvvm.util.RxSchedulers
import io.reactivex.Observable

class TwoRepository(remoteDataSource: TwoRemoteDataSource, localDataSource: TwoLocalDataSource) :
    BaseRepositoryBoth<TwoRemoteDataSource, TwoLocalDataSource>(remoteDataSource, localDataSource) {





    fun  getLocalPage(page:Int):Observable<MutableList<String>>{
        return localDataSource.getLocalData(page)
    }

    fun  getRemoteData(page:Int):Observable<MutableList<String>>{
        return remoteDataSource.getRemoteData(page)
    }




}


class TwoRemoteDataSource : IRemoteDataSource {
    fun  getRemoteData(page:Int):Observable<MutableList<String>>{
        if (page==1){
            return  Observable.create<MutableList<String>> { emtter ->
                SystemClock.sleep(5000)
                emtter.onNext(arrayListOf("网络第一页数据1","网络第一页数据2","网络第一页数据3","网络第一页数据4","网络第一页数据1","网络第一页数据2","网络第一页数据3","网络第一页数据4"))
                emtter.onComplete()
            }.subscribeOn(RxSchedulers.io)
        }else {
            return  Observable.create<MutableList<String>> { emtter ->
                SystemClock.sleep(5000)
                emtter.onNext(arrayListOf("网络第二页数据5","网络第二页数据6","网络第二页数据7","网络第二页数据8","网络第二页数据5","网络第二页数据6","网络第二页数据7","网络第二页数据8"))
                emtter.onComplete()
            }.subscribeOn(RxSchedulers.io)
        }


    }
}

class TwoLocalDataSource : ILocalDataSource {
    fun  getLocalData(page:Int):Observable<MutableList<String>>{
        return  Observable.create<MutableList<String>> { emtter ->
            SystemClock.sleep(1000)
            emtter.onNext(arrayListOf("本地缓存的第一页数据1","本地缓存的第一页数据2","本地缓存的第一页数据3","本地缓存的第一页数据4"))
            emtter.onComplete()
        }.subscribeOn(RxSchedulers.io)
    }

}