# Kotlin_Crawling
코틀린 크롤링을 활용한 앱 프로젝트 (Kotlin+JSoup).
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
#### 크롤링해 올 태그 분석
![image](https://github.com/chihyunwon/Kotlin_Crawling/assets/58906858/908a2768-5e09-45a7-8421-f5dea28fd59e)
```
table dashboardz 쪽의 tbody 밑의 td class=left의 1번이 조식 2번이 중식 3번이 석식 메뉴를 나타낸다. 
```
#### 23.10.30
![image](https://github.com/chihyunwon/Kotlin_Crawling/assets/58906858/68e1ed16-c068-4d76-9459-c8688deee7b3)
```
메뉴가 나오기는 하는데 뭔지 모르겠다
```
#### UI 수정
![image](https://github.com/chihyunwon/Kotlin_Crawling/assets/58906858/c6490779-849d-49db-843c-c6ecfed7ee83)
```
크롤링 텍스트 공간을 스크롤 뷰로 수정하고 버튼의 크기와 텍스트 크기를 수정했다.
```
#### 로직 수정
![image](https://github.com/chihyunwon/Kotlin_Crawling/assets/58906858/b4eaeb07-0166-4765-9d37-ffb82a168f0b)
```
th.bln 이 오늘 날짜와 같을 때만 조식 중식 석식을 출력하도록 하면 오늘 날짜의 조식 중식 석식만 뽑아올 수 있을 것 같다.
```
#### 오늘 날짜를 뽑아내기
![image](https://github.com/chihyunwon/Kotlin_Crawling/assets/58906858/436f433b-9897-42f3-aa0f-97a2033c7d09)    
![image](https://github.com/chihyunwon/Kotlin_Crawling/assets/58906858/f63b8434-50e4-46dd-ad90-a565e752e244)
```
localdate와 formatter를 사용해서 10.30의 형태로 formattedDate 날짜를 저장한 후 Log를 찍어서 10.30의 형태로 출력되는 것을
확인했다.
