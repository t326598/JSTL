<%@page import="service.UserService"%>
<%@page import="service.UserServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="service.FilesServiceImpl"%>
<%@page import="service.FilesService"%>
<%@page import="dto.Files"%>
<%@page import="service.BoardServiceImpl"%>
<%@page import="dto.Board"%>
<%@page import="dto.User"%>
<%@page import="service.BoardService"%>
<%@page import="lombok.EqualsAndHashCode.Include"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
int boardNo = Integer.parseInt(request.getParameter("no"));

// BoardService를 통해 게시글의 상세 내용을 가져옴
BoardService boardService = new BoardServiceImpl();
	Board board = boardService.select(boardNo);
	
	FilesService filesService = new FilesServiceImpl();
	Files file = filesService.select(boardNo); // 단일 파일 객체 가져오기
 
    UserService user1 = new UserServiceImpl();
    User sellerUser = user1.select(board.getUuid());
    
	
%>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>판매글 등록하기</title>
    <script src="js/jquery-3.7.1.min.js"></script>
    <script src="js/insert.js"></script>
    <script src="hf.js"></script>
    <link rel="stylesheet" href="css/viewPage.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

    <div class="container">
        <div class="title-box">
    
            <h1 class="main-title" style="margin-top: 20px; font-size: 50px">판매글</h1>
            <br>
         
          <%
          User user=null;
          if(session.getAttribute("loginUser")!=null){
        	  user = (User) session.getAttribute("loginUser");
          
          if( user.getUuid() == board.getUuid()){
        		   %>
						<div class="button">
                       <input  value="수정하기" type="button" id="update" onclick="location.href='updatePage.jsp?no=<%=board.getNo() %>'" />
                <input type="button" id="delete" value="삭제하기" onclick="location.href='delete_pro.jsp?no=<%=board.getNo() %>'" />
            </div>  
		<%
          }
          }
          
          
		%>

				
    		
    	<label class="sellUser" >판매자: <%= sellerUser.getId() %></label>
    	<br><br>
         
            <!-- 폼 액션을 서블릿으로 설정, enctype 추가 -->
      
       
            <div class="inputed">
                    <div class="input-group">
                        <div class="title-box">
                        <h2 class="title" ><%= board.getTitle() %></h2>
                    </div>
                <br>
                	
                    
              <div class="content-box"  id="input_title">
                  
                        <label for="category">카테고리: <%= board.getCategory() %> &nbsp; &nbsp;&nbsp;&nbsp; </label>
                        

                        <label for="status"> <%= board.getStatus() == 1 ? "판매 중" : "판매 완료" %>  &nbsp; &nbsp;&nbsp;&nbsp;</label>
                        
						 <label>가격: <%= board.getPrice() %>원 &nbsp;&nbsp;&nbsp;&nbsp;</label>

                         </div>
                 
                        </div>
                        <br><br>
                      
                   <%if (file != null && file.getFile_path() != null) {  %>
        				<img id="uploadedImage" src="/04_jsp_market/img?no=<%= file.getNo() %>" style="max-width: 400px; height: auto;" />
				  	<%
				    } else {
				    %>
				        <p>이미지가 없습니다.</p>
				    <%
				    } 
				    %>
                    <br><br>
                    
                    <div class="input-group">
                        <pre for="content" id="content"><%= board.getContent() %> </pre>
                        </div>
                        
                        <!-- 신고버튼 생성 -->
                    </div>
               			<c:if test="${sessionScope.loginId != null}">
							
        	<div class="btn">
        		<input type="button" id="Declaration" onclick="location.href='declaration.jsp?no=<%=board.getNo() %>'" value="신고하기" />
        	</div>        
									</c:if>
        
                <div class="button">
                <input type="button" id="main" onclick="window.location.href='boardList.jsp';" value="목록으로" />
                <input type="submit" id="insert" value="채팅하기" />
            </div>      
        </div>  
        </div>



    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>