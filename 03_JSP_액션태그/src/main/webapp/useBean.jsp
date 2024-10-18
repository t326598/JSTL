<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>useBean</title>
</head>
<body>
<!-- person 이라는 이름으로 Person 객체 생성 -->
<jsp:useBean id="person" class="beans.Person"></jsp:useBean>

<h3>이름 : <%= person.getName() %></h3>
<h3>나이 : <%= person.getAge() %></h3>
<hr>

<h3>이름 : <jsp:getProperty property="name" name="person" /> </h3>
<h3>나이 : <jsp:getProperty property="age" name="person" /> </h3>
<hr>

<jsp:setProperty property="name" name="person" value="홍길동"/>
<jsp:setProperty property="age" name="person" value="30"/>
<h3>이름 : <jsp:getProperty property="name" name="person" /> </h3>
<h3>나이 : <jsp:getProperty property="age" name="person" /> </h3>
<hr>

</body>
</html>