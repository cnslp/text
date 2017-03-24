package cn.nubia.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_press")
public class Press {

	@Id
	@GeneratedValue
	@Column(name = "press_id")
	private Integer pressId;

	@Column(name = "name", nullable = false,length=32)
	private String name;
	
	
	
	public Press() {		
	}



	public Integer getPressId() {
		return pressId;
	}



	public void setPressId(Integer pressId) {
		this.pressId = pressId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
}