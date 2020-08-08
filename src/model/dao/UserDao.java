package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Users;
import util.ConnectDBLibrary;

public class UserDao {
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;

	public ArrayList<Users> getItems() {
		ArrayList<Users> listItems = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		try {
			String sql = "SELECT * FROM users";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Users user = new Users(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("avatar"), rs.getString("fullname"));
				listItems.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(rs, st, conn);
		}
		return listItems;
	}
}
