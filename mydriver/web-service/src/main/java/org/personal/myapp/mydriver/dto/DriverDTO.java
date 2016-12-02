package org.personal.myapp.mydriver.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DRIVER")
public class DriverDTO {
	@Id
	@Column(name="ID")
	private String id;
	@Column(name="NAME")
	private String name;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DriverDTO [id=" + id + ", name=" + name + "]";
	}
}
