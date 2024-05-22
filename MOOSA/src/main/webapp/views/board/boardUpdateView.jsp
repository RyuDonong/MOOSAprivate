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
<title>MOOSA 소통해요</title>
</head>
<body>
	<%@include file = "/views/common/header.jsp" %>
	
	<div id="board-update-outer">
	<h2 align="center">게시글 수정 페이지입니다.</h2>

	<form action="${contextPath }/update.bo" method="post" id="board-update" enctype="multipart/form-data">
	<input type="hidden" name="boardNo" value="${b.boardNo }">
		<table border="1" align="center">
		
			<!-- 카테고리종류, 제목, -->
			<tr>
				<th width="70">카테고리</th>
				<td width="70">
					<select name="board-category">
						<c:forEach var="c" items="${caList }">
							<option value="${c.categoryNo }">${c.categoryName }</option>
						</c:forEach>
					</select>
				</td>
				<th width="70">제목</th>
				<td width="350"><input type="text" name="board-title" value="${b.boardTitle }" required>
				</td>
			</tr>
		
			<!-- 내용 -->
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="8" style="resize:none;" name="board-content" required>${b.boardContent }</textarea>
				</td>
			</tr>
			
			<!-- 첨부파일 -->
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<c:if test="${at !=null }">
						${at.fileName }
					<input type="hidden" name="originFileNo" value="${at.fileNo }">
					<input type="hidden" name="originFileName" value="${at.changeName }">
					</c:if>
					<input type="file" name="reUploadFile">
				</td>
			</tr>
		</table>
		<br><br>
		<div align="center">
			<button type="submit">수정하기</button>
			<button type="button" onclick='location.href="${contextPath}/list.bo?currentPage=1"'>취소</button>
			<!-- 취소 클릭시 listView 로 보내기  -->
		</div>
	</form>
	</div>
	
	<script>
		//게시글 수정을 눌렀을때 기존에 있던 카테고리명 유지하기
		$(function(){
			
			var choosed = "${b.categoryName}" //기존에 선택 돼 있던
			//console.log(choosed);
			//반복 돌면서 기존이랑 일치하는지 판별 후 지정
			$('#board-update option').each(function(){
				//console.log($(this).text());
				if($(this).text()==choosed){
					$(this).attr('selected',true);
					return false;
				}
			});
			
			
		});
	
	
	</script>
	

</body>
</html>