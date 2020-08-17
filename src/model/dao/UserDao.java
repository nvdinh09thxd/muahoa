package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.Users;
import util.ConnectDBLibrary;

public class UserDao {
	private static Connection conn;
	private static PreparedStatement pst;
	private static ResultSet rs;

	public Users getItem(String username, String password) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		Users item = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				item = new Users(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("avatar"), rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(rs, pst, conn);
		}
		return item;
	}
}
