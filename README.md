# 🧩Instagram image downloader
본 프로그램이 법적으로 문제가 된다면 pain0121@naver.com 로 메일 바랍니다.

# Intro
**인스타그램 사용중에 내가 좋아하는 연예인의 사진이나 내가 업로드했던 사진들을 다운로드 하고싶었던 적이 있으신가요?**

&nbsp;
하나씩 찾아가서 캡처하기엔 너무 귀찮지만 딱히 다른 방법은 없으셨을텐데요. 저 또한 이런 경험이 있어, 본 프로그램을 개발하게 되었습니다.
단순히 입력란에 닉네임과 폴더 경로만 입력하면 해당 닉네임에 대한 인스타그램 이미지를 다운로드합니다.

&nbsp;
주의하실 점은 비공개 계정에 대해서는 이미지 다운로드가 불가능하며 최근 500개의 게시물까지만 이미지 다운로드가 가능합니다. 
또한, 자바 11버전 이상이 프로그램을 구동할 PC에 설치되어 있어야합니다.

&nbsp;
## 📌준비물
- JAVA 11 (jdk 11) 이상, JAVA_HOME 이나 PATH 환경변수 설정까지 되어있어야 합니다.

본 프로그램은 jdk 11 버전에서 javaFX UI로 구현한 순수 자바 프로그램입니다. 따라서 프로그램 구동을 위해 자바 11 이상(jdk 11) 이 설치되어있어야 합니다.
exe 프로그램이 **%JAVA_HOME%** 또는 **%PATH%** 경로의 jdk 환경에서 구동되기 때문에 다음과 같이 환경변수 설정까지 해주셔야합니다.
<p align="center">
  <img src="https://user-images.githubusercontent.com/99247279/209555711-8540cd0f-dbe7-4604-9694-13018a9346ff.png" width="400" />
</p>

&nbsp;
자바 11 설치 및 환경 변수 설정에 대한 방법은 잘 정리된 포스팅이 있어 아래에 첨부합니다.
https://crazykim2.tistory.com/478

&nbsp;
## 📌다운로드
🖇LINK -> [instagram downloader - 다운로드](https://github.com/KAispread/Instagram-image-downloader/releases/download/v0.0.1/v0.0.1_Instagram_downloader.zip)   

&nbsp;
## 💡사용방법

위 링크에서 프로그램을 다운받은 후, 압축을 풀고 v0.0.1Instagram_downloader.exe 를 실행합니다.

<p align="center">
  <img src="https://user-images.githubusercontent.com/99247279/209555919-0fe611bd-b90a-401a-b79e-de179b03f02a.png" width="700" />
</p>

위 링크에서 프로그램을 다운받은 후, 압축을 풀고 v0.0.1Instagram_downloader.exe 를 실행합니다. 

> 입력 공간 설명
- 1 - 닉네임 : 이미지를 다운받을 인스타그램 닉네임
- 2 - 다운받을 폴더 경로 : 이미지를 저장할 폴더 경로
- 3 - 이미지 개수 : 최대 이미지 개수를 설정합니다. **생략시 최대 500개의 게시물에 대한 이미지 다운로드를 시도합니다.**

&nbsp;
### 1. 닉네임
<p align="center">
  <img src="https://user-images.githubusercontent.com/99247279/209556722-dbe00a73-d537-42dd-8dcc-2cc9705a4b1b.png" width="800" />
</p>

- 이미지를 다운받을 인스타그램 유저 닉네임을 입력하는 공간입니다.
- 위 사진처럼 다운받을 인스타그램 닉네임을 복사하고 닉네임 입력란에 붙여넣기 합니다.

&nbsp;
### 2. 다운받을 폴더 경로
<p align="center">
  <img src="https://user-images.githubusercontent.com/99247279/209556990-8aecadf3-0c21-4d0c-84ca-fa89cf233b41.png" width="800" />
</p>

- 이미지가 저장될 폴더 경로를 지정합니다.
- 위 사진과 같이 폴더 경로를 클릭한 후 복사 - 붙여넣기 합니다.


&nbsp;
### 3. 이미지 개수
<p align="center">
  <img src="https://user-images.githubusercontent.com/99247279/209557253-e462d25d-3f40-4906-8f4b-d160b7ee8a71.png" width="500" />
</p>

- 최대 이미지 개수를 지정합니다.
- 입력란 생략 시, 최대 500개의 게시물에 대한 모든 이미지를 다운로드 시도합니다.
- (예제의 경우, 가장 최근부터 100장까지의 이미지를 다운로드합니다)


&nbsp;
### 🎯 다운로드
선택 입력란인 이미지 개수를 제외한 모든 입력란에 대해 입력했다면 파란색 **DOWNLOAD** 버튼을 클릭합니다.
<p align="center">
  <img src="https://user-images.githubusercontent.com/99247279/209557817-7b72d219-a05f-409a-84d9-9571b523b621.png" width="500" />
</p>

&nbsp;
![image](https://user-images.githubusercontent.com/99247279/209557621-b3916091-4a33-4058-ad6c-f9c1c95f2def.png)
- 잠시 기다리면 다음과 같이 안내문구와 함께 이미지 다운로드가 완료됩니다.


&nbsp;
# Reference
- https://openjfx.io/javadoc/11/ -> JavaFX
- https://openjfx.io/openjfx-docs/#gradle -> gradle JavaFX
- https://jsoup.org/cookbook/extracting-data/dom-navigation -> Jsoup
- https://docs.oracle.com/javase/7/docs/api/javax/imageio/ImageIO.html -> ImageIO
- https://www.selenium.dev/ -> Selenium
