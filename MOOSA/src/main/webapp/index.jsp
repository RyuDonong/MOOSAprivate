<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%> <!-- Member 필드  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Core 라이브러리 -->

<!DOCTYPE HTML>


<html>
	<head>
		<title>MOOSA 무사 이제와시냐</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	    <!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
		<!-- Popper JS -->
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		
		<style>
		
		/* ========== 이미지 슬라이드 =========== */
		
        .slider {
		  width: 500px;
		  height: 300px;
		  position: relative;
		  overflow: hidden;
		}

		.slide {
		  position: absolute;
		  top: 0;
		  left: 0;
		  width: 100%;
		  height: 100%;
		  opacity: 0;
		  transition: opacity 1s ease-in-out;
		}

		.slide.active {
		  opacity: 1;
		}
		
		.prev, .next {
		  position: absolute;
		  top: 50%;
		  transform: translateY(-50%);
		  font-size: 16px;
		  padding: 10px;
		  cursor: pointer;
		  
		  z-index: 1; /* 버튼이 이미지 위에 위치하도록 z-index 설정 */
  		  opacity: 1; /* 버튼의 투명도를 1로 설정 */
  		  overflow:visible;
		  border:none;
		  background:transparent;
		}
		
		.prev {
		  left: 20px;
		}
		
		.next {
		  right: 20px;
		}
		
    </style>
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body class="homepage is-preload">
		<div id="page-wrapper">
		
			
			<!-- 네비바 include -->
			<%@include file = "/views/common/header.jsp"%>
			
			
			<!-- 메인배너 슬라이더-->
			
					<div class="container" align="center">
							<div class="slider">
								<button class="prev">◁</button>
									        <div class="slide" >
										        <a href="https://map.naver.com/p/search/%EC%A0%9C%EC%A3%BC%20%ED%92%8D%EC%B0%A8/place/17040007?c=15.00,0,0,0,dh&placePath=%3Fentry%253Dbmp" target="_blank">
										           <img src="views/common/images/jeju1.jpg" style="width: 100%; height: auto;">
										        </a>
									        </div>
									        <div class="slide">
										        <a href="https://map.naver.com/p/search/%EC%A0%9C%EC%A3%BC%20%ED%92%8D%EC%B0%A8/place/20104174?c=15.00,0,0,0,dh&placePath=%3Fentry%253Dbmp" target="_blank">
										          <img src="views/common/images/jeju2.jpg" style="width: 100%; height: auto;">
										        </a>
									        </div>
									        <div class="slide">
										        <a href="https://map.naver.com/p/search/%EC%A0%9C%EC%A3%BC%20%ED%92%8D%EC%B0%A8/place/1693044854?c=15.00,0,0,0,dh&placePath=%3Fentry%253Dbmp" target="_blank">
										          <img src="views/common/images/jeju3.jpg" style="width: 100%; height: auto;">
										        </a>
									        </div>
							    <button class="next">▷</button>
						    </div>
					</div>
					
			<!-- 숙소 -->
				<section id="features">
					<div class="container">
						<header>
							<h2>MOOSA : 숙소</h2>
						</header>
						<div class="lod-list" style="display:flex;">
							
						</div>
					</div>
				</section>
				
				
			<!-- 관광명소 -->
			<section id="features">
					<div class="container">
						<header>
							<h2>MOOSA : 관광명소</h2>
						</header>
						<div class="tour-list" style="display:flex;">
							
						</div>
					</div>
				</section>
			
			
			<!-- 코스추천 -->
			<section id="features">
					<div class="container">
						<header>
							<h2>MOOSA : 코스</h2>
						</header>
						<div class="course-list" style="display:flex;">
							
						</div>
					</div>
				</section>
				
			<!-- 게시판추천 -->
			<section id="features">
					<div class="container">
						<header>
							<h2>MOOSA : 게시판</h2>
						</header>
						<div class="b-list" style="display:flex;">
							
						</div>
					</div>
				</section>
			
			
			
				
			<!-- Footer -->
			<%@include file = "/views/common/footer.jsp"%>	

		</div>
		
	<script>
	//=========== 페이지 처음 시작시 가져올 데이터 ==========
	 $(function () {
	    /*const audio = document.getElementById('myAudio');
	    const playPauseBtn = document.getElementById('playPauseBtn');
	
	    playPauseBtn.addEventListener('click', () => {
	        if (audio.paused) {
	            audio.play();
	            playPauseBtn.textContent = '||';
	        } else {
	            audio.pause();
	            playPauseBtn.textContent = '▶';
	        }
    }); */

  //=========== <숙소> 페이지 처음 시작시 가져올 데이터 ==========
    $.ajax({
	        url: "/moosa/lod.info",
	        success: function (list) {
	            var lodList = "";
	            for (var i = 0; i < list.length; i++) {
	                lodList += "<div class='col-4 col-6-medium col-12-small'>"
	                    + "<section>"
	                    + "<a href='/moosa/list.lo'>"
	                    + "<img src='/moosa" + list[i].filePath + "" + list[i].changeName + "' style='width:100%; height:auto;'>"
	                    + "</a>"
	                    + "</header>"
	                    + "<h3>" + list[i].lodName + "</h3>"
	                    + "</header>"
	                    + "<body>"
	                    + "<p>" + list[i].lodAddress + "</p>"
	                    + "</body>"
	                    + "</section>"
	                    + "</div>";
	            }
	            $('.lod-list').html(lodList);
	            console.log('데이터 불러오기 성공!!');
	        },
	        error: function () {
	            console.log('데이터 불러오기 실패');
	        }
    	});
  
  //=========== <관광명소> 페이지 처음 시작시 가져올 데이터 ==========
  $.ajax({
	        url: "/moosa/tour.info",
	        success: function (list) {
	            var lodList = "";
	            for (var i = 0; i < list.length; i++) {
	                lodList += "<div class='col-4 col-6-medium col-12-small'>"
	                    + "<section>"
	                    + "<a href='/moosa/tour.to'>"
	                    + "<img src='/moosa" +list[i].changeName  + "" + list[i].filePath + "' style='width:100%; height:auto;'>"
	                    + "</a>"
	                    + "</header>"
	                    + "<h3>" + list[i].boardTitle + "</h3>"
	                    + "</header>"
	                    + "<body>"
	                    + "<p>" + list[i].boardContent + "</p>"
	                    + "</body>"
	                    + "</section>"
	                    + "</div>";
	            }
	            $('.tour-list').html(lodList);
	            console.log('데이터 불러오기 성공!!');
	        },
	        error: function () {
	            console.log('데이터 불러오기 실패');
	        }
    	});
  
  
  //=========== <코스 리뷰 추천> 페이지 처음 시작시 가져올 데이터 ==========
	  
		  $.ajax({
		        url: "/moosa/course.info",
		        success: function (list) {
		            var lodList = "";
		            for (var i = 0; i < list.length; i++) {
		                lodList += "<div class='col-4 col-6-medium col-12-small'>"
		                    + "<section>"
		                    + "<a href='/moosa/course.go'>"
		                    + "<img src='/moosa" + list[i].filePath + "" + list[i].changeName + "' style='width:100%; height:auto;'>"
		                    + "</a>"
		                    + "</header>"
		                    + "<h3>" + list[i].boardTitle + "</h3>"
		                    + "</header>"
		                    + "<body>"
		                    + "<p>" + list[i].boardContent + "</p>"
		                    + "</body>"
		                    + "</section>"
		                    + "</div>";
		            }
		            $('.course-list').html(lodList);
		            console.log('데이터 불러오기 성공!!');
		        },
		        error: function () {
		            console.log('데이터 불러오기 실패');
		        }
	    	});
  
	//=========== <게시판> 페이지 처음 시작시 가져올 데이터 ==========
		  $.ajax({
		        url: "/moosa/main.info",
		        success: function (list) {
		            var lodList = "";
		            for (var i = 0; i < list.length; i++) {
		                lodList += "<div class='col-4 col-6-medium col-12-small'>"
		                    + "<section>"
		                    + "<a href='http://www.naver.com'>"
		                    + "<img src='/moosa" + list[i].filePath + "" + list[i].changeName + "' style='width:100%; height:auto;'>"
		                    + "</a>"
		                    + "</header>"
		                    + "<h3>" + list[i].boardTitle + "</h3>"
		                    + "</header>"
		                    + "<body>"
		                    + "<p>" + list[i].boardContent + "</p>"
		                    + "</body>"
		                    + "</section>"
		                    + "</div>";
		            }
		            $('.b-list').html(lodList);
		            console.log('데이터 불러오기 성공!!');
		        },
		        error: function () {
		            console.log('데이터 불러오기 실패');
		        }
	    	});
		
	  });

	 //========== 이미지 슬라이드 ===========
		$(function() {
		  var slides = $('.slide');
		  var currentSlide = 0;
		  
		//페이지 로드 처음엔 사진을 바로 보여줘야
		  slides.eq(currentSlide).addClass('active');
		  setTimeout(function(){ 
			  var slideInterval = setInterval(nextSlide, 7000);
		  },0);

		  function nextSlide() { //(현재슬라이드 +1) % 슬라이드 이미지 갯수
		    slides.eq(currentSlide).removeClass('active');
		    currentSlide = (currentSlide + 1) % slides.length;
		    slides.eq(currentSlide).addClass('active');
		  }

		  function prevSlide() {
		    slides.eq(currentSlide).removeClass('active');
		    currentSlide = (currentSlide - 1 + slides.length) % slides.length;
		    slides.eq(currentSlide).addClass('active');
		  }

		  $('.next').click(function(e){
			  e.preventDefault(); // 기본 동작 방지 (내장객체, click,submit등을 기본동작을 취소)
			    nextSlide();
		  });
		  
		  $('.prev').click(function(e){
			  e.preventDefault();
		  		prevSlide();
		  });
		  	
		  $('.slide img').click(function(e) {
			    e.preventDefault();
			    window.open($(this).parent('a').attr('href')); // URL 새 탭에서 열기
			   
			    console.log($('.slide a').attr('href'));
			});
		});
    </script>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.dropotron.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>