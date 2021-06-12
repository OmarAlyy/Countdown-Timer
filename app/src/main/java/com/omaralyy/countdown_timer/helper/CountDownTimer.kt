package com.ocs.labbayk.helper


import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

abstract class CountDownTimer(private val startValue: Long, private val timeUnit: TimeUnit) {
    private var disposable: Disposable? = null
    abstract fun onTick(tickValue: Long)
    abstract fun onFinish()
    fun start() {
        Observable.zip(
            Observable.rangeLong(0, startValue),
            Observable.interval(1, timeUnit),
            { integer: Long, aLong: Long? ->
                val l = startValue - integer
                l
            }
        ).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(aLong: Long) {
                    onTick(aLong)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    onFinish()
                }
            })
    }

    fun cancel() {
        if (disposable != null) disposable!!.dispose()
    }
}