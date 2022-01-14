package com.icox.quizlocker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ScreenOffReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when {
//            화면이 꺼질 때 오는 Broadcast 메세지인 경우 토스트 출력
            intent?.action == Intent.ACTION_SCREEN_OFF -> {
                Log.d("ScreenOffReceiver", "퀴즈 잠금: 화면이 꺼졌습니다.")
                Toast.makeText(context, "퀴즈 잠금: 화면이 꺼졌습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}