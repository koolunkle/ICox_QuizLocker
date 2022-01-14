package com.icox.quizlocker

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class QuizLockerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_locker)

//        잠금 화면보다 상단에 위치하기 위한 설정 조정 -> 버전별로 사용법이 다르기 때문에 버전에 따라 적용
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {

//            잠금 화면에서 보여지도록 설정
            setShowWhenLocked(true)

//            잠금 해제
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)

        } else {
//            잠금 화면에서 보여지도록 설정
            window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)

//            기본 잠금화면을 해제
            window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        }

//        화면을 켜진 상태로 유지
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_quiz_locker)
    }
}