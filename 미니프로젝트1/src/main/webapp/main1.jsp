<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_market", "username", "password");
    String sql = "SELECT * FROM posts ORDER BY created_at DESC";
    PreparedStatement stmt = conn.prepareStatement(sql);
    ResultSet rs = stmt.executeQuery();
%>

<h1>게시글 목록</h1>
<ul>
<% while (rs.next()) { %>
    <li>
        <h2><%= rs.getString("title") %></h2>
        <p>작성자: <%= rs.getString("writer") %></p>
        <p>주제: <%= rs.getString("type") %></p>
        <img src="<%= rs.getString("image_path") %>" alt="이미지" style="max-width: 200px;">
        <p><%= rs.getString("content") %></p>
        <hr>
    </li>
<% } %>
</ul>