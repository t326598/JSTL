

// PostDAO.java
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PostDAO {
    // 게시글 저장 메서드
    public void savePost(PostDTO post) {
        String sql = "INSERT INTO posts (title, content, writer) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setString(3, post.getWriter());  // writer 정보 저장
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


<!-- createPost.jsp -->
<form action="createPost.do" method="post">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required>

    <label for="content">Content:</label>
    <textarea id="content" name="content" required></textarea>

    <button type="submit">Submit</button>
</form>

// CreatePostServlet.java
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/createPost.do")
public class CreatePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼 데이터 받아오기
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // 세션에서 로그인한 사용자 정보 가져오기
        HttpSession session = request.getSession();
        String writer = (String) session.getAttribute("userId");  // 로그인한 사용자 ID

        // DTO에 데이터 설정
        PostDTO post = new PostDTO();
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);  // writer 설정

        // DAO를 사용하여 데이터베이스에 저장
        PostDAO postDAO = new PostDAO();
        postDAO.savePost(post);

        // 게시글 작성 후 리다이렉트
        response.sendRedirect("postList.do");
    }
}
