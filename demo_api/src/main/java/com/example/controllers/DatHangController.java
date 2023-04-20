package com.example.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.DatHang;
import com.example.service.DatHangService;



@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class DatHangController {
	@Autowired
	private DatHangService datHangService;
	
	@GetMapping("/dathang")
	public List<DatHang> getAllDatHang(){
		return this.datHangService.listAll();
	}
	
	@PostMapping("/dathang")
	public void saveDatHang(@RequestBody DatHang datHang){
		this.datHangService.save(datHang);
	}
	
	@GetMapping("/dathang/{id}")
	public ResponseEntity<DatHang> getDatHangById(@PathVariable String id){
		try {
			DatHang datHang = datHangService.getDathangById(id);
			return new ResponseEntity<DatHang>(datHang, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<DatHang>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/dathang/{id}")
	public ResponseEntity<DatHang> updateDatHang(@RequestBody DatHang datHang,
			@PathVariable String id){
		try {
			DatHang datHangExist = datHangService.getDathangById(id);
			datHangService.save(datHang);
			return new ResponseEntity<DatHang>(datHang, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<DatHang>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/dathang/{id}")
	public void deleteDatHangById(@PathVariable String id){
		datHangService.deleteDatHangById(id);
	}
}
