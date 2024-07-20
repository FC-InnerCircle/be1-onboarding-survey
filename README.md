# 이너써클 BE 온보딩 프로젝트

## Introduction

- 진행 기간: 2024.07.20.(토) ~ 2024.07.27.(토) / 일찍 제출하실수록 온라인으로 리뷰드릴 수 있는 시간이 길어집니다.
- “설문조사 서비스"를 구현하려고 합니다.
- “온보딩 프로젝트 기능 요구사항"을 구현해 주시기 바랍니다.
- 온보딩 프로젝트 기능 요구 사항 및 기술 요구사항이 충족되지 않은 결과물은 코드레벨 평가를 진행하지 않습니다.
- 아래의 “코드레벨 평가항목"으로 코드를 평가합니다.
- “설문조사 서비스"의 API 명세를 함께 제출해주세요.
- 우대사항은 직접 구현하지 않더라도 README에 적용 방법 등을 구체적으로 명시해주시는 것으로 대체 할 수 있습니다.

## 온보딩 프로젝트 기능 요구사항

### 개요

- “설문조사 서비스”는 설문조사 양식을 만들고, 만들어진 양식을 기반으로 응답을 받을 수 있는 서비스입니다. (e.g. Google Forms, Tally, Typeform)
- 설문조사 양식은 [설문조사 이름], [설문조사 설명], [설문 받을 항목]의 구성으로 이루어져있습니다.
- [설문 받을 항목]은 [항목 이름], [항목 설명], [항목 입력 형태], [항목 필수 여부]의 구성으로 이루어져있습니다.
- [항목 입력 형태]는 [단답형], [장문형], [단일 선택 리스트], [다중 선택 리스트]의 구성으로 이루어져있습니다.


### 1. 설문조사 생성 API

- 요청 값에는 [설문조사 이름], [설문조사 설명], [설문 받을 항목]이 포함됩니다.
- [설문 받을 항목]은 [항목 이름], [항목 설명], [항목 입력 형태], [항목 필수 여부]의 구성으로 이루어져있습니다.
- [항목 입력 형태]는 [단답형], [장문형], [단일 선택 리스트], [다중 선택 리스트]의 구성으로 이루어져있습니다.
    - [단일 선택 리스트], [다중 선택 리스트]의 경우 선택 할 수 있는 후보를 요청 값에 포함하여야 합니다.


### 2. 설문조사 수정 API

- 요청 값에는 [설문조사 이름], [설문조사 설명], [설문 받을 항목]이 포함됩니다.
- [설문 받을 항목]은 [항목 이름], [항목 설명], [항목 입력 형태], [항목 필수 여부]의 구성으로 이루어져있습니다.
- [항목 입력 형태]는 [단답형], [장문형], [단일 선택 리스트], [다중 선택 리스트]의 구성으로 이루어져있습니다.
    - [단일 선택 리스트], [다중 선택 리스트]의 경우 선택 할 수 있는 후보를 요청 값에 포함하여야 합니다.
- [설문 받을 항목]이 추가/변경/삭제 되더라도 기존 응답은 유지되어야 합니다.


### 3. 설문조사 응답 제출 API

- 요청 값에는 [설문 받을 항목]에 대응되는 응답 값이 포함됩니다.
- 응답 값은 설문조사의 [설문 받을 항목]과 일치해야만 응답 할 수 있습니다.


### 4. 설문조사 응답 조회 API

- 요청 값에는 [설문조사 식별자]가 포함됩니다.
- 해당 설문조사의 전체 응답을 조회합니다.
- **(Advanced)** 설문 응답 항목의 이름과 응답 값을 기반으로 검색 할 수 있습니다.

<br/>

> 💡 주어진 요구사항 이외의 추가 기능 구현에 대한 제약은 없으며, 새롭게 구현한 기능이 있을 경우 README 파일에 기재 해주세요.

<br/>

## 기술 요구 사항

- JAVA 11 이상 또는 Kotlin 사용
- Spring Boot 사용
- Gradle 기반의 프로젝트
- 온보딩 프로젝트 기능 요구사항은 서버(백엔드)에서 구현/처리
- 구현을 보여줄 수 있는 화면(프론트엔드)은 구현 금지
- DB는 인메모리 DB(예: h2)를 사용하며 DB 컨트롤은 JPA로 구현
- API의 HTTP Method는 자유롭게 선택해주세요.
- 에러 응답, 에러 코드는 자유롭게 정의해주세요.
- 외부 라이브러리 및 오픈소스 사용 가능 (단, README 파일에 사용한 오픈 소스와 사용 목적을 명확히 명시해 주세요.)

## 코드레벨 평가 항목

온보딩 프로젝트는 다음 내용을 고려하여 평가 하게 됩니다.

- 프로젝트 구성 방법 및 관련된 시스템 아키텍처 설계 방법이 적절한가?
- 작성한 애플리케이션 코드의 가독성이 좋고 의도가 명확한가?
    - e.g. 불필요한(사용되지) 않는 코드의 존재 여부, 일정한 코드 컨벤션 등
- 작성한 테스트 코드는 적절한 범위의 테스트를 수행하고 있는가?
    - e.g. 유닛/통합 테스트 등
- Spring Boot의 기능을 적절히 사용하고 있는가?
- 예외 처리(Exception Handling)은 적절히 수행하고 있는가?

## 우대사항

- 프로젝트 구성 추가 요건: 멀티 모듈 구성 및 모듈간 의존성 제약
- Back-end 추가 요건
    - 트래픽이 많고, 저장되어 있는 데이터가 많음을 염두에 둔 구현
    - 다수의 서버, 인스턴스에서 동작할 수 있음을 염두에 둔 구현
    - 동시성 이슈가 발생할 수 있는 부분을 염두에 둔 구현
 
## 온보딩 프로젝트 제출 방식

### 소스코드

- 본 Repository에 main 브랜치를 포크하여 작업을 시작합니다.
- SpringBoot 프로젝트를 신규로 설정하고, 개인별로 main 브랜치에 PR을 공개적으로 먼저 작성한 후에 작업을 시작합니다. 이때 PR은 Draft 상태로 설정해주세요.
- 최소 기능 단위로 완성할 때 마다 커밋합니다.
- 모든 작업이 완료되면 `Ready for review` 버튼을 이용하여 PR을 Draft 상태에서 Review 가능 상태로 변경해주세요.

### 기능 점검을 위한 빌드 결과물

빌드 결과물을 Executable jar 형태로 만들어 위 Branch에 함께 업로드 하시고, README에 다운로드 링크 정보를 넣어주시기 바랍니다. GitHub의 용량 문제로 업로드가 안되는 경우 다른 곳(개인 구글 드라이브 등)에 업로드 한 후 해당 다운로드 링크 정보를 README에 넣어주셔도 됩니다.

해당 파일을 다운로드 및 실행(e.g. java -jar project.jar)하여 요구 사항 기능 검증을 진행하게 됩니다. 해당 파일을 다운로드할 수 없거나 실행 시 에러가 발생하는 경우에는 기능 점검을 진행하지 않습니다. 온보딩 프로젝트 제출 전 해당 실행 파일 다운로드 및 정상 동작 여부를 체크해 주시기 바랍니다.

