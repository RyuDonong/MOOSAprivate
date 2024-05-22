<%@page import="com.kh.common.model.vo.Photo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	String alertMsg = (String)session.getAttribute("alertMsg"); 
	Member loginUser = (Member)session.getAttribute("loginUser");
	Photo profile = (Photo)session.getAttribute("profile");
%>
<!DOCTYPE html>
<html>
<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
		<title>MOOSA 무사 이제와시냐</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css?after" />
		
		<style> /* 마이페이지 스타일 */
        .user-info {
            display: flex;
            align-items: center;
            background-color: #878787;
            color: #fff;
            padding: 8px 12px;
            border-radius: 20px;
            position: absolute;
            top: 10px;
            right: 10px;
        }

        .profile-pic {
            width: 15px;
            height: 15px;
            border-radius: 50%;
            margin-right: 8px;
        }

        .username,
        .email,
        .status {
            margin-right: 15px;
        }

		/* ========== 음악재생 =========== */
		#audioPlayer {
		  width: 300px;
		  height: 50px;
		  align : center;
		  background-color: #F0F0F0;
		  border-radius: 10px;
		  border : noen;
		  padding: 10px;
		  color: #fff;
		  font-family: Arial, sans-serif;
		  display:flex;
		}
		
		.player-header {
		  text-align: center;
		  margin-bottom: 10px;
		}
		
		#music-title {
		  font-weight: bold;
		  color:skyblue;
		  font-size: 16px;
		}
		
		.player-controls {
		  display: flex;
		  justify-content: center;
		}
		
		#playPauseBtn {
		  width: 1px;	
		  height: 15px;
		  margin-top : 15%;
		  margin-left: 50%;
		  background-color: #ccc;
		  border: none;
		  font-size: 10px;
		  line-height: 1px;
		  cursor: pointer;
		}
		
		#audio-back{
		  background-color : white;
		}

    	</style>
	</head>
<body>

<!-- el로 contextPath 사용하기 위한 작업(page에 변수처리해주기) -->
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	
	<script>
		    //로그인 성공 알림메세지
		var msg = "<%=alertMsg%>";
		
		if(msg!="null"){ 
			alert(msg); 
			<%session.removeAttribute("alertMsg");%>
		}
	</script>
	<!-- Header -->
	
		
	<!-- <div id="audio-back">
		 <div id="audioPlayer">
		  <div class="player-header">
		    <span id="music-title"></span>
		  </div>
		  
		  <div class="player-controls">
		    <button id="playPauseBtn"></button>
		  </div>
		  <audio id="myAudio" src="resources/uploadFiles/travel.mp4"></audio>
		</div>
	</div>		 -->
	
	
		<section id="header">
			<div class="container">

				<!-- Logo -->
					<h1 id="logo"><a href="index.jsp" style="color: rgb(65, 62, 62);">MOOSA</a></h1>
					<p>제주에 무사 와시냐</p>

				<!-- Nav -->
					<nav id="nav">
						<ul>
							<li><a href="${contextPath }/index.jsp"><span>홈으로</span></a></li>
							<!-- <li>
								<a href="#" class="icon fa-chart-bar"><span>관광명소</span></a>
								<ul>
									<li><a href="#">Lorem ipsum dolor</a></li>
									<li><a href="#">Magna phasellus</a></li>
									<li><a href="#">Etiam dolore nisl</a></li>
									<li>
										<a href="#">Phasellus consequat</a>
										<ul>
											<li><a href="#">Magna phasellus</a></li>
											<li><a href="#">Etiam dolore nisl</a></li>
											<li><a href="#">Phasellus consequat</a></li>
										</ul>
									</li>
									<li><a href="#">Veroeros feugiat</a></li>
								</ul>
							</li> -->
							<li><a href="${contextPath }/tour.to"><span>관광명소</span></a></li>
							<li><a href="${contextPath }/list.lo"><span>숙소</span></a></li>
							<li><a href="${contextPath }/course.go"><span>코스추천</span></a></li>
							<li><a href="${contextPath }/list.bo?currentPage=1"><span>소통게시판</span></a></li>
				<%--로그인 전 마이페이지 --%>
				<c:choose> 
					<c:when test="${loginUser!=null }">
						<li>
							<div class="user-info"> <!-- 로그인 후 마이페이지 -->
								<a href="${contextPath}/myPage.me" style="text-decoration: none; color:white;">
							        <img src="${contextPath }${profile.thumbnail}" class="profile-pic">
							        <span>${loginUser.userName } 님</span>
							        <span style="padding:10px 10px;">${loginUser.email }</span>
								</a>
						        <span><button onclick="logout();" style="font-size:10px; padding:10px 10px;">로그아웃</button></span>
						    </div>
						</li>
					</c:when>
					
					<c:otherwise> <%--로그인 후 마이페이지 --%>
						<li><button onclick="login();">MOOSA 로그인</button></li>
					</c:otherwise>
				</c:choose>
						</ul>
					</nav>

			</div>
		</section>
		
		<script>
			function login(){
				location.href = "${contextPath}/loginPage.go";
			}
			function logout(){
				location.href = "${contextPath}/logout.me";
			}
			

		   /*  $(function(){
		    	$.ajax({
			        url: "/moosa/music.info",
			        success: function (result) {
			            
			            var musicTitle = "<h3>"+result+"</h3>";
			            console.log(result);
			            
			            $('#music-title').html(musicTitle);
			            
			           // console.log('데이터 불러오기 성공!!');
			        },
			        error: function () {
			            //console.log('데이터 불러오기 실패');
			        }
			    });
		    }); */
			
		</script>
</body>
</html>