package com.captainfresh.assignment.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CaptainSheet {
	
	public Double totalHeight = new Double(0);;
	
	private List<Cell> cellList = new ArrayList<Cell>();

	public Double getTotalHeight() {
		return totalHeight;
	}

	public void setTotalHeight(Double totalHeight) {
		this.totalHeight = totalHeight;
	}

	public List<Cell> getCellList() {
		return cellList;
	}

	public void setCellList(List<Cell> cellList) {
		this.cellList = cellList;
	}

	@Override
	public String toString() {
		return "CaptainSheet [totalHeight=" + totalHeight + ", cellList=" + cellList + "]";
	}
	
	public CaptainSheet() {	}

	public CaptainSheet(Double totalHeight, List<Cell> cellList) {
		super();
		this.totalHeight = totalHeight;
		this.cellList = cellList;
	}
	
	public Long addCell(Cell cell) {
		getCellList().add(cell);
		setTotalHeight(getTotalHeight() + cell.getHeight());
		return cell.getId();
	}

}
