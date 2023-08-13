package com.brainmentors.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.brainmentors.chatapp.dto.UserDTO;
import com.brainmentors.chatapp.utils.Encryption;

// USER CURD
public class UserDAO {

	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception  {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String SQL = "select userid from users where userid=? and password=?";
		try {
			con = CommonDAO.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String encryptedPwd = Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2, encryptedPwd);
			rs = pstmt.executeQuery();
			return rs.next();
//			if(rs.next()) {
//				return true;
//			}
//			else {
//				return false;
//			} 
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
	
	//public int add(String userid, String password, byte age, String city, String phone, String email, String country, String state, Sring areaCpode, String stdCode) {
		public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
			System.out.println("Rec "+userDTO.getUserid()+" "+userDTO.getPassword());
			Connection connection = null;
			Statement stmt = null; //query
			try {
			connection = CommonDAO.createConnection(); //Step-1 Connection Create
			//Step-2 We do Query
			stmt = connection.createStatement();
			// insert into users(userid, password) values('ram','ram123');
			int record = stmt.executeUpdate("insert into users (userid, password) values('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')"); //Insert, Delete, Update
			return record;
			}
			finally { // Always Ececute
				if(stmt!=null) {
			stmt.close();
				}
				if(connection!=null) {
			connection.close();
				}
			}
			
		}
}

 