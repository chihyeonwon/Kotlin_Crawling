package com.example.wonjumenu

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.wonjumenu.R
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.w3c.dom.Element
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    val today = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("MM.dd")
    @RequiresApi(Build.VERSION_CODES.O)
    val formattedDate = today.format(formatter)

    val TAG = MainActivity::class.java.simpleName
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, formattedDate.toString())

        // 크롤링 시작
        findViewById<Button>(R.id.btnStart).setOnClickListener {
            doTask("https://coop.gwnu.ac.kr/contents.asp?page=848")
        }

    }

    // 크롤링 하기
    fun doTask(url : String) {
        var documentTitle : String = ""
        var itemList : ArrayList<MenuItem> = arrayListOf() //MenuItem 배열

        Single.fromCallable {
            try {
                // 사이트에 접속해서 HTML 문서 가져오기
                val doc = Jsoup.connect(url).get()

                // HTML 파싱해서 데이터 추출하기
                // div.table-responsive 태그만 가져오기
                    val elements : Elements = doc.select("table.table.table-dashboardz")
                // (여러개의) elements 처리
                run elemLoop@{
                    elements.forEachIndexed{ index, elem ->
                        // elem은 하나의 td.left를 전달해줌
                        var breakfast = elem.select("th.bln").text()
                        var lunch = elem.select("td").text()
                        var dinner = elem.select("td.left").text()

                        // MenuItem 아이템 생성 후 추가
                        var item = MenuItem(breakfast, lunch, dinner)
                        itemList.add(item)

                        // 1개만 가져오기
                        if (index == 1) return@elemLoop
                    }
                }

                // 올바르게 HTMl 문서 가져왔다면 title로 바꾸기
                documentTitle = doc.title()
            } catch (e : Exception) {e.printStackTrace()}

            return@fromCallable documentTitle   // subscribe 호출
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                // documentTitle 응답 성공 시
                { text ->
                    // TextView에 출력하기
                    showData(text.toString())

                    // itemList 출력하기
                    showData(itemList.joinToString())
                },
                // documentTitle 응답 오류 시
                { it.printStackTrace() })
    }

    // TextView에 출력하기
    fun showData(msg : String) {
        findViewById<TextView>(R.id.output).append(msg + "\n")
    }
}