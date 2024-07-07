# MOOSA
MOOSA 사이트는 웹사이트 하나에서 제주도의 모든 여행거리, 먹거리, 숙소 에 대한 정보를 모두 제공하는 사이트 입니다. 

# Intro

국비지원 자바 개발자 풀스텍 과정에서 해당 프로젝트 팀장을 맡으며 개발에 대한 역량과 리더쉽을 길렀고,

해당하는 프로젝트는 MVC패턴을 사용하여 MVC패턴을 이해하고 코딩하는데 도움이 많이 되었습니다. 

지금 보고계시는 이 프로젝트는 세미 프로젝트로 2주 동안 진행한 프로젝트 중 제가 코딩한 부분만 정리하여 보여드립니다.

# Update
- 2024.07.06 (xml로만 작성되었던 sql문을 mybatis를 적용시켜 수정해보았다.)
  
# Project
- 제작기간
2024.05.01 ~ 2024.05.16

- Environment

  운영체제	: Window, OS

  사용언어	: Front - JavaScript, jQuery, HTML, CSS, Ajax

  : Back - Java / View - Template - strongly-typed

  FrameWork/Library	: ojdbc, cos, jstl 

  DB	: Oracle / SQL Developer

  Tool	: Eclipse, Visual Studio Code  

  Collaboration	: GitHub

### View  
<img width="1710" alt="스크린샷 2024-07-06 오후 2 13 40" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/34c0c41b-23a3-4445-a72b-bba261bb2a80">

사이트 상단 로그인, 관광명소, 숙소, 코스추천, 소통게시판 메뉴가 있습니다.

### 숙소메뉴 클릭시  
<img width="1707" alt="스크린샷 2024-07-06 오후 1 46 15" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/a700410d-40fd-4d66-b354-d8c9512dfa76">

##### DB에 저장된 카테고리별 숙소 조회(Ajax 사용하여 비동기로 카테고리별 조회)
마우스 호버시 해당하는 숙소 이름, 위치 확인 가능 
(로그인시 위시리스트 등록, 해제 버튼 활성화 비로그인시 위시리스트 버튼 비활성화)


### 숙소 클릭시
<img width="1710" alt="스크린샷 2024-07-06 오후 1 47 09" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/7f061765-03ee-44bf-9d1c-fa987f2a6af0">
<img width="1709" alt="스크린샷 2024-07-06 오후 1 47 22" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/63e7cbe8-5cef-4949-829b-60ea572526f7">


##### 해당하는 숙소 상세페이지 이동 
해당하는 숙소 위치 KaKao지도 Api로 지도 보여주기, 숙소 등록 사용자가 입력한 숙소 설명 보여주기, 숙소 방 정보 조회, 고객리뷰 작성, 조회 

### 상단 회원 정보탭 클릭시 
<img width="1706" alt="스크린샷 2024-07-06 오후 2 39 51" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/b1d065f3-eea7-4765-a0b5-2714cb737ecd">

##### 해당 로그인된 회원페이지 이동 

### 개인 정보 수정 탭 클릭시
<img width="1705" alt="스크린샷 2024-07-06 오후 1 47 47" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/6dc55b4d-9fcb-4456-966c-2daeffe02260">

##### 회원 정보 수정 전 비밀번호 확인 

### 개인정보 수정 탭 화면 
<img width="1710" alt="스크린샷 2024-07-06 오후 1 48 12" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/42d2db9e-5366-4771-9c83-c6abbe4d16a1">

##### 회원 정보 입력후 수정하기 클릭시 수정, 

### 비밀번호 변경하기 클릭시 
<img width="1710" alt="스크린샷 2024-07-06 오후 1 49 12" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/aff8459f-9a7f-4f85-ae40-4ff52e99de55">
<img width="1710" alt="스크린샷 2024-07-06 오후 1 49 37" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/5ecf9ddd-bcef-4c39-8ec8-60a3ee166c31">

##### 현재비밀번호 확인후 변경할비밀번호, 변경할 비밀번호 확인후 변경 가능 (변경할 비밀번호와 변경할 비밀번호 불일치시 변경 불가)

### 탈퇴 버튼 클릭시
<img width="1708" alt="스크린샷 2024-07-06 오후 1 49 59" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/7285c4fc-8a10-4553-830f-b01d94d5bcda">
<img width="1710" alt="스크린샷 2024-07-06 오후 1 50 06" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/9e3c4bd7-2e20-4f78-9b6f-4de37b7cb2be">

##### 비밀번호 확인후 버튼클릭 회원탈퇴 의사 한번 더 묻고 확인 클릭시 탈퇴

### 위시리스트 탭 클릭시
<img width="1710" alt="스크린샷 2024-07-06 오후 1 50 20" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/849a37d3-2e2a-4d99-af42-a60e8115c547">

##### 등록되었던 위시리스트 조회가능, 여러 위시리스트 선택하여 삭제 가능 

### 나의 리뷰 탭 클릭시
<img width="1710" alt="스크린샷 2024-07-06 오후 1 50 34" src="https://github.com/RyuDonong/MOOSAprivate/assets/168408760/605ba05a-e00f-458b-8380-1d2778f398ab">

##### 작성된 리뷰 조회 , 수정, 삭제 가능



