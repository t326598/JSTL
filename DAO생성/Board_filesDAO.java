import DAO.JDBConnection;

public class Board_filesDAO extends JDBConnection {

    public int insertBoard_File(User user){
        
        String sql = "INSERT INTO user_files (file_name, file_path, board_no, reg_date, upd_date) VALUES (?, ?, ?, ?, ?)";
        try {
            psmt = con.PreparedStatement(sql);
            psmt.setString(1, user.getFile_name);
            psmt.setString(2, user.getFile_path);
            psmt.setString(3, user.getBoard_no);
            psmt.setString(4, new java.sql.Date(user.getReg_date().getTime()));
            psmt.setString(5, new java.sql.Date(user.getUeg_date().getTime()));

            result = psmt.executeUpdate();
        } catch (Exception e) {
           System.err.println("게시판 파일 등록시 예외 발생");
           e.printStackTrace();
        }
        return result;
    }
    
}
