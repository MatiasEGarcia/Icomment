package com.icomment.icomment.payload.request;

public class LogoutRequest {
	
	private String accessToken;
	private String refreshToken;
	
	public LogoutRequest() {
		super();
	}
	public LogoutRequest(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	@Override
	public String toString() {
		return "LogoutRequest [accessToken=" + accessToken + ", refreshToken=" + refreshToken + "]";
	}
}
