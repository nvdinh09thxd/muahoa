package model.bean;

public class Users {
	private int id;
	private String username;
	private String password;
	private String avatar;
	private String fullName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Users(int id, String username, String password, String avatar, String fullName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
		this.fullName = fullName;
	}

	public Users() {
		super();
	}

}
