<%@page import="com.kh.lodging.model.vo.Review"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member" import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("list");

%>

<!DOCTYPE HTML>
<!--
	Strongly Typed by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>${lod.lodName }</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<!-- swiper cdn -->
		<!-- CSS -->
		<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
		
		<!-- JS -->
		<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
		<!-- /swiper cdn -->
		<!-- 카카오 지도 api -->
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8bbc3ea0a937b0baf9ab04c7ad6b1970&libraries=services"></script>
		<style type="text/css">
		#scoreDiv label{
			display:inline;
		}
		#scoreDiv input[type=checkbox]{
			display:inline;
		}
		
		
		</style>
	</head>
	<body class="no-sidebar is-preload">
		<div id="page-wrapper">
				
			<!-- Header -->
			<%@include file="/views/common/header.jsp" %>	
			<!-- Main -->
				<section id="main">
					<div class="container">
						<div id="content">

							<!-- Post -->
								<article class="box post">
									<header>
										<h2>${lod.category }</h2>
									</header>
									<span class="image featured"><img src="/moosa${lod.thumbnail }" alt="" /></span>
									<h3>숙소 정보</h3>
									<p>${lod.category }</p>
									<div id="map" style="width:500px;height:400px;"></div>
									<div>${lod.lodAddress }</div><br>
									<div>${lod.lodInfo }</div>
									<hr>
									<h3>숙소 방 정보</h3>
									<div class="roomInfoWrap">
										<c:forEach items="${rList }" var="a">
											<div class="roomInfo">
												<div class="roomImagesSlider">
												    <div class="swiper-wrapper">
												    	<c:forEach items="${rpList }" var='rp'>
														    <c:if test="${a.roomNo eq rp.roomNo }">
															    <div class="swiper-slide"><img src="${contextPath }${rp.thumbnail }"></div>
														    </c:if>
												    	</c:forEach>
												    </div>
											    	<div class="swiper-button-prev"></div>
													<div class="swiper-button-next"></div>
										    	</div>
												<div>${a.roomName }</div>
												<div>${a.roomInfo }</div>
											</div>
										</c:forEach>
									</div>
									<hr>
									<h3>고객 리뷰</h3>
									<div class="review-textarea">
										<c:choose>
											<c:when test="${not empty loginUser }">
												<form action="insertReview.lo" id="reviewForm" method="post" enctype="multipart/form-data">
													<input type="hidden" name="userNo" value="${loginUser.userNo }">
													<input type="hidden" name="lno" value = ${lod.lodNo }>
													<!-- 별점 입력 영역 -->
													<div id="scoreDiv">
														별점을 작성해 주세요 <br>
														<label for="rate1"><img id="starImg1" src="${contextPath }/images/star.png"></label><input type="radio" name="count" value="1" id="rate1">
												        <label for="rate2"><img id="starImg2" src="${contextPath }/images/star.png"></label><input type="radio" name="count" value="2" id="rate2">
												        <label for="rate3"><img id="starImg3" src="${contextPath }/images/star.png"></label><input type="radio" name="count" value="3" id="rate3">
												        <label for="rate4"><img id="starImg4" src="${contextPath }/images/star.png"></label><input type="radio" name="count" value="4" id="rate4">
												        <label for="rate5"><img id="starImg5" src="${contextPath }/images/star.png"></label><input type="radio" name="count" value="5" id="rate5">
													</div>
													<div>
														방을 골라주세요 <br>
														<select name=roomNo >
															<c:forEach var="d" items="${rList}">
																<option value=${d.roomNo }>${d.roomName }</option>
															</c:forEach>
														</select>
													</div> <br>
													<textarea name="review-content"rows="5" cols="100" required></textarea> <br>
													<input type="file" id="reviewImg1" name="reviewImg1">
													<input type="file" id="reviewImg2" name="reviewImg2">
													<input type="file" id="reviewImg3" name="reviewImg3">
													<button type="submit">작성</button>
												</form>
												<hr>
											</c:when>
											<c:otherwise>
												<textarea readonly rows="5" cols="100">로그인 후 이용 가능한 서비스 입니다.</textarea><br>
											 	<button disabled>작성</button>
												<hr>	
											</c:otherwise>
										</c:choose>
										
									</div>
									<div class="review">
										<c:choose>
											<c:when test="${empty list}">작성된 리뷰가 없습니다.</c:when>
											<c:otherwise>
												<c:forEach var="r" items="${list}">
													<!-- 반복문으로 별점 뽑아내기 -->
													<ul class="score">
														<li>
														 	<c:forEach begin="1" end="${r.count}">
																<img class="full" alt="" src="/moosa/images/fullStar.png">
															</c:forEach>
															<c:forEach begin="${r.count + 1}" end="5">
																<img alt="" src="/moosa/images/star.png">
															</c:forEach>
														</li>
													</ul>
													<div>${r.userNo }</div>
													<div>${r.roomNo }</div>
													<div>${r.reviewContent}</div>
													<div class="reviewPhoto">
														<c:forEach var="p" items="${pList }">
															<c:if test="${not empty p && r.reviewNo eq p.reviewNo }">
																<div>	
																	<img  alt="" src="/moosa${p.thumbnail }">
																</div>
															</c:if>
														</c:forEach>
													</div>
													<hr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</div>
								</article>

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
	
			<!-- 별점 script -->
			<script>
			$("#scoreDiv input[type=radio]").on("click",function(){
				//console.log($(this).val());
				var value = $(this).val();
				//만약 한번 누르고 다른 값을 다시 누르면 초기화
				$("#scoreDiv label").children().attr("src","${contextPath}/images/star.png")
				for(let i=0;i<value;i++){
					//value만큼 반복 해서 속성에 접근
					$("#scoreDiv label").children("#starImg"+(i+1)).attr("src","${contextPath}/images/fullStar.png")
				}
			});
			
			const mySwiper = new Swiper('.roomImagesSlider', {
				loop: true,
				/* autoplay: {//자동으로 넘어가기 
					    delay: 3000
				}, */ 
			    navigation: {
				    nextEl: '.swiper-button-next',
				    prevEl: '.swiper-button-prev',
				},	  
			
				});
			</script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch('${lod.lodAddress}', function(result, status) {
		
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        /* var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">${lod.lodName}</div>'
		        });
		        infowindow.open(map, marker); */
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
</script>
	</body>
</html>