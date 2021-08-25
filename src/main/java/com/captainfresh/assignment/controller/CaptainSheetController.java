package com.captainfresh.assignment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.captainfresh.assignment.model.Cell;
import com.captainfresh.assignment.service.CaptainSheetService;

@RestController("/api")
public class CaptainSheetController {
	
	public static final Logger log = LoggerFactory.getLogger(CaptainSheetController.class);
	
	@Autowired
	CaptainSheetService myService;
	
	public CaptainSheetController(CaptainSheetService captainSheetService) {
		this.myService = captainSheetService;
	}
	
	@GetMapping("/getCellList")
	public ResponseEntity<List<Cell>> getCellList() {
		
		List<Cell> cellList = myService.getCellList();
		
		log.info(cellList.size()+ " cells found in the CaptianSheet");
		
		if(cellList.isEmpty())
			return new ResponseEntity<List<Cell>>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Cell>>(cellList, HttpStatus.OK);
	}
	
	@GetMapping("/getCellById/{cellId}")
	public ResponseEntity<Cell> getCellById(@PathVariable Long cellId) {
		
		Cell cell = myService.getCellById(cellId);
		
		if(cell==null) {
			log.warn("No Cell found with ID: ",cellId);
			return new ResponseEntity<Cell>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cell>(cell, HttpStatus.OK);
	}
	
	@GetMapping("/getTotalHeight")
	public ResponseEntity<Double> getTotalHeight() {
		
		Double totalHeight = myService.getTotalHeight();
		log.info("Total Height of Captain Sheet: "+ totalHeight);
		
		return new ResponseEntity<Double>(totalHeight, HttpStatus.OK);
	}
	
	@PostMapping("/addCell")
	public ResponseEntity<Long> addCell(@RequestBody Cell cell) {
		
		log.info("Adding cell{} to CaptainSheet: ",cell);
		Long cellId = myService.addCell(cell);
		
		log.info("Cell added successfully with ID: {} ",cell.getId());		
		return new ResponseEntity<Long>(cell.getId(), HttpStatus.CREATED);
	}
	
	@PostMapping("/addAllCells")
	public ResponseEntity<List<Long>> addAllCells(@RequestBody List<Cell> cellsList) {
		
		log.info("Adding {} no.of cells to CaptainSheet: ",cellsList.size());
		List<Long> cellIdList = myService.addAllCells(cellsList);
		
		if(cellIdList==null) {
			log.info("No cells found in the passed list.");		
			return new ResponseEntity<List<Long>>(cellIdList, HttpStatus.NO_CONTENT);
		}
		
		log.info("{} no.of cells added successfully with IDs: {} ",cellIdList.size(), cellIdList);		
		return new ResponseEntity<List<Long>>(cellIdList, HttpStatus.CREATED);
	}	
	
	@PutMapping("/updateCell")
	public ResponseEntity<Long> updateCell(@RequestBody Cell cell){
		
		log.info("Updating a cell with ID: {}",cell.getId());
		
		Long cellId = myService.updateCell(cell);
		
		if(cellId==null) {
			log.info("No Cell found with ID: ",cell.getId());
			return new ResponseEntity<Long>(cellId, HttpStatus.NOT_FOUND);
		}		
		log.info("Cell with ID {} updated successfully",cell.getId());

		return new ResponseEntity<Long>(cellId, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteCell/{cellId}")
	public ResponseEntity<Long> deleteCell(@PathVariable Long cellId){
		
		
		Long deletedCellId = myService.deleteCell(cellId);
		
		if(deletedCellId==null) {
			log.info("No Cell found with ID: ",cellId);
			return new ResponseEntity<Long>(cellId, HttpStatus.NOT_FOUND);
		}
		
		log.info("Cell with ID {} deleted successfully",deletedCellId);
		return new ResponseEntity<Long>(deletedCellId, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAllCells")
	public ResponseEntity<List<Long>> deleteAllCells(){	
		
		List<Long> deletedCellId = myService.deleteAllCells();
		
		if(deletedCellId==null) {
			log.info("CaptainSheet is empty");
			return new ResponseEntity<List<Long>>(HttpStatus.NO_CONTENT);
		}
		
		log.info("{} no.of cells deleted successfully with IDs: {} ",deletedCellId.size(), deletedCellId);
		return new ResponseEntity<List<Long>>(deletedCellId, HttpStatus.OK);
	}
	
	@GetMapping("/getCellByPixel/{pixel}")
	public ResponseEntity<String> getCellByPixel(@PathVariable Long pixel){
		
		if(pixel>myService.getTotalHeight()) {
			log.info("Pixel is out of range of sheet's total size.");
			return new ResponseEntity<String>("Pixel cannot be greater than sheet's total size", HttpStatus.NOT_ACCEPTABLE);
		}
		if(pixel>0) {
			Long cellId = myService.getCellByPixel(pixel);
			return new ResponseEntity<String>("Pixel falls in cell with ID: "+cellId, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Pixel value should be greater than 0", HttpStatus.NOT_ACCEPTABLE);
		
	}	
}
