<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<!-- Popper JS -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>MOOSA 게시판 글작성 페이지</title>

	<style>
		#board-insert input,#board-insert textarea{
			width:100%;
			box-sizing:border-box;
		}
		#board-insert-outer{
			margin: 10%;
		}
	
	</style>

</head>
<body>
	<%@include file = "/views/common/header.jsp" %>

	<div id="board-insert-outer">
	<h2 align="center">게시글 작성 페이지</h2>

	<form action="${contextPath }/insert.bo" method="post" id="board-insert" enctype="multipart/form-data">
	<input type="hidden" name="userNo" value="${loginUser.userNo }">
		<table border="1" align="center">
		
			<!-- 카테고리종류, 제목, -->
			<tr>
				<th width="70">카테고리</th>
				<td width="70">
					<select name="board-category">
						<c:forEach var="c" items="${cgList }">
							<option value="${c.categoryNo }">${c.categoryName }</option>
						</c:forEach>
					</select>
				</td>
				<th width="70">제목</th>
				<td width="350"><input type="text" name="board-title" required></td>
			</tr>
		
			<!-- 내용 -->
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="8" style="resize:none;" name="board-content" required></textarea>
				</td>
			</tr>
			
			<!-- 첨부파일 -->
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<input type="file" name="uploadFile">
				</td>
			</tr>
			
		</table>
		<br><br>
		<div align="center">
			<button type="submit">글작성 완료</button>
			<button type="button" onclick="cancel();">취소</button>
			<!-- 취소 클릭시 listView 로 보내기  -->
		</div>
	</form>
	</div>
	
	<script>
	//글작성 취소 함수
		function cancel(){
			location.href="${contextPath}/list.bo";
		}
	
	</script>
	
	<script>
		    //로그인 실패 알림메세지
		var msg = "<%=alertMsg%>";
		
		if(msg!="null"){ 
			alert(msg); 
			<%session.removeAttribute("alertMsg");%>
		}
	</script>
	

</body>
</html>




















