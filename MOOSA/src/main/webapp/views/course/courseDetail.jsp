<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String alertMsg = (String) session.getAttribute("alertMsg");
Member loginUser = (Member) session.getAttribute("loginUser");
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>

@import url("https://hangeul.pstatic.net/hangeul_static/css/NanumMiNiSonGeurSsi.css");
@media ( min-width : 1000px) {
	#timeline .demo-card:nth-child(even) .head::after, #timeline .demo-card:nth-child(odd) .head::after
		{
		position: absolute;
		content: "";
		width: 0;
		height: 0;
		border-top: 15px solid transparent;
		border-bottom: 15px solid transparent;
	}
	#timeline .demo-card:nth-child(even) .head::before, #timeline .demo-card:nth-child(odd) .head::before
		{
		position: absolute;
		content: "";
		width: 9px;
		height: 9px;
		background-color: #bdbdbd;
		border-radius: 9px;
		box-shadow: 0px 0px 2px 8px #f7f7f7;
	}
}
/* Some Cool Stuff */
.demo-card:nth-child(1) {
	order: 1;
}

.demo-card:nth-child(2) {
	order: 4;
}

.demo-card:nth-child(3) {
	order: 2;
}

.demo-card:nth-child(4) {
	order: 5;
}

.demo-card:nth-child(5) {
	order: 3;
}

.demo-card:nth-child(6) {
	order: 6;
}

/* Border Box */
* {
	box-sizing: border-box;
}

/* Fonts */
body {
	font-family: Roboto;
}

#timeline {
	padding: 100px 0;
	background: #f7f7f7;
	border-top: 1px solid rgba(191, 191, 191, 0.4);
	border-bottom: 1px solid rgba(191, 191, 191, 0.4);
}

#timeline h1 {
	text-align: center;
	font-size: 3rem;
	font-weight: 200;
	margin-bottom: 20px;
}

#timeline p.leader {
	text-align: center;
	max-width: 90%;
	margin: auto;
	margin-bottom: 45px;
}

#timeline .demo-card-wrapper {
	position: relative;
	margin: auto;
}

@media ( min-width : 1000px) {
	#timeline .demo-card-wrapper {
		display: flex;
		flex-flow: column wrap;
		width: 1170px;
		height: 1650px;
		margin: 0 auto;
	}
}

#timeline .demo-card-wrapper::after {
	z-index: 1;
	content: "";
	position: absolute;
	top: 0;
	bottom: 0;
	left: 50%;
	border-left: 1px solid rgba(191, 191, 191, 0.4);
}

@media ( min-width : 1000px) {
	#timeline .demo-card-wrapper::after {
		border-left: 1px solid #bdbdbd;
	}
}

#timeline .demo-card {
	position: relative;
	display: block;
	margin: 10px auto 80px;
	max-width: 94%;
	z-index: 2;
}

@media ( min-width : 480px) {
	#timeline .demo-card {
		max-width: 60%;
		box-shadow: 0px 1px 22px 4px rgba(0, 0, 0, 0.07);
	}
}

@media ( min-width : 720px) {
	#timeline .demo-card {
		max-width: 40%;
	}
}

@media ( min-width : 1000px) {
	#timeline .demo-card {
		max-width: 450px;
		height: 400px;
		margin: 90px;
		margin-top: 45px;
		margin-bottom: 45px;
	}
	#timeline .demo-card:nth-child(odd) {
		margin-right: 45px;
	}
	#timeline .demo-card:nth-child(odd) .head::after {
		border-left-width: 15px;
		border-left-style: solid;
		left: 100%;
	}
	#timeline .demo-card:nth-child(odd) .head::before {
		left: 491.5px;
	}
	#timeline .demo-card:nth-child(even) {
		margin-left: 45px;
	}
	#timeline .demo-card:nth-child(even) .head::after {
		border-right-width: 15px;
		border-right-style: solid;
		right: 100%;
	}
	#timeline .demo-card:nth-child(even) .head::before {
		right: 489.5px;
	}
	#timeline .demo-card:nth-child(2) {
		margin-top: 180px;
	}
}

#timeline .demo-card .head {
	position: relative;
	display: flex;
	align-items: center;
	color: #fff;
	font-weight: 400;
}

