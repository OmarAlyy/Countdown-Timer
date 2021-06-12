package com.omaralyy.countdown_timer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ocs.labbayk.helper.CountDownTimer
import com.omaralyy.countdown_timer.helper.DateHelper
import java.util.concurrent.TimeUnit


class MainViewModel(app: Application) : AndroidViewModel(app) {

    val time24: MutableLiveData<String> = MutableLiveData(String()).apply {
        ""
    }
    val isRunning: MutableLiveData<Boolean> = MutableLiveData(false)
    val enableStart: MutableLiveData<Boolean> = MutableLiveData(false)
    val enableEditText: MutableLiveData<Boolean> = MutableLiveData(true)
    val numberValue: MutableLiveData<String> = MutableLiveData(String())
    lateinit var countDownTimer: CountDownTimer


    init {
        numberValue.observeForever {

            if (it.isNotEmpty()) {
                enableStart.postValue(true)
            } else
                enableStart.postValue(false)
        }
    }

    fun startButton() {
        if (isRunning.value == false && !numberValue.value.equals("")) {
            setUpCountDown((numberValue.value?.toLong() ?: 1) * 60)
            enableEditText.postValue(false)
        } else if (countDownTimer != null) {
            countDownTimer.cancel()
            numberValue.postValue("")
            time24.postValue("00:00")
            isRunning.postValue(false)
            enableStart.postValue(false)
            enableEditText.postValue(true)

        }
    }


    private fun setUpCountDown(toInt: Long) {

        isRunning.postValue(true)


        countDownTimer = object : CountDownTimer(toInt, TimeUnit.SECONDS) {
            override fun onTick(tickValue: Long) {
                time24.postValue(DateHelper.timeFormatter(tickValue))
            }

            override fun onFinish() {
                startButton()
                isRunning.postValue(false)
            }
        }

        countDownTimer.start()
    }


}