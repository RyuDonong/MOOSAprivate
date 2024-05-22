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
/* Card sizing */
/* Colors */
/* Calculations */
/* Placeholders */
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
	background-color: #89CFF0;
}

#timeline .demo-card--step1 .head::after {
	border-color: #89CFF0;
}

#timeline .demo-card--step2 {
	background-color: #89CFF0;
}

#timeline .demo-card--step2 .head::after {
	border-color: #89CFF0;
}

#timeline .demo-card--step3 {
	background-color: #89CFF0;
}

#timeline .demo-card--step3 .head::after {
	border-color: #89CFF0;
}

#timeline .demo-card--step4 {
	background-color: #89CFF0;
}

#timeline .demo-card--step4 .head::after {
	border-color: #89CFF0;
}

#timeline .demo-card--step5 {
	background-color: #89CFF0;
}

#timeline .demo-card--step5 .head::after {
	border-color: #89CFF0;
}

.btn-review {
      display: block;
      margin: 30px auto;
      padding: 10px 20px;
      background-color: #89CFF0;
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
		<h1>시원한 여름 코스</h1>
		<p class="leader">더위를 날려주는 여름 제주 코스</p>
		<div class="demo-card-wrapper">
			<div class="demo-card demo-card--step1">
				<div class="head">
					<div class="number-box">
						<span>01</span>
					</div>
					<h2>
						<span class="small">1번째 코스</span><br> 만장굴
					</h2>
				</div>
				<div class="body">
					<p>
					천연기념물로 지정되어 있는 이곳은 만장굴입니다 
					시원한 동굴에서 자연을 구경하면서 더위 피할수 있는 좋은 곳입니다
					</p>
					<img src="${contextPath}/resources/coursereviewImages/sum3.jpg" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step2">
				<div class="head">
					<div class="number-box">
						<span>02</span>
					</div>
					<h2>
						<span class="small">2번째 코스</span><br> 절물자연휴양림 <br>
					</h2>
				</div>
				<div class="body">
					<p>
						제주시내와 가까운 대표적인 휴양림입니다 시작부터 삼나무 숲이 펼쳐져 있고 상쾌한 공기를 맡아볼수 있습니다
						숲길이라 시원한 바람을 느끼면서 산책할 수 있는 곳입니다
					</p>
					<img src="${contextPath}/resources/coursereviewImages/sum4.jpg" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step3">
				<div class="head">
					<div class="number-box">
						<span>03</span>
					</div>
					<h2>
						<span class="small">3번째 코스</span><br> 쇠소깍
					</h2>
				</div>
				<div class="body">
					<p>
						쇠소깍은 제주 현무암 지하를 흐르는 물이 분출하여 바닷물과 만나 깊은 웅덩이를 형성하는곳입니다
						계곡의 아름다운 풍경을 볼 수 있고 조각배를 타며 직접 즐길 수도 있습니다
					</p>
					<img src="${contextPath}/resources/coursereviewImages/sum1.png" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step4">
				<div class="head">
					<div class="number-box">
						<span>04</span>
					</div>
					<h2>
						<span class="small">4번째 코스</span><br> 서귀다원
					</h2>
				</div>
				<div class="body">
					<p>
						차밭 사이로 시원한 바람이 부는 이곳은 서귀다원입니다
						조용하고 시원한 곳을 찾는다면 이 곳을 추천드립니다 
					</p>
					<img src="${contextPath}/resources/coursereviewImages/sum2.jpg" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step5">
				<div class="head">
					<div class="number-box">
						<span>05</span>
					</div>
					<h2>
						<span class="small">5번째 코스</span><br> 금오름 패러글라이딩
					</h2>
				</div>
				<div class="body">
					<p>
						더운 여름에 시원바람을 맞으며 하는 패러글라이딩입니다 
						하늘에서 제주도 풍경 보면서 더위를 날리 수 있습니다
					</p>
					<img src="${contextPath}/resources/coursereviewImages/sum5.jpg" alt="Graphic">
				</div>
			</div>

		</div>
		<a href="${contextPath}/index.jsp" class="btn-review">메인가기</a>
		
		<a href="${contextPath}/course.go" class="btn-review">다른 계절 선택하기</a>
		
		<a href="${contextPath}/list.rv?currentPage=1" class="btn-review">리뷰가기</a>
		
		</section>
		
		
	
</body>
</html>