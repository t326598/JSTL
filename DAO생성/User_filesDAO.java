package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.JDBConnection;
import dto.User_files;
import java.util.Date;

public class User_filesDAO extends JDBConnection {

    public int insertUserFile(User user){
        String sql = "INSERT INTO user_files (file_name, file_path, parent_no, reg_date, upd_date) VALUES (?, ?, ?, ?, ?)";
        try  {
            psmt = con.PreparedStatement(sql);
            pstmt.setString(1, user.getFile_name());
            pstmt.setString(2, user.getFile_path());
            pstmt.setInt(3, user.getParent_no());
            pstmt.setDate(4, new java.sql.Date(user.getReg_date().getTime()));
            pstmt.setDate(5, new java.sql.Date(user.getUpd_date().getTime()));
            
            result = psmt.executeUpdate();

        } catch (Exception e) {
            System.err.println(" 유저 파일 등록시 예외 발생");           
            e.printStackTrace();
        }

                   return result;
                }

 
}