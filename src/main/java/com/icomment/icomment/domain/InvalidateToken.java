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
}
