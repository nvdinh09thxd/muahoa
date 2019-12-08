package model.bean;

public class LoaiHoa {
	int id;
	String tenLoaiHoa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenLoaiHoa() {
		return tenLoaiHoa;
	}

	public void setTenLoaiHoa(String tenLoaiHoa) {
		this.tenLoaiHoa = tenLoaiHoa;
	}

	public LoaiHoa(int id, String tenLoaiHoa) {
		super();
		this.id = id;
		this.tenLoaiHoa = tenLoaiHoa;
	}

	public LoaiHoa() {
		super();
	}

}
