package com.icox.quizlocker

import android.os.Bundle
import android.preference.MultiSelectListPreference
import android.preference.PreferenceFragment
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val fragment = MyPreferenceFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        preferenceContent FrameLayout 영역을 PreferenceFragment 로 교체
        fragmentManager.beginTransaction().replace(R.id.preferenceContent, fragment).commit()
    }

    class MyPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

//            환경설정 Resource 파일 적용
            addPreferencesFromResource(R.xml.pref)

//            퀴즈 종류 요약 정보에, 현재 선택된 항목을 보여주는 코드
            val categoryPref = findPreference("category") as MultiSelectListPreference
            categoryPref.summary = categoryPref.values.joinToString(", ")

//            환경설정 정보 값이 변경될 때에도 요약 정보를 변경하도록 Listener 등록
            categoryPref.setOnPreferenceChangeListener { preference, newValue ->

//                newValue 파라미터가 HashSet 으로 캐스팅이 실패하면 리턴
                val newValueSet = newValue as? HashSet<*>
                    ?: return@setOnPreferenceChangeListener true

//                선택된 퀴즈 종류로 요약 정보 보여줌
                categoryPref.summary = newValue.joinToString(", ")
                true

            }
        }
    }

}