package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/uploads")
public class Upload extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String UPLOAD_DIRECTORY = "uploads";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		
		String title = request.getParameter("title");
		String type = request.getParameter("type");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Part filePart = request.getPart("image");
		if(filePart == null || filePart.getSize() == 0) {
			response.getWriter().println("파일이 업로드되지 않았습니다.");
			return;
		}
		
		String fileName = extractFileName(filePart);
		
		String filePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(filePath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		String imageFilePath = filePath + File.separator + fileName;
		filePart.write(imageFilePath);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_market", "username", "password");
			
			String sql = "INSERT INTO posts (title, type, writer, content, img_path) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, title);
			statement.setString(2, type);
			statement.setString(3, writer);
			statement.setString(4, content);
			statement.setString(5, "uploads/" + fileName);
			
			int result = statement.executeUpdate();
			
			if(result > 0) {
				response.sendRedirect("insert.jsp?success=1");
			}
			else {
				response.sendRedirect("insert.jsp?error=0");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		private String extractFileName(Part part) {
			String contentDisp = part.getHeader("content-disposition");
			for(String cd : contentDisp.split(";")) {
				if(cd.trim().startsWith("filename")) {
					return cd.substring(cd.indexOf("=") + 2, cd.length() -1);
					
				}
			}
			return null;
	
	}

}
