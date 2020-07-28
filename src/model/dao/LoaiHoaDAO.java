package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.LoaiHoa;

public class LoaiHoaDAO {
	private static Connection conn;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;

	public static ArrayList<LoaiHoa> getListCat() {
		ArrayList<LoaiHoa> listCat = new ArrayList<>();
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM loaihoa";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				LoaiHoa objCat = new LoaiHoa(rs.getInt("id"), rs.getString("ten_loaihoa"));
				listCat.add(objCat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(rs, st, conn);
		}
		return listCat;
	}

	public static LoaiHoa getItemCatByIdHoa(int id) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM hoa AS a JOIN loaihoa AS b on a.id_loaihoa=b.id WHERE a.id = ?";
		LoaiHoa ItemCat = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				ItemCat = new LoaiHoa(rs.getInt("b.id"), rs.getString("b.ten_loaihoa"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(rs, st, conn);
		}
		return ItemCat;
	}

	public static int addItem(LoaiHoa item) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO loaihoa (ten_loaihoa) VALUES (?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getTenLoaiHoa());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(rs, pst, conn);
		}
		return result;
	}

	public static void main(String[] args) {
		// LoaiHoa ItemCat = LoaiHoaDAO.getItemCat(2);
		// for(LoaiHoa objCat: listCat) {
		// System.out.println(ItemCat.getId() + " - " + ItemCat.getTenLoaiHoa());
		// }
	}
}
