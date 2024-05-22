<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<!DOCTYPE HTML>
<html>
<head>
<title>마이페이지</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
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
		<%@include file="/views/common/header.jsp"%>
		<!-- Main -->
		<h1 style="text-align: center;">My Page</h1>
		<section id="main">
			<div class="container">
				<div class="row">
					<!-- Sidebar -->
					<div id="sidebar" class="col-3 col-12-medium">
						<!-- Excerpts -->
						<section>
							<ul class="divided">
								<li><a href="${contextPath }/updateMember.me">개인 정보 수정</a>
								</li>
								<li><a href="${contextPath }/selectWishList.me?userNo=${loginUser.userNo}">위시 리스트</a>
								</li>
								<li><a href="${contextPath }/myReview.me?userNo=${loginUser.userNo}">나의 리뷰</a></li>
							</ul>
						</section>
					</div>

					<!-- Content -->
					<div id="content" class="col-8 col-12-medium imp-medium">

						<!-- Post -->
						<article class="box post">
							<header>
								<h2>개인정보</h2>
							</header>
							<form action="${contextPath}/updateMember.me" method="post"
								id="updateMemberForm" enctype="multipart/form-data">
								<input type="hidden" name="photoNo" value="${loginUser.photoNo }">
								아이디 : <input type="text" name="userId" value="${loginUser.userId}" readonly><br>
								연락처 : <input type="text" value="${loginUser.phone}" name="phone">
								이메일 : <input type="email" name="email"
									value="${loginUser.email}"><br> 
								주소 : <input type="text" name="address" value="${loginUser.address}"><br>
								<!-- 프로필 사진 미리보기 -->
								<img src="${contextPath }${profile.thumbnail }" id="profile-pre">
								<input type="file" name="profile" onchange="loadImg(this);">
								<button type="submit">수정하기</button>

								<button type="button" data-toggle="modal"
									data-target="#updatePwdForm">비밀번호 변경하기</button>
								<button type="button" data-toggle="modal" 
									data-target="#deleteForm">탈퇴</button>
							</form>



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

	<script>
	//프로필 사진 미리 보기
		function loadImg(inputFile) {
			if (inputFile.files.length == 1) {
				var reader = new FileReader();
				reader.readAsDataURL(inputFile.files[0]);
				reader.onload = function(e) {
					$("#profile-pre").attr("src", e.target.result);

				}
			} else {
				$("#profile-pre").attr("src", null);
			}
		}
	</script>

	<!-- 비밀번호 변경 모달영역 -->
	<div class="modal" id="updatePwdForm">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" style="text-align: center;">비밀번호 변경</h4>
				</div>
				<!-- Modal body -->
				<div class="modal-body" align="center">
					<form action="${contextPath }/updatePwd.me" method="post">
						<!-- 현재 비밀번호,변경할 비밀번호,변경할 비밀번호 재입력 -->
						<!-- 회원의 아이디를 전송하기 (hidden) - 식별자용도 -->
						<!-- 사용자에겐 보이지 않지만 서버엔 전송될수 있는 타입 -->
						<input type="hidden" name="userId" value="${loginUser.userId }">
						<table>
							<tr>
								<td>현재 비밀번호</td>
								<td><input type="password" name="userPwd" required>
								</td>
							</tr>
							<tr>
								<td>변경할 비밀번호</td>
								<td><input type="password" name="updatePwd" required>
								</td>
							</tr>
							<tr>
								<td>변경할 비밀번호 확인</td>
								<td><input type="password" id="chkPwd" required></td>
							</tr>
						</table>
						<br>
						<button type="submit" onclick="return checkPwd();">비밀번호
							변경</button>
					</form>
				</div>
				<script>
					function checkPwd() {
						//변경할 비밀번호와 비밀번호 확인이 일치하는지 체크하기
						var updatePwd = $("input[name=updatePwd]").val();
						var checkPwd = $("#chkPwd").val();
						//비교후 같으면 true 다르면 false 를 반환하여 기본이벤트 막아주기
						if (updatePwd != checkPwd) {
							alert("변경할 비밀번호와 확인이 일치하지 않습니다.");
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

	<!-- 회원탈퇴 모달용 영역 -->
	<div class="modal" id="deleteForm">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" align="center">회원 탈퇴</h4>
				</div>
				<div class="modal-body" align="center">
					<form action="${contextPath }/delete.me" method="post">
						<!-- 식별자용 아이디 전달하기 -->
						<input type="hidden" name="userId" value="${loginUser.userId }">
						<table>
							<tr>
								<td>현재 비밀번호 :</td>
								<td><input type="password" id="deletePwd" required>
								</td>
							</tr>
						</table>
						<br>
						<button type="submit" 
							onclick="return deleteMember();">회원탈퇴</button>
					</form>
				</div>
				<script>
					function deleteMember(){
						//현재 입력한 비밀번호와 세션에 담겨있던 비밀번호가 일치한다면
						//정말 탈퇴할것인지 물어보고 그에 따른 처리하기
						var inputPwd = $("#deletePwd").val();
						var userPwd = "${loginUser.userPwd}";
						if (inputPwd == userPwd) {
							return confirm("정말로 탈퇴하실건가요? 탈퇴 후 복구는 불가능합니다.");
						} else {
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