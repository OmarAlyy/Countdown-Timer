package com.omaralyy.countdown_timer.helper


class DateHelper {


    companion object {

        fun timeFormatter(secs: Long): String {

            return String.format(
                "%02d:%02d:%02d:%02d",
                secs / 86400,
                secs / 3600,
                (secs % 3600) / 60,
                secs % 60
            )
        }
    }


}