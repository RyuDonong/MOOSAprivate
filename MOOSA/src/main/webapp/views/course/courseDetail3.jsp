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
	background-color: #D87C28;
}

#timeline .demo-card--step1 .head::after {
	border-color: #D87C28;
}

#timeline .demo-card--step2 {
	background-color: #D87C28;
}

#timeline .demo-card--step2 .head::after {
	border-color: #D87C28;
}

#timeline .demo-card--step3 {
	background-color: #D87C28;
}

#timeline .demo-card--step3 .head::after {
	border-color: #D87C28;
}

#timeline .demo-card--step4 {
	background-color: #D87C28;
}

#timeline .demo-card--step4 .head::after {
	border-color: #D87C28;
}

#timeline .demo-card--step5 {
	background-color: #D87C28;
}

#timeline .demo-card--step5 .head::after {
	border-color: #D87C28;
}
.btn-review {
      display: block;
      margin: 30px auto;
      padding: 10px 20px;
      background-color: #D87C28;
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
		<h1>힐링 가을 코스</h1>
		<p class="leader">편안하고 힐링하는 가을 제주 코스</p>
		<div class="demo-card-wrapper">
			<div class="demo-card demo-card--step1">
				<div class="head">
					<div class="number-box">
						<span>01</span>
					</div>
					<h2>
						<span class="small">1번째 코스</span><br> 마노르블랑 핑크뮬리축제
					</h2>
				</div>
				<div class="body">
					<p>
					마노르블랑에서 핑크뮬리 축제를 합니다 
					축제 기간은 9월 11월이고 환상적인 조망과 함께 핑크뮬리 인생샷을 남길 수 있는 다양한 산책로 와 포토존이 있습니다
					</p>
					<img src="${contextPath}/resources/coursereviewImages/aut2.jpg" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step2">
				<div class="head">
					<div class="number-box">
						<span>02</span>
					</div>
					<h2>
						<span class="small">2번째 코스</span><br> 제주 산방산탄산온천 <br>
					</h2>
				</div>
				<div class="body">
					<p>
						제주산방탄산온천은 제주 최초의 대중온천이자 국내에서 보기 힘든 탄산 온천입니다 하루의 피로를 풀기 아주 좋은 곳입니다
					</p>
					<img src="${contextPath}/resources/coursereviewImages/aut5.jpg" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step3">
				<div class="head">
					<div class="number-box">
						<span>03</span>
					</div>
					<h2>
						<span class="small">3번째 코스</span><br> 도두동무지개해안도로
					</h2>
				</div>
				<div class="body">
					<p>
						가을의 높은 하늘과 어울리는 곳입니다 푸른 하늘과 시원한 바닷바람 느끼면서 힐링 할 수 있는 곳입니다
					</p>
					<img src="${contextPath}/resources/coursereviewImages/aut1.jpg" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step4">
				<div class="head">
					<div class="number-box">
						<span>04</span>
					</div>
					<h2>
						<span class="small">4번째 코스</span><br> 하원수로길
					</h2>
				</div>
				<div class="body">
					<p>
						가을에 빠질 수 없는 단풍을 볼 수 있는 곳입니다 
						알록달록한 단풍을 보면서 가을을 느낄수 있습니다
					</p>
					<img src="${contextPath}/resources/coursereviewImages/aut4.jpg" alt="Graphic">
				</div>
			</div>

			<div class="demo-card demo-card--step5">
				<div class="head">
					<div class="number-box">
						<span>05</span>
					</div>
					<h2>
						<span class="small">5번째</span><br> 천지연폭포 <br>
					</h2>
				</div>
				<div class="body">
					<p>
						서귀포 천지연폭포는 조면질 안산암의 기암절벽이 하늘높이 치솟아 마치 신선의 세계로 들어온 것 같은 황홀경을 느끼게 합니다 
					</p>
					<img src="${contextPath}/resources/coursereviewImages/aut5.jpg" alt="Graphic">
				</div>
			</div>
	
		</div>
		<a href="${contextPath}/index.jsp" class="btn-review">메인가기</a>
		
		<a href="${contextPath}/course.go" class="btn-review">다른 계절 선택하기</a>
		
		<a href="${contextPath}/list.rv?currentPage=1" class="btn-review">리뷰가기</a>
		</section>

		
</body>
</html>