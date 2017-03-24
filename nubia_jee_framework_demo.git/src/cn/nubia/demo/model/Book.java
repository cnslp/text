package cn.nubia.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book")
public class Book {

	@Id
	@GeneratedValue
	@Column(name = "book_id")
	private Integer bookId;

	@Column(name = "name", nullable = false,length=32)
	private String name;
	
	@Column(name = "add_time", insertable=false, updatable=false)
	private Timestamp addTime;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="press_id")
	private Press press;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private BookCategory bookCategory;
	
	public Book() {		
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Press getPress() {
		return press;
	}

	public void setPress(Press press) {
		this.press = press;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	
}
