<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MOOSA 소통해요</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
.list-area>tbody>tr, .search-area>tbody>tr:hover {
	cursor: pointer;
}
</style>


</head>
<body>

	<%@include file="/views/common/header.jsp"%>

	<div class="board-outer" style="margin-left: 10%; margin-right: 10%;">
		<h2 align="center"
			style="color: #413E3E; font-family: 'Arvo'; font-weight: 700;">소통게시판</h2>
		<br>

		<div align="center">

			<a href="${contextPath }/insert.bo?currentPage="
				class="btn btn-primary">글작성</a>
		</div>

		<div class="category-area">
			<select id="categoreNo"
				style="font-size: 15px; font-family: 'Arvo'; border-radius: 5px; border: 1px solid #413E3E;">
				<option>카테고리별 정렬</option>
				<option value="1">잡담</option>
				<option value="2">숙소추천</option>
				<option value="3">관광지추천</option>
				<option value="4">코스추천</option>
				<option value="5">질문</option>
			</select>
		</div>

		<br>

		<table class="list-area" align="center" border="1">
			<thead>
				<tr>
					<th width="50">글번호</th>
					<th width="50">카테고리</th>
					<th width="50">제목</th>
					<th width="50">작성일</th>
					<th width="50">작성자</th>
					<th width="50">조회수</th>
					<th width="50">좋아요</th>
				</tr>
			</thead>

			<tbody>
				<c:choose>
					<c:when test="${empty list }">
						<tr>
							<td>조회된 게시글이 없습니다.</td>
						</tr>
					</c:when>

					<c:otherwise>
						<%-- 가져온 리스트 반복문으로 넣을예정 --%>
						<c:forEach var="b" items="${list }">
							<tr>
								<td>${b.boardNo }</td>
								<td>${b.categoryName }</td>
								<td>${b.boardTitle }</td>
								<td>${b.createDate }</td>
								<td>${b.boardWriter }</td>
								<td>${b.count }</td>
								<td>${b.likes }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
<a href="${contextPath }/list.bo?currentPage=1" class="btn btn-secondary">글목록</a>

	</div>

	<script>
		$(function() { //게시글 상세보기로
			$(".list-area>tbody>tr").click(function() {
				var bno = $(this).children().first().text();
				location.href = "detail.bo?bno=" + bno;
			});
		});
	</script>

	<br>
	<br>
	
	<!-- ===================================== 페이징 바 ======================================-->

<div align="center" class="pagingBar-area">

		<!-- currentPage 가 1이면 이전버튼 disabled -->
		<c:choose>
			<c:when test="${bp.currentPage eq 1}">
				<button class="btn btn-secondary" disabled>이전</button>
			</c:when>
			<c:otherwise>
				<button class="btn btn-secondary"
					onclick='location.href="${contextPath }/list.bo?currentPage=1"'>이전</button>
			</c:otherwise>
		</c:choose>
		
		<!-- 페이징 번호 begin ~ end 까지 불러오기 -->
		<c:forEach var="p" begin="${bp.startPage }" end="${bp.endPage }">
			<button class="btn btn-secondary"
				onclick="location.href='${contextPath}/list.bo?currentPage=${p}'">${p}</button>
		</c:forEach>

		<c:choose>
			<c:when test="${bp.endPage eq bp.maxPage }">
				<button class="btn btn-secondary" disabled>다음</button>
			</c:when>
			<c:otherwise>
				<!-- 수정해야함 -->
				<button class="btn btn-secondary"
					onclick="location.href='${contextPath}/list.bo?currentPage=${bp.endPage +1}'">다음</button>
			</c:otherwise>
		</c:choose>
	</div>
	

	<br>
	<br>
	
	

	<!-- ======================================= 검색창 ====================================-->

	<div class="search-area" align="center">
		<select id="searchOption" name="searchOption">
			<option value="1" selected>제목</option>
			<option value="2">내용</option>
			<option value="3">아이디</option>
		</select> <input type="text" id="searchContent">
		<button class="btn btn-success" id="searchBtn" type="button">검색</button>
	</div>

	<br>
	<br>

	<!-- Footer -->
	<%@include file="/views/common/footer.jsp"%>

	<script>
		//검색창 입력 후 Enter 누르면 검색버튼 클릭효과
		$('#searchContent').on('keydown', function(event) {
			if (event.which === 13) {
				$('#searchBtn').click();
			}
		});

		// ========== 카테고리별 정렬 =============
		$(function() {
			$('#categoreNo').on(
					'change',
					function() {
						var categoryNo = $(this).val();
						console.log(categoryNo);
						location.href = '/moosa/category.bo?categoryNo='
								+ categoryNo + '&currentPage=1';
					});
		});

		// ==========  검색창 옵션선택 (기본)============

		$(function() {
			$('#searchOption').on('change', function() {
				var searchOption = $(this).val();
			});
			$('#searchOption').trigger('change');
		});

		$(function() {
			$('#searchBtn').on(
					'click',
					function() {
						var searchContent = $('#searchContent').val();
						var searchOption = $('#searchOption').val();

						location.href = '/moosa/search.bo?searchOption='
								+ searchOption + '&searchContent='
								+ searchContent + '&currentPage=1';

					});
		});

		/* function boardSearch({
			var searchContent = $('#searchContent').val();
			var searchOption = $('#searchOption').val();
			
			location.href = '/moosa/search.bo?searchOption='+searchOption+'&searchContent='+searchContent+'&currentPage=1';

		}); */

		/* ==========  검색창 옵션선택 (비동기식)============
		
			$(function(){ //옵션선택(제목,내용,아이디)
				
				$('#searchOption').on('change',function(){
					var searchOption = $(this).val();
				});
				$('#searchOption').trigger('change');
			});
			
			function boardSearch(){ //검색창에 입력한 내용
				var searchContent = $('#searchContent').val();
				var searchOption = $('#searchOption').val();
				
				   $.ajax({
					url : "/moosa/search.bo",
					data : {
						searchContent : searchContent,
						searchOption : searchOption
					},
					success : function(result){
						
						console.log(result);
						
					    if(result==""){
							alert("검색 결과가 없습니다 ㅠㅠ")
						}
					    
					    var str = "";
					    for(var i=0; i<result.length; i++){
							str += "<tr>"
									+ "<td>"+ result[i].boardNo +"</td>"
									+ "<td>"+ result[i].categoryName +"</td>"
									+ "<td>"+ result[i].boardTitle+ "</td>"
									+ "<td>"+ result[i].createDate +"</td>"
									+ "<td>"+ result[i].userId +"</td>"
									+ "<td>"+ result[i].count +"</td>"
									+ "<td>"+ result[i].likes +"</td>"
									+"</tr>";
						}
					    $('.search-area tbody').html(str);
					    
					    $(".search-area>tbody>tr").click(function(){ //상세보기가 안됨 ㅠㅠ
							var bno = $(this).children().first().text();
							location.href="detail.bo?bno="+bno;
						});
						console.log('검색할 내용 보내기 성공!!')
					},
					error : function(){
						console.log('검색할 내용 보내기 실패 ㅠㅠ')
					}
				}); 
			}
			
		 */
	</script>

</body>
</html>
















