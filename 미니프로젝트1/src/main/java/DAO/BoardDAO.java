package DAO;

import DTO.Board;

public class BoardDAO extends JDBConnection {
	
	public int insert(Board index) {
		int result = 0;
		
		String sql = " INSERT INTO board(title,category,price,status,content,uuid)"
				+ " VALUES( ?,?,?,?,?,?)";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, index.getTitle());
			psmt.setString(2, index.getCategory());
			psmt.setInt(3, index.getPrice());
			psmt.setInt(4, index.getStatus());
			psmt.setString(5, index.getContent());
			psmt.setInt(6, index.getUuid());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("BoardDAO : 보드 등록 시,예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}