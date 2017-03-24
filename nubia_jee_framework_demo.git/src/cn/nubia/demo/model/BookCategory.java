package cn.nubia.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_book_category")
public class BookCategory {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "name", nullable = false,length=32)
	private String name;
	
	
	
	public BookCategory() {		
	}



	public Integer getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	
}