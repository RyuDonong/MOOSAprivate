<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<!--
	Strongly Typed by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>숙소 정보</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css?after" />
	</head>
	<body class="left-sidebar is-preload">
		<div id="page-wrapper">

			<!-- Header -->
				<%@include file = "/views/common/header.jsp"%>

			<!-- Main -->
				<section id="main">
					<div class="container">
						<div class="row">
							<!-- Content -->
							<div id="content" class="col-12-medium imp-medium lod_content">
								<div class="inner">
									<header>
										<ul class="lod_menu">
											<li class="on"><button id="ho">HOTEL</button></li>
											<li><button >PENSION</button></li>
											<li><button>POOLVILA</button></li>
										</ul>
									</header>
									<div id="contentDiv">
									</div>
									<div id="pagingDiv">
									</div>
								</div>
							</div>
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
		 <script>
	
		 /* 비동기 통신으로 카테고리별 숙소 정보 조회해오는 비동기 통신 Script */
		$(function(){
			$("#ho").click();
		});
		
		$('.lod_menu li button').on('click', function() {
			$(this).parent('li').addClass('on');
			$(this).parent('li').siblings().removeClass('on');
			//console.log($(this).text());
			$.ajax({
				url : "${contextPath}/selectList.lo",
				data : {category :$(this).text()},
				success : function(list){
					var html = "";
					for (var i in list){
						html += '<section class="tiles">';
						html += '<article class="style2">';
						html += '<span class="image">';
						html += '<img src="/moosa'+list[i].Thumbnail+'"/>';
						html += '</span>';
						html += '<a href="/moosa/lodDetail.lo?lno='+list[i].lodNo+'">';
						html += '<h2 id="contentTitle">'+list[i].lodName+'</h2>';
						html += '<div class="content"><p>'+list[i].lodAddress+'</p></div>';
						html += '</a></article>';
						if(${not empty loginUser}){
							html += '<button onclick="return addWishList('+list[i].lodNo+',${loginUser.userNo});">위시리스트</button>';
						}
						html += '</section>';
					}
					$("#contentDiv").html(html);
				},
				error : function(){
					console.log("통신 실패");
				}
			});
			
		});
		//위시리스트 추가
		function addWishList(lno,userNo){
			var flag = confirm("위시리스트를 변경하시겠습니까?");
			if(flag){
				$.ajax({
					url : "${contextPath}/addWishList.me",
					data : {
						lno:lno,
						userNo:userNo
					},
					success : function(result){
						
						if(result>0){
							alert("위시리스트 변경 성공 마이페이지에서 확인하세요.");
						}else{
							alert("위시리스트 변경 실패 관리자에게 문의하세요.");
						}
					},
					error : function(){
						console.log("통신실패");
					}
				});
			}else{
				return false;
			}
			
		};
	</script> 

	</body>
</html>