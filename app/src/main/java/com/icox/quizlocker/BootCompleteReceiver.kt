package com.icox.quizlocker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.preference.PreferenceManager
import android.util.Log

// BroadcastReceiver 를 상속받는다
class BootCompleteReceiver : BroadcastReceiver() {

    // Broadcast 메세지 수신 시 불리는 콜백 함수
    override fun onReceive(context: Context?, intent: Intent?) {
//        부팅이 완료될 때의 메세지인지 확인
        when {
            intent?.action == Intent.ACTION_BOOT_COMPLETED -> {
                Log.d("QuizLocker", "부팅이 완료됨")
//                퀴즈 잠금 화면 설정 값이 ON 인지 확인
                context?.let {
                    val pref = PreferenceManager.getDefaultSharedPreferences(context)
                    val useLockScreen = pref.getBoolean("useLockScreen", false)
//                    LockScreenService 시작
                    if (useLockScreen) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            it.startForegroundService(
                                Intent(
                                    context,
                                    LockScreenService::class.java
                                )
                            )
                        } else {
                            it.startService(
                                Intent(
                                    context,
                                    LockScreenService::class.java
                                )
                            )
                        }
                    }
                }
            }
        }
    }

}