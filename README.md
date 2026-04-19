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
- **별도의 Java 설치가 필요하지 않습니다.** 설치 파일에 실행에 필요한 JRE가 포함되어 있습니다.

본 프로그램은 **Kotlin** + **Compose Multiplatform** 으로 구현한 데스크탑 애플리케이션으로, **Windows**와 **macOS** 를 모두 지원합니다.

&nbsp;
## 📌다운로드

GitHub Actions 를 통해 master branch 에 변경사항이 push 될 때마다 Windows용 MSI 파일과 macOS용 DMG 파일이 자동으로 빌드됩니다.

최신 설치 파일은 아래 링크에서 다운로드할 수 있습니다:

🖇LINK -> [Actions - Build Installers](https://github.com/KAispread/Instagram-image-downloader/actions/workflows/build-installers.yml)

위 링크에서 가장 최근의 성공한 워크플로우를 선택한 뒤, **Artifacts** 섹션에서 운영체제에 맞는 파일을 다운로드합니다.

&nbsp;
### 🪟 Windows (.msi)

1. Artifacts 섹션에서 `InstagramDownloader-msi-{번호}` 를 다운로드합니다.
2. 압축을 풀면 `InstagramDownloader-1.0.0.msi` 설치 파일이 있습니다.
3. 파일을 실행하면 설치가 진행됩니다.
4. 설치 완료 후 시작 메뉴에서 **InstagramDownloader** 를 실행합니다.

&nbsp;
### 🍎 macOS (.dmg)

1. Artifacts 섹션에서 `InstagramDownloader-dmg-{번호}` 를 다운로드합니다.
2. 압축을 풀면 `InstagramDownloader-1.0.0.dmg` 파일이 있습니다.
3. DMG 파일을 더블클릭하여 마운트한 뒤, **InstagramDownloader** 아이콘을 Applications 폴더로 드래그합니다.
4. Launchpad 또는 Applications 폴더에서 **InstagramDownloader** 를 실행합니다.

> **macOS 보안 경고 대응**
>
> 처음 실행 시 "Apple이 확인할 수 없습니다" 경고가 표시될 수 있습니다.
> 아래 방법으로 실행할 수 있습니다.
>
> **방법 1.** 앱 아이콘을 **우클릭(Control+클릭)** → **열기** → **열기** 버튼 클릭
>
> **방법 2.** 시스템 설정 → 개인정보 보호 및 보안 → 보안 섹션 → **"확인 없이 열기"** 클릭

&nbsp;
## 💡사용방법

설치 후 프로그램을 실행하면 아래와 같은 화면이 나타납니다.

> 입력 공간 설명
- **Nickname** : 이미지를 다운받을 인스타그램 닉네임
- **File Path** : 이미지를 저장할 폴더 경로
  - Windows 예시: `C:\Users\사용자명\Pictures\instagram`
  - macOS 예시: `/Users/사용자명/Pictures/instagram`
- **Image Count** (선택): 최대 다운로드 이미지 개수. **생략 시 최대 1,500개의 게시물에 대한 이미지 다운로드를 시도합니다.**

&nbsp;
### 1. 닉네임
<p align="center">
  <img src="https://user-images.githubusercontent.com/99247279/209556722-dbe00a73-d537-42dd-8dcc-2cc9705a4b1b.png" width="800" />
</p>

- 이미지를 다운받을 인스타그램 유저 닉네임을 입력하는 공간입니다.
- 위 사진처럼 다운받을 인스타그램 닉네임을 복사하고 닉네임 입력란에 붙여넣기 합니다.

&nbsp;
### 2. 다운받을 폴더 경로
- 이미지가 저장될 폴더 경로를 입력합니다.
- Windows: 탐색기에서 폴더 경로를 복사하여 붙여넣기 합니다. (예: `C:\Users\사용자명\Downloads\instagram`)
- macOS: Finder에서 폴더를 선택 후 `Option+Command+C` 로 경로를 복사하여 붙여넣기 합니다. (예: `/Users/사용자명/Downloads/instagram`)

&nbsp;
### 3. 이미지 개수
- 최대 이미지 개수를 지정합니다.
- 입력란 생략 시, 최대 1,500개의 게시물에 대한 모든 이미지를 다운로드 시도합니다.
- (예제의 경우, 가장 최근부터 100장까지의 이미지를 다운로드합니다)

&nbsp;
### 🎯 다운로드
닉네임과 폴더 경로를 입력한 뒤 **Download** 버튼을 클릭합니다.

로그 영역에 페이지별 파싱 진행 상황이 표시되며, 다운로드가 완료되면 완료 메시지가 출력됩니다.

&nbsp;
### ❗❗ ++ 다운로드 중단기능
다운로드 중 **Stop** 버튼을 클릭하면 즉시 다운로드를 중단합니다.

&nbsp;
# Tech Stack
- **Kotlin** 2.0.21
- **Compose Multiplatform** 1.6.11
- **Gradle** 8.5
- **Jsoup** 1.15.3 (HTTP 통신)
- **json-simple** 1.1.1 (JSON 파싱)
- **Kotlin Coroutines** 1.8.1 (비동기 처리)
- **GitHub Actions** (Windows MSI / macOS DMG 자동 빌드)

&nbsp;
# Reference
- https://www.jetbrains.com/lp/compose-multiplatform/ -> Compose Multiplatform
- https://jsoup.org/cookbook/extracting-data/dom-navigation -> Jsoup
- https://docs.oracle.com/javase/7/docs/api/javax/imageio/ImageIO.html -> ImageIO