#timeline .demo-card .head .number-box {
	display: inline;
	float: left;
	margin: 15px;
	padding: 10px;
	font-size: 35px;
	line-height: 35px;
	font-weight: 600;
	background: rgba(0, 0, 0, 0.17);
}

#timeline .demo-card .head h2 {
	text-transform: uppercase;
	font-size: 1.3rem;
	font-weight: inherit;
	letter-spacing: 2px;
	margin: 0;
	padding-bottom: 6px;
	line-height: 1rem;
}

@media ( min-width : 480px) {
	#timeline .demo-card .head h2 {
		font-size: 165%;
		line-height: 1.2rem;
	}
}

#timeline .demo-card .head h2 span {
	display: block;
	font-size: 0.6rem;
	margin: 0;
}

@media ( min-width : 480px) {
	#timeline .demo-card .head h2 span {
		font-size: 0.8rem;
	}
}

#timeline .demo-card .body {
	background: #fff;
	border: 1px solid rgba(191, 191, 191, 0.4);
	border-top: 0;
	padding: 15px;
}

@media ( min-width : 1000px) {
	#timeline .demo-card .body {
		height: 315px;
	}
}

#timeline .demo-card .body p {
	font-size: 14px;
	line-height: 18px;
	margin-bottom: 15px;
}

#timeline .demo-card .body img {
	display: block;
	width: 100%;
}

#timeline .demo-card--step1 {
	background-color: #F3CECD;
}

#timeline .demo-card--step1 .head::after {
	border-color: #F3CECD;
}

#timeline .demo-card--step2 {
	background-color: #F3CECD;
}

#timeline .demo-card--step2 .head::after {
	border-color: #F3CECD;
}

#timeline .demo-card--step3 {
	background-color: #F3CECD;
}

#timeline .demo-card--step3 .head::after {
	border-color: #F3CECD;
}

#timeline .demo-card--step4 {
	background-color: #F3CECD;
}

#timeline .demo-card--step4 .head::after {
	border-color: #F3CECD;
}

#timeline .demo-card--step5 {
	background-color: #F3CECD;
}

#timeline .demo-card--step5 .head::after {
	border-color: #F3CECD;
}

