# DEVoca-chrome-extension

DEVoca 크롬 확장 프로그램입니다.

## 설치 방법

### 필요 사항

- Google Chrome 브라우저 설치

### 설치 및 실행

1. 소스 코드 [다운로드](https://lab.ssafy.com/s10-webmobile2-sub2/S10P12D112/-/archive/develop/S10P12D112-develop.zip?path=DEVoca-chrome-extension) & 압축 해제

2. 크롬 브라우저에서 [확장 프로그램 관리](chrome://extensions) 페이지 접속

    ![DEVoca Chrome Extension](../docs/resources/DEVoca%20Chrome%20Extension%202.png)

    - 우측 상단 '개발자 모드' 토글 켜기

3. 압축 해제한 확장 프로그램 로드

    ![DEVoca Chrome Extension](../docs/resources/DEVoca%20Chrome%20Extension%203.gif)

4. 단어 검색

    ![DEVoca Chrome Extension](../docs/resources/DEVoca%20Chrome%20Extension%204.gif)

> ! Note !  
> 현재 확장 프로그램은 배포 상태가 아니라 개발자 모드에서 실행되기 때문에 확장 프로그램 아이디를 서버에 수동으로 등록해줘야 합니다.

## 디렉토리 구조

```bash
.
├── README.md
├── assets
├── images
├── index.html     # 화면 소스코드
├── manifest.json  # 설정
└── scripts
    └── content.js # Web context에서 실행할 JS 스크립트
```

## Author

- [신주용](https://github.com/cheesecat47)
