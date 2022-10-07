package com.icomment.icomment.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "invalidate_tokens", schema = "icomment")
public class InvalidateToken implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idInvToken;
	
	@Column(name="token")
    private String token;
	
	@Column(name="type")
    private String type;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="invalidate_date")
    private Date invalidateDate;
	
	public InvalidateToken() {
		super();
	}

	public InvalidateToken(String token, String type, Date invalidateDate) {
		super();
		this.token = token;
		this.type = type;
		this.invalidateDate = invalidateDate;
	}

	public InvalidateToken(Long idInvToken, String token, String type, Date invalidateDate) {
		super();
		this.idInvToken = idInvToken;
		this.token = token;
		this.type = type;
		this.invalidateDate = invalidateDate;
	}

	public Long getIdInvToken() {
		return idInvToken;
	}

	public void setIdInvToken(Long idInvToken) {
		this.idInvToken = idInvToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getInvalidateDate() {
		return invalidateDate;
	}

	public void setInvalidateDate(Date invalidateDate) {
		this.invalidateDate = invalidateDate;
	}

	@Override
	public String toString() {
		return "InvalidateToken [idInvToken=" + idInvToken + ", token=" + token + ", type=" + type + ", invalidateDate="
				+ invalidateDate + "]";
	}
	
	
	
	
}
