package com.icox.quizlocker

import android.os.Bundle
import android.preference.PreferenceFragment
import androidx.appcompat.app.AppCompatActivity

class PrefFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pref_fragment)

//        Activity 의 ContentView 를 MyPrefFragment 로 교체한다
        fragmentManager.beginTransaction().replace(android.R.id.content, MyPrefFragment()).commit()
    }

    // PreferenceFragment: XML 로 작성한 Preference 를 UI 로 보여주는 클래스
    class MyPrefFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
//        Preference 정보가 있는 XML 파일 지정
            addPreferencesFromResource(R.xml.ex_pref)
        }
    }
}