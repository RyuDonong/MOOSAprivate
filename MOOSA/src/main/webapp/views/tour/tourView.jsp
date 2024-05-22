<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String contextPath = request.getContextPath();
%>





<!DOCTYPE HTML>


<html>
<head>
<title>관광명소</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />

<style>
/* 그리드 시스템 스타일 */
.row {
	display: flex;
	flex-wrap: wrap;
	margin: -15px; /* 그리드 아이템 간격 조절 */
	width: 100%; /* 한 행에 여러 개의 요소 포함 */
}

.col-2 {
	width: calc(25% - 30px); /* 4열 그리드, 여백 고려 */
	margin: 15px; /* 그리드 아이템 간격 조절 */
}

/* 반응형 그리드 시스템 */
@media ( max-width : 980px) {
	.col-2 {
		width: calc(50% - 30px); /* 2열 그리드로 변경, 여백 고려 */
	}
}

@media ( max-width : 480px) {
	.col-2 {
		width: calc(100% - 30px); /* 1열 그리드로 변경, 여백 고려 */
	}
}

.img {
	width: 100%;
	height: auto;
	max-width: 200px; /* 이미지 최대 너비 설정 */
	max-height: 200px; /* 이미지 최대 높이 설정 */
}

section#features {
	padding: 0;
}
</style>


</head>
<%@include file="/views/common/header.jsp"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<br>

<%
if ((loginUser != null) && loginUser.getUserId().equals("admin")) {
%>
<div align="center">
	<a href="<%=contextPath%>/insert.to" class="btn btn-info">관광명소 추가</a>

</div>

<%
}
%>
<br>

<pre>
	<h3>                                            해변                                 일출*일몰                       액티비티                               음식                                   축제</h3>
</pre>


<body class="homepage is-preload">
	<div id="page-wrapper">
		<!-- 반복문으로 썸네일 뽑아주기 -->
		<section id="features">
			<div class="container">
				<div class="row">
					<c:forEach var="th" items="${list}">
						<div class="col-2">
							<section>
								<div class="image feat" align="center"
									onclick="location.href='${contextPath}/detail.tO?bno=${th.boardNo}'">
									<img src="${contextPath}${th.thumbnailImg}"
										alt="Thumbnail Image" width="200" height="200">
									<header>
										<p>
											<b>${th.boardTitle}</b><br> 조회수 : ${th.count}
										</p>
									</header>
								</div>
							</section>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
	</div>




	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.dropotron.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>