.cherry_blossom {
      overflow: hidden;
      position: relative;
      width: 100vw;
      height: 100vh;
      background: url('https://i.imgur.com/7M6L1Cd.jpg') center center no-repeat;
      background-size: cover;
      perspective: 1000px;
    }

    .cherry_blossom .petal {
      position: absolute;
      background: linear-gradient(-45deg, #ffb6c1 0%, #ffc5d0 40%, #ffdfe6 80%);
      border-radius: 10% 50% 40% 50%;
      z-index: 1;
      box-shadow: 0 0 20px rgba(255, 255, 255, 0.8);
      pointer-events: none;
      transform-style: preserve-3d;
      transition: transform 1000ms linear;
    }

    @keyframes fall {
      0% {
        top: -110%;
        opacity: 0;
        transform: rotateX(0deg) rotateY(0deg) rotateZ(0deg);
      }

      80% {
        opacity: 1;
      }

      100% {
        top: 110%;
        opacity: 0;
        transform: rotateX(360deg) rotateY(360deg) rotateZ(360deg);
      }
    }
    body, html {
      margin: 0;
      padding: 0;
      overflow-x: hidden; /* 수평 스크롤 제거 */
    }
    .cherry_blossom {
      overflow-y: auto; /* 수직 스크롤 추가 */
    }
    
    .btn-review {
      display: block;
      margin: 30px auto;
      padding: 10px 20px;
      background-color: #F3CECD;
      color: white;
      text-align: center;
      font-size: 16px;
      border: none;
      border-radius: 5px;
      text-decoration: none;
    }

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

	<section id="timeline" class="cherry_blossom">
		<h1>따뜻한 제주 봄 코스</h1>
		<p class="leader">봄기운 느껴지는 제주 코스</p>
		<div class="demo-card-wrapper">
			<div class="demo-card demo-card--step1">
				<div class="head">
					<div class="number-box">
						<span>01</span>
					</div>
					<h2>
						<span class="small">첫번째 코스</span><br> 엉덩물계곡
					</h2>
				</div>
				<div class="body">
					<p>
					중문관광단지의 색달해수욕장 뒷편으로 펼쳐진 노란빛 계곡입니다 2월 말부터 3월중순까지 이곳은 물대신 노란빛으로 가득합니다. 
					 길게 유채꽃길이 있어 예쁜풍경을 볼 수 있습니다
					</p>
					<img src="${contextPath}/resources/coursereviewImages/spr2.jpg" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step2">
				<div class="head">
					<div class="number-box">
						<span>02</span>
					</div>
					<h2>
						<span class="small">두번째 코스</span><br> 색달 해수 욕장 <br>
					</h2>
				</div>
				<div class="body">
					<p>
						엉덩물계곡 바로 앞에 있는 색달해수욕장은 예쁜 모래사장이 있고 맑고 청록한 바다색깔을 볼수 있습니다
					</p>
					<img src="${contextPath}/resources/coursereviewImages/spr4.jpg" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step3">
				<div class="head">
					<div class="number-box">
						<span>03</span>
					</div>
					<h2>
						<span class="small">세번째 코스</span><br> 카페 루시아
					</h2>
				</div>
				<div class="body">
					<p>
						중문관광단지에서 차로 약 15분거리에 위치한 카페 루시아입니다 안에 들어가서 밖에 뷰를 보면 
						박수기정과 유채꽃을 보면서 음료수과빵을 먹으면서 편안하게 쉴 수 있는 곳입니다
					</p>
					<img src="${contextPath}/resources/coursereviewImages/spr5.jpg" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step4">
				<div class="head">
					<div class="number-box">
						<span>04</span>
					</div>
					<h2>
						<span class="small">4번째 코스</span><br> 녹산로
					</h2>
				</div>
				<div class="body">
					<p>
						땅에는 유채꽃이 하늘에는 벚꽃들이 피어있는 코스인 녹산로는 3~4월 제주도의 필수 코스 입니다 
						드라이브 구경해도 좋고 걸어가면서 봄기운을 느껴보는 것도 좋습니다.
					</p>
					<img src="${contextPath}/resources/coursereviewImages/spr1.jpg" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step5">
				<div class="head">
					<div class="number-box">
						<span>05</span>
					</div>
					<h2>
						<span class="small">5번째 코스</span><br> 가파도
					</h2>
				</div>
				<div class="body">
					<p>
						벚꽃도 다 진 4월 초중순 가파도에서는 청보리축제가 열리는데도 가파도에서는 청보리로 가득해집니다
						섬이 거의 평지라 한바퀴 산책하는것도 좋고 자전거로 한바퀴 도는 것도 좋습니다.
					</p>
					<img src="${contextPath}/resources/coursereviewImages/spr3.jpg" alt="Graphic">
				</div>
			
			</div>
			
		</div>
		<a href="${contextPath}/index.jsp" class="btn-review">메인가기</a>
		
		<a href="${contextPath}/course.go" class="btn-review">다른 계절 선택하기</a>
		
		<a href="${contextPath}/list.rv?currentPage=1" class="btn-review">리뷰가기</a>
		
		</section>

	
  <!-- 벚꽃 -->
  
   <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
  function review(){
	  location.href = "${contextPath}/list.re?currentPage=1";
  }
  
    $(document).ready(function() {
        // 사쿠라 꽃잎이 흩날리는 애니메이션 효과 구현
        function createPetal() {
            const petalEl = $('<div class="petal"></div>');
            const size = Math.random() * 20 + 10; // 꽃잎의 크기는 10px에서 30px 사이 랜덤으로 설정
            const startPosX = Math.random() * document.body.clientWidth; // 시작 위치는 랜덤
            const speed = Math.random() * 5 + 5; // 낙하 속도도 랜덤

            petalEl.css({
                width: size,
                height: size,
                left: startPosX,
                top:"-20%"
            });

            $('.cherry_blossom').append(petalEl);

            // 애니메이션 실행
            petalEl.animate({
                top: "120%",
                opacity: 0
            }, speed * 2500, function() {
                // 애니메이션 종료 후 꽃잎 제거
                petalEl.remove();
            });
        }

        // 0.5초마다 꽃잎 생성
        setInterval(createPetal, 500);
    });
</script>
</body>
</html>