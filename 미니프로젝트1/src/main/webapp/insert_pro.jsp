<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    
    // success 파라미터가 있을 경우 성공 메시지 출력
    if (request.getParameter("success") != null) {
%>
        <h1>게시글 등록 성공!</h1>
<%
    }
    
    // error 파라미터가 있을 경우 실패 메시지 출력
    if (request.getParameter("error") != null) {
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

<!-- JSTL을 이용한 게시글 목록 표시 -->
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

</body>
</html>