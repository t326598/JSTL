<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글 상세페이지</title>
    <script src="js/jquery-3.7.1.min.js"></script>
    <script src="js/insert.js"></script>
    <style type="text/css">
        .input-content {
            border: 6px solid black;
            width: 100%;
            padding: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="title-box">
            <h1 class="main-title">글상세페이지</h1>

            <!-- 폼 액션을 서블릿으로 설정 -->
            <form action="uploads" method="post" enctype="multipart/form-data">
                <div class="input-group">
                    <label for="title">제목 :</label> 
                    <input type="text" name="title" placeholder="제목을 입력하세요" id="title" required> 
                    <label for="type">주제 :</label> 
                    <select name="type" id="type">
                        <option value="1">전자/게임</option>
                        <option value="2">운동</option>
                        <option value="3">의류</option>
                        <option value="4">가구</option>
                        <option value="5">기타</option>
                    </select>
                </div>
                <div class="input-group">
                    <label for="writer">작성자 :</label> 
                    <input type="text" name="writer" placeholder="작성자를 입력하세요" id="writer" required>
                </div>

                <div class="input-group">
                    <label for="image">이미지 첨부:</label> 
                    <input type="file" name="image" id="image" accept="image/*">
                </div>

                <div class="input-content">
                    <div class="input-group">
                        <img id="uploadedImage" src="" style="max-width: 200px; height: auto;">
                    </div>

                    <div class="input-group">
                        <label for="content">내용:</label>
                        <textarea name="content" id="content" cols="30" rows="10" style="width: 100%;"></textarea>
                    </div>

                    <div class="button">
                        <input type="submit" id="insert" value="등록하기" /> 
                        <input type="button" id="main" onclick="location.href='insert.jsp';" value="목록으로" />
                    </div>
                </div>
            </form>
        </div>

        <!-- 성공/실패 메시지 출력 -->
        <%
            if (request.getParameter("success") != null) {
        %>
            <h1>게시글 등록 성공!</h1>
        <%
            } else if (request.getParameter("error") != null) {
        %>
            <h1>게시글 등록 실패. 다시 시도해주세요.</h1>
        <%
            }
        %>

        <!-- 데이터 -->
        <sql:setDataSource var="dataSource" 
            url="jdbc:mysql://localhost:3306/jsp_market?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false"
            driver="com.mysql.cj.jdbc.Driver"
            user="kimdo"
            password="123456" 
        />

        <!-- 게시글 목록 표시 -->
        <sql:query dataSource="${dataSource}" var="posts">
            SELECT * FROM posts
        </sql:query>

        <!-- 게시글 목록 출력 -->
        <c:forEach var="post" items="${posts.rows}">
            <h2>${post.title}</h2>
            <p>작성자: ${post.writer}</p>
            <p>${post.content}</p>
            <img src="${post.img_path}" alt="게시글 이미지" />
        </c:forEach>
    </div>

    <script>
        document.getElementById('image').addEventListener('change', function(event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById('uploadedImage').src = e.target.result;
                }
                reader.readAsDataURL(file); // 파일을 읽어 미리보기로 표시
            }
        });
    </script>
</body>
</html>