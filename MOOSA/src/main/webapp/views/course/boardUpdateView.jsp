<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#update-area input,#update-area textarea{
		width :100%;
		box-sizing : border-box;
	}
</style>
</head>
<body>
	<%@include file = "/views/common/header.jsp"%>

	<div class="outer">
		<br>
		<h2 align="center">리뷰 수정</h2>
		<br>

		<form action="${contextPath }/update.rv" method="post"
			id="update-area" enctype="multipart/form-data">
			<input type="hidden" name="boardNo" value="${b.boardNo}">
			<table border="1" align="center">
				<tr>
					<th width="70">카테고리</th>
					<td width="70"><select name="category">
							<c:forEach items="${cList}" var="c">
								<option value="${c.categoryNo}">${c.categoryName}</option>
							</c:forEach>
					</select></td>
					<th width="70">제목</th>
					<td width="350"><input type="text" name="title" value="${b.boardTitle }" required>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3"><textarea name="content" rows="10"
							style="resize: none" required>${b.boardContent }</textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
											
					<td colspan="3">
						<c:if test="${at!=null }">
							${at.fileName }
						
						 <input type="hidden" name="originFileNo" value="${at.fileNo }">
						 <input type="hidden" name="originFileName" value="${at.changeName}">
						
						</c:if>
						<input type="file" name="reUploadFile">
					
					</td>
				</tr>
			</table>
			<br>
			<br>
			<div align="center">
				<button type="submit">수정하기</button>
				<button type="reset">취소</button>
			</div>
		</form>
		
		<script>
			$(function(){
				
				
				var choosed = "${b.category}"; 
				
				console.log(choosed);
				
				
				$("#update-area option").each(function(){
					console.log($(this));
					if($(this).text() == choosed){ 
						$(this).attr("selected",true); 
						return false; 
					}
				});
				
			});
		
		</script>

		<br>
		<br>
	</div>
</body>
</html>