# 🧩Instagram image downloader
본 프로그램이 법적으로 문제가 된다면 pain0121@naver.com 로 메일 바랍니다.

# Intro
**인스타그램 사용중에 내가 좋아하는 연예인의 사진이나 내가 업로드했던 사진들을 다운로드 하고싶었던 적이 있으신가요?**

&nbsp;
하나씩 찾아가서 캡처하기엔 너무 귀찮지만 딱히 다른 방법은 없으셨을텐데요. 저 또한 이런 경험이 있어, 본 프로그램을 개발하게 되었습니다.
단순히 입력란에 닉네임과 폴더 경로만 입력하면 해당 닉네임에 대한 인스타그램 이미지를 다운로드합니다.

&nbsp;
주의하실 점은 비공개 계정에 대해서는 이미지 다운로드가 불가능하며 최근 **1,500개**의 게시물까지만 이미지 다운로드가 가능합니다.

&nbsp;
## 📌준비물
- **별도의 Java 설치가 필요하지 않습니다.** MSI 설치 파일에 실행에 필요한 JRE가 포함되어 있습니다.

본 프로그램은 **Kotlin** + **JavaFX 17** 로 구현한 Windows 데스크탑 애플리케이션입니다.
jpackage를 통해 JRE를 번들링한 MSI 설치 파일을 제공하므로 Java를 별도로 설치할 필요가 없습니다.

&nbsp;
## 📌다운로드

GitHub Actions 를 통해 master branch 에 변경사항이 push 될 때마다 MSI 설치 파일이 자동으로 빌드됩니다.

최신 MSI 파일은 아래에서 다운로드할 수 있습니다:

🖇LINK -> [Actions - Build MSI](https://github.com/KAispread/Instagram-image-downloader/actions/workflows/build-msi.yml)

위 링크에서 가장 최근의 성공한 워크플로우를 선택한 뒤, **Artifacts** 섹션에서 `InstagramDownloader-{번호}` 파일을 다운로드합니다.
압축을 풀면 `InstagramDownloader-1.0.0.msi` 설치 파일이 있습니다. 실행하면 설치가 진행됩니다.

&nbsp;
## 💡사용방법

MSI 설치 파일을 실행하여 프로그램을 설치한 후, 시작 메뉴에서 **InstagramDownloader** 를 실행합니다.

<p align="center">
  <img src="https://user-images.githubusercontent.com/99247279/209555919-0fe611bd-b90a-401a-b79e-de179b03f02a.png" width="700" />
</p>

> 입력 공간 설명
- 1 - 닉네임 : 이미지를 다운받을 인스타그램 닉네임
- 2 - 다운받을 폴더 경로 : 이미지를 저장할 폴더 경로
- 3 - 이미지 개수 : 최대 이미지 개수를 설정합니다. **생략시 최대 1,500개의 게시물에 대한 이미지 다운로드를 시도합니다.**

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
- 입력란 생략 시, 최대 1,500개의 게시물에 대한 모든 이미지를 다운로드 시도합니다.
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
### ❗❗ ++ 다운로드 중단기능
<p align="center">
  <img src="https://user-images.githubusercontent.com/99247279/224315368-f7c948ea-b226-436b-81eb-fcfedf9615f1.png" width="600" />
</p>
이미지 다운로드 중에 STOP 버튼을 클릭하면 다운로드를 중단합니다.

&nbsp;
# Tech Stack
- **Kotlin** 2.0.21
- **JavaFX** 17
- **Gradle** 8.5
- **Jsoup** 1.15.3 (HTTP 통신)
- **json-simple** 1.1.1 (JSON 파싱)
- **GitHub Actions** (MSI 자동 빌드)

&nbsp;
# Reference
- https://openjfx.io/javadoc/11/ -> JavaFX
- https://openjfx.io/openjfx-docs/#gradle -> gradle JavaFX
- https://jsoup.org/cookbook/extracting-data/dom-navigation -> Jsoup
- https://docs.oracle.com/javase/7/docs/api/javax/imageio/ImageIO.html -> ImageIO
