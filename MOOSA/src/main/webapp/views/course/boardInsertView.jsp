<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#enroll-area input,#enroll-area textarea{
		width :100%;
		box-sizing : border-box;
	}
</style>
	
	
</head>
<body>	

	<%@include file = "/views/common/header.jsp"%>
	
	<div class="outer">
		<br>
		<h2 align="center">리뷰 작성</h2>
		<br>
		
		<form action="${contextPath }/insert.rv" method="post" id="enroll-area" enctype="multipart/form-data">
			<input type="hidden" name="userNo" value="${loginUser.userNo }">
			<table border="1" align="center">
				<tr>
					<th width="70">계절</th>
					<td width="70">
						<select name="board_category">
							<!-- 카테고리 정보 db에서 조회해와서 넣어주기(반복문) -->
							<c:forEach items="${cList}" var="c">
								<option value="${c.categoryNo}">${c.categoryName}</option>
							</c:forEach>
						</select>
					</td>
					<th width="70">제목</th>
					<td width="350"><input type="text" name="title" required> </td>
				</tr>			
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea name="content" rows="10" style="resize:none" required></textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3">
						<input type="file" name="UploadFile">
					</td>
				</tr>
			</table>
			<br><br>
			<div align="center">
				<button type="submit">글작성</button>
				<button type="reset">취소</button>
			</div>
		</form>
	
		<br><br>
	</div>
	<%@include file = "/views/common/footer.jsp"%>

</body>
</html>
