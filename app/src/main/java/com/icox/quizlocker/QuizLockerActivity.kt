package com.icox.quizlocker

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz_locker.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class QuizLockerActivity : AppCompatActivity() {

    var quiz: JSONObject? = null

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

//        퀴즈 데이터를 가져온다
        val json = assets.open("capital.json").reader().readText()
        val quizArray = JSONArray(json)

//        퀴즈를 선택한다
        quiz = quizArray.getJSONObject(Random().nextInt(quizArray.length()))

//        퀴즈를 보여준다
        quizLabel.text = quiz?.getString("question")
        choice1.text = quiz?.getString("choice1")
        choice2.text = quiz?.getString("choice2")
    }

}