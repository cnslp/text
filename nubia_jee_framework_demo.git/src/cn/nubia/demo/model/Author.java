package cn.nubia.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity										//JPA要求每一个实体Entity,必须有且只有一个主键
@Table(name = "tbl_author")
public class Author {

	@Id										//定义为主键 自增
	@GeneratedValue
	@Column(name = "author_id")
	private Integer authorId;

	@Column(name = "name", nullable = false,length=32)
	private String name;
	
	
	
	public Author() {		
	}



	public Integer getAuthorId() {
		return authorId;
	}



	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	
	
	
}
