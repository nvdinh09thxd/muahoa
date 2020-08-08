package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Hoa;
import util.ConnectDBLibrary;

public class HoaDAO {
	private static Connection conn;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;

	public static ArrayList<Hoa> getListHoa() {
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM hoa";
		ArrayList<Hoa> listHoa = new ArrayList<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Hoa ObjHoa = new Hoa(rs.getInt("id"), rs.getString("ten_hoa"), rs.getInt("so_luong"),
						rs.getFloat("gia_ban"), rs.getString("hinh_anh"), rs.getString("mo_ta"),
						rs.getInt("id_loaihoa"));
				listHoa.add(ObjHoa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(rs, st, conn);
		}
		return listHoa;
	}

	public static Hoa getItemHoa(int id) {
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM hoa WHERE id = ?";
		Hoa ItemHoa = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				ItemHoa = new Hoa(rs.getInt("id"), rs.getString("ten_hoa"), rs.getInt("so_luong"),
						rs.getFloat("gia_ban"), rs.getString("hinh_anh"), rs.getString("mo_ta"),
						rs.getInt("id_loaihoa"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(rs, pst, conn);
		}
		return ItemHoa;
	}

	public static int delItem(int id) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "DELETE FROM hoa WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(pst, conn);
		}
		return result;
	}

	public static int addItem(Hoa hoa) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO hoa VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, hoa.getId());
			pst.setString(2, hoa.getTenHoa());
			pst.setInt(3, hoa.getSoLuong());
			pst.setFloat(4, hoa.getGiaBan());
			pst.setString(5, hoa.getHinhAnh());
			pst.setString(6, hoa.getMoTa());
			pst.setInt(7, hoa.getId_loaihoa());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(rs, pst, conn);
		}
		return result;
	}

	public static void main(String[] args) {
		ArrayList<Hoa> listHoa = HoaDAO.getListHoa();
		for (Hoa objCat : listHoa) {
			System.out.println(objCat.getTenHoa() + " " + objCat.getSoLuong());
		}
	}

	public static int editItem(Hoa hoa) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE hoa SET ten_hoa = ?, so_luong = ?, gia_ban = ?, hinh_anh = ?, mo_ta = ?, id_loaihoa = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, hoa.getTenHoa());
			pst.setInt(2, hoa.getSoLuong());
			pst.setFloat(3, hoa.getGiaBan());
			pst.setString(4, hoa.getHinhAnh());
			pst.setString(5, hoa.getMoTa());
			pst.setInt(6, hoa.getId_loaihoa());
			pst.setInt(7, hoa.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(rs, pst, conn);
		}
		return result;
	}
}
