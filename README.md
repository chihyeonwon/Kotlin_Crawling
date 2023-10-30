# Kotlin_Crawling
코틀린 크롤링을 활용한 앱 프로젝트 (Kotlin+JSoup)
## 프로젝트 개요
(원주캠 학생식당 식단표)[https://coop.gwnu.ac.kr/contents.asp?page=848]    
![image](https://github.com/chihyunwon/Kotlin_Crawling/assets/58906858/747297b8-adb9-498c-ab90-736e2a1431e5)
```
개발자 : won chi hyeon

식단표 웹사이트에서 필요한 부분의 정보를 크롤링한다음 파이어베이스 스토어에 저장한 후
보여주는 원주캠 전용 식단앱을 개발하고자 한다.
```
#### app 수준 gradle 설정
```kotlin
id ("kotlin-android-extensions")

implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")
implementation ("io.reactivex.rxjava2:rxkotlin:2.4.0")
implementation ("org.jsoup:jsoup:1.13.1")
```
#### AndroidManifest 설정 추가
```
<uses-permission android:name="android.permission.INTERNET" />
<application> android:usesCleartextTraffic="true" 
```
#### 23.10.30 작업
![image](https://github.com/chihyunwon/Kotlin_Crawling/assets/58906858/9c3cb038-a9da-47bf-a4ea-d28a87f05620)
```
https://coop.gwnu.ac.kr/contents.asp?page=848 사이트의 어느 부분을 가져오기는 하는 데
조식, 중식, 석식의 태그를 잘못 찾는 것 같다
```
