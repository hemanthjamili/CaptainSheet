package com.captainfresh.assignment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.captainfresh.assignment.model.CaptainSheet;
import com.captainfresh.assignment.model.Cell;

@Service
public class CaptainSheetService {

	private static CaptainSheet mySheet = new CaptainSheet();
	private static Long cellIdentifier = 3L;
	
	static {
		Cell c1 = new Cell(1L, 10.0);
		Cell c2 = new Cell(2L, 20.0);
		Cell c3 = new Cell(3L, 5.0);
		Cell c4 = new Cell(4L, 5.0);
		Cell c5 = new Cell(5L, 10.0);
		Cell c6 = new Cell(6L, 6.0);
		Cell c7 = new Cell(7L, 5.0);
		Cell c8 = new Cell(8L, 5.0);
		Cell c9 = new Cell(9L, 5.0);
		Cell c10 = new Cell(10L, 15.0);

		mySheet.addCell(c1);
		mySheet.addCell(c2);
		mySheet.addCell(c3);
		mySheet.addCell(c4);
		mySheet.addCell(c5);
		mySheet.addCell(c6);
		mySheet.addCell(c7);
		mySheet.addCell(c8);
		mySheet.addCell(c9);
		mySheet.addCell(c10);
		
	}
	
	public Long addCell(Cell cell) {
		if(cell.getId()!=null) {
			return null;	// Cell already exist in the sheet		
		}
		cell.setId(cellIdentifier++);
		mySheet.addCell(cell);
		return cell.getId();
	}
	
	public List<Long> addAllCells(List<Cell> cellsList){
		if(cellsList.isEmpty())
			return null;
		List<Long> cellIdList = new ArrayList<Long>();
		
		for(Cell cell: cellsList) {
			cellIdList.add(addCell(cell));
		}
		return cellIdList;
		
	}
	
	public List<Cell> getCellList() {
		return mySheet.getCellList();
	}	
	
	public Long updateCell(Cell newCell) {
		
		Cell cellToBeUpdated = mySheet.getCellList().stream()
				.filter(x -> x.getId().equals(newCell.getId()))
				.findAny()
				.orElse(null);
		
		if(cellToBeUpdated!=null && !getCellList().isEmpty()) {
			Double heightDiff;
			if(cellToBeUpdated.getHeight()>newCell.getHeight())
				heightDiff = cellToBeUpdated.getHeight() - newCell.getHeight();
			else 
				heightDiff = newCell.getHeight() - cellToBeUpdated.getHeight();
			 
			cellToBeUpdated.setHeight(newCell.getHeight());
			mySheet.setTotalHeight(getTotalHeight() + heightDiff);
			
			int cellPos = getCellList().indexOf(cellToBeUpdated);
			mySheet.getCellList().set(cellPos, cellToBeUpdated);
			
			return newCell.getId();
		}
		return null;
	}
	
	public Long deleteCell(Long cellId) {
		
		Optional<Cell> cellToBeDeleted = mySheet.getCellList().stream()
												.filter(cell -> cell.getId().equals(cellId))
												.findAny();
		if(cellToBeDeleted.isPresent()) {
			mySheet.getCellList().remove(cellToBeDeleted.get());
			mySheet.setTotalHeight(mySheet.getTotalHeight() - cellToBeDeleted.get().getHeight());
			return cellToBeDeleted.get().getId();
		} else {
			//Cell not exist in the Sheet
			return null;
		}		
	}
	
	public List<Long> deleteAllCells(){
		if(mySheet.getCellList().isEmpty())
			return null;
		List<Long> cellIdList = mySheet.getCellList().stream().map(cell -> cell.getId()).collect(Collectors.toList());
		
		mySheet.getCellList().clear();
		return cellIdList;
		
	}
	
	public Long getCellByPixel(Long pixel) {
		
		List<Cell> cellList = mySheet.getCellList();
		double sum = 0;
		double prevSum = 0;
		for(Cell cell: cellList) {
			prevSum = sum;
			sum = sum + cell.getHeight();
			if(pixel>=prevSum && pixel <= sum) {
				return cell.getId();
			}
		}
		return -1L;
	}
	
	public Double getTotalHeight() {
		return mySheet.getTotalHeight();
	}
	
	public Cell getCellById(Long cellId) {
		
		return mySheet.getCellList().stream()
				.filter(cell -> cell.getId().equals(cellId))
				.findAny().orElse(null);
	}
}
