package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Hoa;

public class HoaDAO {
	private static Connection conn;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;

	public static ArrayList<Hoa> getListHoa() {
		conn = ConnectDBLibrary.getConnection();
		String sql = "SELECT * FROM hoa INNER JOIN loai_hoa on hoa.id_loaihoa=loai_hoa.id";
		ArrayList<Hoa> listHoa = new ArrayList<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Hoa ObjHoa = new Hoa(rs.getInt("id"), rs.getString("ten_hoa"), rs.getInt("so_luong"),
						rs.getString("hinh_anh"), rs.getString("mo_ta"), rs.getInt("id_loaihoa"));
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
		String sql = "SELECT * FROM hoa WHERE id="+id;
		Hoa ItemHoa = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				ItemHoa = new Hoa(rs.getInt("id"), rs.getString("ten_hoa"), rs.getInt("so_luong"),
						rs.getString("hinh_anh"), rs.getString("mo_ta"), rs.getInt("id_loaihoa"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(rs, st, conn);
		}
		return ItemHoa;
	}

	public static int delItem(int id) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "DELETE FROM hoa WHERE id="+id;
		try {
			st = conn.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(st, conn);
		}
		return result;
	}
	
	public static int addItem(Hoa hoa) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "INSERT INTO hoa VALUES (?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, hoa.getId());
			pst.setString(2, hoa.getTenHoa());
			pst.setInt(3, hoa.getSoLuong());
			pst.setString(4, hoa.getHinhAnh());
			pst.setString(5, hoa.getMoTa());
			pst.setInt(6, hoa.getId_loaihoa());
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
			System.out.println(objCat.getTenHoa()+" "+objCat.getSoLuong());
		}
	}
	public static int editItem(Hoa hoa) {
		int result = 0;
		conn = ConnectDBLibrary.getConnection();
		String sql = "UPDATE hoa SET ten_hoa = ?, so_luong = ?, hinh_anh = ?, mo_ta = ?, id_loaihoa = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, hoa.getTenHoa());
			pst.setInt(2, hoa.getSoLuong());
			pst.setString(3, hoa.getHinhAnh());
			pst.setString(4, hoa.getMoTa());
			pst.setInt(5, hoa.getId_loaihoa());
			pst.setInt(6, hoa.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBLibrary.close(rs, pst, conn);
		}
		return result;
	}
}
