<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.list-area>tbody tr:hover{
		background: gray;
		cursor : pointer;
	}

</style>

</head>
<body>
	<%@include file = "/views/common/header.jsp"%>
	
	<div class="outer">
		<br>
		
		<h2 align="center">리뷰 게시판</h2>
		<br><br>
		<c:if test="${not empty loginUser }">
			<div align="center">
				<a href="${contextPath }/insert.rv" class="btn btn-info">글작성</a>
			</div>
		</c:if>
		<br>
		
		<table class="list-area" align="center" border="1">
			<thead>
				<tr>
					<th width="70">글 번호</th>
					<th width="70">계절</th>
					<th width="300">제목</th>
					<th width="100">작성자</th>
					<th width="50">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				
				<c:choose>
					<c:when test="${empty list }">
				
						<tr>
							<td colspan="6">조회된 리뷰가 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="b" items="${list}">
							<tr>
								<td>${b.boardNo}</td>
								<td>${b.category }</td>
								<td>${b.boardTitle }</td>
								<td>${b.boardWriter }</td>
								<td>${b.count }</td>
								<td>${b.createDate }</td>
							</tr>
						</c:forEach>
							
					</c:otherwise>
				</c:choose>
				
				
			
			</tbody>
			
		
		</table>
		
		
		<script>
			$(function(){
				
				$(".list-area>tbody>tr").click(function(){
					
					var bno = $(this).children().first().text();
					location.href="detail.rv?bno="+$(this).children().first().text();
					
				});
				
			});
		
		</script>
	
		<br><br>
		
		
		<div align="center" class="paging-area">
			
			
		<c:choose>
			<c:when test="${pi.currentPage eq 1}">
				<button disabled>이전</button>
			</c:when>
			<c:otherwise>
				<button onclick="location.href='list.rv?currentPage=${pi.currentPage-1}'">이전</button>
			</c:otherwise> 
		</c:choose>	
		
		<c:forEach var="i" begin="${pi.startPage }" end="${pi.endPage }">
			 <button onclick="location.href='list.rv?currentPage=${i}'">${i}</button>
		</c:forEach>
		<c:choose>
			<c:when test="${pi.currentPage eq pi.maxPage}">
				<button disabled>다음</button>
			</c:when>
			<c:otherwise>
				<button onclick="location.href='list.rv?currentPage=${pi.currentPage+5}'">다음</button>
			</c:otherwise> 
		</c:choose>	
		</div>
		
		<br><br>
		
	</div>
	
	<%@include file = "/views/common/footer.jsp"%>
</body>
</html>
