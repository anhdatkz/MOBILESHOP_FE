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

import com.example.entities.PhieuNhap;
import com.example.service.PhieuNhapService;


@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class PhieuNhapController {
	@Autowired
	private PhieuNhapService phieuNhapService;
	
	@GetMapping("/phieunhap")
	public List<PhieuNhap> getAllPhieuNhap(){
		return this.phieuNhapService.listAll();
	}
	
	@PostMapping("/phieunhap")
	public void savePhieuNhap(@RequestBody PhieuNhap phieuNhap){
		this.phieuNhapService.save(phieuNhap);
	}
	
	@GetMapping("/phieunhap/{id}")
	public ResponseEntity<PhieuNhap> getPhieuNhapById(@PathVariable String id){
		try {
			PhieuNhap phieuNhap = phieuNhapService.getPhieuNhapById(id);
			return new ResponseEntity<PhieuNhap>(phieuNhap, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<PhieuNhap>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/phieunhap/{id}")
	public ResponseEntity<PhieuNhap> updatePhieuNhap(@RequestBody PhieuNhap phieuNhap,
			@PathVariable String id){
		try {
			PhieuNhap phieuNhapExist = phieuNhapService.getPhieuNhapById(id);
			phieuNhapService.save(phieuNhap);
			return new ResponseEntity<PhieuNhap>(phieuNhap, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<PhieuNhap>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/phieunhap/{id}")
	public void deletePhieuNhapById(@PathVariable String id){
		phieuNhapService.deletePhieuNhapById(id);
	}
}
