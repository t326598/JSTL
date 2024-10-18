<%@page import="shop.Service.UserServiceImpl"%>
<%@page import="shop.Service.UserService"%>
<%@page import="shop.DTO.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String username = request.getParameter("username");
String password = request.getParameter("password");
String name = request.getParameter("name");
String email = request.getParameter("email"); // email 변수명 수정

// Lombok의 빌더 패턴을 사용하여 Users 객체 생성 
Users user = Users.builder()
					.username(username)
					.password(password)
					.name(name)
					.email(email) // email 메서드 수정
					.enabled(true)
					.build();

UserService userService = new UserServiceImpl();
int result = userService.signup(user);

if(result > 0) {
	response.sendRedirect("index.jsp");
} else {
	response.sendRedirect("signup.jsp?error=0");
}
%>