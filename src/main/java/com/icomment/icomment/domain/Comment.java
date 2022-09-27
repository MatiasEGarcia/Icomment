package com.icomment.icomment.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments", schema = "icomment")
public class Comment implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Long idComment;
    
    @Column(name="body")
    private String body;
    
    @Column(name="user_name")
    private String username;
    
    @Column(name="parent_Id")
    private Long parentId;
    
    @Column(name="create_At")
    private String createAt; //date type in bdd

	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, createAt, idComment, parentId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(body, other.body) && Objects.equals(createAt, other.createAt)
				&& Objects.equals(idComment, other.idComment) && parentId == other.parentId
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", body=" + body + ", username=" + username + ", parentId="
				+ parentId + ", createAt=" + createAt + "]";
	}

	    
}
