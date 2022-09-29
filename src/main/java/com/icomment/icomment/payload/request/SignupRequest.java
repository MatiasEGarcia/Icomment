package com.icomment.icomment.payload.request;

import java.util.List;
import java.util.Objects;

public class SignupRequest {

    private String username;
    private String email;
    private String password;
    private List<String> roles;
    
	public SignupRequest() {
	}


	public SignupRequest(String username, String email, String password, List<String> roles) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password, roles, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignupRequest other = (SignupRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(roles, other.roles) && Objects.equals(username, other.username);
	}


	@Override
	public String toString() {
		return "SignupRequest [username=" + username + ", email=" + email + ", password=" + password + ", roles="
				+ roles + "]";
	}
  
    
    
    
}
