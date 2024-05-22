<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>마이페이지</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
		<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	</head>
	<body class="left-sidebar is-preload">
		<div id="page-wrapper">

			<!-- Header -->
				<%@include file="/views/common/header.jsp" %>
			<!-- Main -->
				<h1 style="text-align:center;">My Page</h1>
				<section id="main">
					<div class="container">
						<div class="row">
							<!-- Sidebar -->
								<div id="sidebar" class="col-3 col-12-medium">
									<!-- Excerpts -->
										<section>
											<ul class="divided">
												<li >
													<a data-toggle="modal" data-target="#checkPwdForm">개인 정보 수정</a>
												</li>
												<li>
													<a href="${contextPath }/selectWishList.me?userNo=${loginUser.userNo}">위시 리스트</a>
												</li>
												<li>
													<a href="${contextPath }/myReview.me?userNo=${loginUser.userNo}">나의 리뷰</a>
												</li>
											</ul>
										</section>
								</div>

							<!-- Content -->
								<div id="content" class="col-8 col-12-medium imp-medium">

									<!-- Post -->
										<article class="box post">
											<header>
												<h2></h2>
											</header>
											
										</article>

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

	<!-- 비밀번호 확인후 개인정보 수정화면으로 넘기기 -->
	<div class="modal" id="checkPwdForm">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" style="text-align: center;">비밀번호 확인</h4>
				</div>
				<!-- Modal body -->
				<div class="modal-body" align="center">
					<form action="${contextPath }/updateMember.me" method="get">
						<h1>${loginUser.userName } 님의 소중한 개인정보 보호를 위해 비밀번호를 재확인합니다.</h1>
						<table>
							<tr>
								<td>아이디</td>
								<td><input type="text" value="${loginUser.userId }" readonly >
								</td>
							</tr>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" id="chkPwd" required>
								</td>
							</tr>
						</table>
						<br>
						<button type="submit" onclick="return checkPwd();">확인</button>
					</form>
				</div>
				<script>
					function checkPwd() {
						//변경할 비밀번호와 비밀번호 확인이 일치하는지 체크하기
						var checkPwd = $("#chkPwd").val();
						var userPwd = '${loginUser.userPwd}';
						//비교후 같으면 true 다르면 false 를 반환하여 기본이벤트 막아주기
						if (checkPwd != userPwd) {
							alert("비밀번호가 일치하지 않습니다.");
							return false;
						}
					}
				</script>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	</body>
</html>