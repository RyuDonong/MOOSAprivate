<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>계절 코스 선택</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .container {
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
        }
        .buttons {
            display: flex; 
            flex-wrap: wrap; 
            justify-content: center; 
            gap: 40px; 
        }

        h1 {
            margin-bottom: 20px;
        }

        .book {
            position: relative;
            border-radius: 10px;
            width: 220px;
            height: 300px;
            background-color: whitesmoke;
            -webkit-box-shadow: 1px 1px 12px #faf8f8;
            box-shadow: 1px 1px 12px #f8f8f8;
            -webkit-transform: preserve-3d;
            -ms-transform: preserve-3d;
            transform: preserve-3d;
            -webkit-perspective: 2000px;
            perspective: 2000px;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
            color: #000;
            cursor: pointer; /* 마우스 커서를 포인터로 변경 */
        }

        .cover {
            top: 0;
            position: absolute;
            background-color: lightgray;
            width: 100%;
            height: 100%;
            border-radius: 10px;
            cursor: pointer;
            -webkit-transition: all 0.5s;
            transition: all 0.5s;
            -webkit-transform-origin: 0;
            -ms-transform-origin: 0;
            transform-origin: 0;
            -webkit-box-shadow: 1px 1px 12px #000;
            box-shadow: 1px 1px 12px #000;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
        }

        .book:hover .cover,
        .book.clicked .cover { /* 클릭 상태일 때도 .cover가 보여지도록 추가 */
            -webkit-transition: all 0.5s;
            transition: all 0.5s;
            -webkit-transform: rotatey(-80deg);
            -ms-transform: rotatey(-80deg);
            transform: rotatey(0deg);
        }

        .description {
            position: absolute;
            bottom: 0;
            background-color: rgba(11, 25, 151, 0.7);
            color: white;
            width: 100%;
            height: 100%;
            padding: 10px;
            box-sizing: border-box;
            transition: opacity 0.3s, transform 0.3s;
            opacity: 0;
            transform: translateY(100%);
            border-radius: 10px; 
        }

        .book.clicked .description {
            opacity: 1;
            transform: translateY(0%);
        }

        p {
            font-size: 20px;
            font-weight: bolder;
            color: white;
        }
        a{
            color: #f8f8f8;
        }
        #summer{
            background: url(https://wimg.mk.co.kr/meet/neds/2022/06/image_readtop_2022_557782_16562167665086621.jpg)
        }

        #spring{
            background: url(https://i.pinimg.com/736x/fc/e0/1f/fce01ff9b49c54eb88609b19c8db2703.jpg);
        }
        #autumn{
            background: url(https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20120910_266%2Fhan_na0401_1347281920612inx7l_JPEG%2F%25A4%25AC%25A4%25B7%25A4%25BD%25A4%25B7%25A4%25BD%25A4%25B7%25A4%25BD_%25285%2529.jpg&type=sc960_832);
        }
        #winter{
            background: url(https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTAxMDJfMjMw%2FMDAxNjA5NTUwNzgwNTUw.Li9l8NxxdXJZnljgrYKaMDfdoE74Yk-S6Q9mJEjES-kg.CYwbektayp77QjjZN1Em6c8xwZkXBeio8TIteTLVvXMg.GIF.asw8766%2F%25B0%25DC%25BF%25EF%25B4%25AB_13.gif&type=sc960_832_gif);
        }
    </style>
   
</head>

<body>
    <%@include file = "/views/common/header.jsp"%>
     
    <div class="container">
        <h1>계절을 선택하세요</h1>
            
        <div class="buttons">
            <div class="book" onclick="toggleDescription(this)">
                <button    class="season-button" onclick="changeSeason('spring')">봄</button>
                <div id="spring" class="cover" onclick="changeSeason('spring')">
                    <p>봄</p>
                </div>
                <div class="description">
                    <p>봄은 화창하고 따뜻한 계절입니다. 꽃이 피고 새싹이 돋아나는 시기로, 새로운 시작을 의미합니다.</p>
                    <p>#꽃 #따뜻함 #새싹 </p>
                    
                    <a href="${contextPath}/course.sp">바로가기</a>
                </div>
            </div>
            
            <div class="book" onclick="toggleDescription(this)">
                <button  class="season-button" onclick="changeSeason('summer')">여름</button>
                <div id="summer" class="cover" onclick="changeSeason('summer')">
                    <p>여름</p>
                </div>
                <div class="description">
                    <p>여름은 덥고 활기찬 계절입니다. 해변에서 즐거운 시간을 보내거나 시원한 음료를 즐기기 좋습니다.</p>
                    <p>#해변  #더위  #시원함 </p>
                    
                    <a href="${contextPath}/course.su">바로가기</a>
                </div>
            </div>
            
            <div class="book" onclick="toggleDescription(this)">
                <button class="season-button" onclick="changeSeason('autumn')">가을</button>
                <div id="autumn" class="cover" onclick="changeSeason('autumn')">
                    <p>가을</p>
                </div>
                <div class="description">
                    <p>가을은 서늘하고 색이 아름다운 계절입니다. 단풍 구경을 하거나 따뜻한 차를 마시기 좋습니다.</p>
                    <p>#단풍 #산책 #차</p>
                    <br>
                    <a href="${contextPath}/course.au">바로가기</a>
                </div>
            </div>
            
            <div class="book" onclick="toggleDescription(this)">
                <button class="season-button" onclick="changeSeason('winter')">겨울</button>
                <div id="winter" class="cover" onclick="changeSeason('winter')">
                    <p>겨울</p>
                </div>
                <div class="description">
                    <p>겨울은 차가운 계절입니다. 눈이 내리는 풍경을 감상하거나 온기를 느끼는 모임이 어울립니다.</p>
                    <p>#눈 #추위  #온기</p>
                    <br>
                    <a href="${contextPath}/course.wi">바로가기</a>
                </div>
            </div>
        </div>
        
       
    </div>
	<%@include file = "/views/common/footer.jsp"%>
    <script>
        function changeSeason(season) {
            const body = document.body;
            const text = document.getElementById('season-text');
            switch (season) {
                case 'spring':
                    
                    break;
                case 'summer':
                    
                    break;
                case 'autumn':
                    
                    break;
                case 'winter':
                    
                    break;
            }
        }

        function toggleDescription(book) {
            book.classList.toggle('clicked');
        }
    </script>
</body>
</html>