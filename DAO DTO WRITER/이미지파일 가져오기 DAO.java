import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardDAO {
    // 게시글 저장 메서드
    public int saveBoard(BoardDTO board) {
        String sql = "INSERT INTO board (title, writer) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getWriter());
            pstmt.executeUpdate();

            // 생성된 board_id를 반환
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // 생성된 board_id 반환
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 실패 시
    }

    // 게시글과 파일 정보를 함께 가져오는 메서드
    public BoardDTO getBoardWithFiles(int boardId) {
        String sql = "SELECT b.title, b.writer, f.file_name, f.file_path " +
                     "FROM board b LEFT JOIN file f ON b.board_id = f.board_id " +
                     "WHERE b.board_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, boardId);
            ResultSet rs = pstmt.executeQuery();

            BoardDTO board = null;
            while (rs.next()) {
                if (board == null) {
                    board = new BoardDTO();
                    board.setTitle(rs.getString("title"));
                    board.setWriter(rs.getString("writer"));
                }

                // 파일 정보를 DTO에 저장 (필요시 파일 관련 정보를 처리하는 로직 추가)
                FileDTO file = new FileDTO();
                file.setFileName(rs.getString("file_name"));
                file.setFilePath(rs.getString("file_path"));

                // 파일 정보를 별도로 리스트에 저장할 수 있음 (예: List<FileDTO> 사용)
            }
            return board;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


//FILEDAO
public class FileDAO {
    public void saveFile(FileDTO file) {
        String sql = "INSERT INTO file (board_id, file_name, file_path) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, file.getBoardId());
            pstmt.setString(2, file.getFileName());
            pstmt.setString(3, file.getFilePath());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
