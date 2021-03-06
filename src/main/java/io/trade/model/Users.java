package io.trade.model;

import javax.persistence.*;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String userName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "enabled", nullable = false)
	private int enabled;
	
	
	
	public Users(String userName, String password, int enabled) {
		super();
		this.userName = userName;
		this.password = password;
		if(enabled == 1)
			this.enabled = enabled;
		else
			this.enabled = 0;
	}

	public Users() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		if(enabled == 1)
			this.enabled = enabled;
		else
			this.enabled = 0;
	}
	
	
}
