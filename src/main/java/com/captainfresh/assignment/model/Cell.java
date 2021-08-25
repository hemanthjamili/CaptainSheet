package com.captainfresh.assignment.model;

import org.springframework.stereotype.Component;

@Component
public class Cell {
	
	private Long id;
	private Double height;
	
	public Cell() {	}
	
	public Cell(Long id, Double height) {
		super();
		this.id = id;
		this.height = height;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Cell [id=" + id + ", height=" + height + "]";
	}
}
