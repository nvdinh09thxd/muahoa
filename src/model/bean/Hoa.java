package model.bean;

public class Hoa {
	int id;
	String tenHoa;
	int soLuong;
	float giaBan;
	String hinhAnh;
	String moTa;
	int id_loaihoa;

	public float getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenHoa() {
		return tenHoa;
	}

	public void setTenHoa(String tenHoa) {
		this.tenHoa = tenHoa;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public int getId_loaihoa() {
		return id_loaihoa;
	}

	public void setId_loaihoa(int id_loaihoa) {
		this.id_loaihoa = id_loaihoa;
	}

	public Hoa(int id, String tenHoa, int soLuong, float giaBan, String hinhAnh, String moTa, int id_loaihoa) {
		super();
		this.id = id;
		this.tenHoa = tenHoa;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.hinhAnh = hinhAnh;
		this.moTa = moTa;
		this.id_loaihoa = id_loaihoa;
	}

	public Hoa() {
		super();
	}

}
