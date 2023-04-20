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

import com.example.entities.NhanVien;
import com.example.service.NhanVienService;


@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class NhanVienController {
	@Autowired
	private NhanVienService nhanVienService;
	
	@GetMapping("/nhanvien")
	public List<NhanVien> getAllNhanVien(){
		return this.nhanVienService.listAll();
	}
	
	@PostMapping("/nhanvien")
	public void saveHang(@RequestBody NhanVien nhanVien){
		this.nhanVienService.save(nhanVien);
	}
	
	@GetMapping("/nhanvien/{id}")
	public ResponseEntity<NhanVien> getNhanVienById(@PathVariable String id){
		try {
			NhanVien nv = nhanVienService.getNhanVienById(id);
			return new ResponseEntity<NhanVien>(nv, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<NhanVien>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/nhanvien/tk/{id}")
	public ResponseEntity<NhanVien> getNhanVienByMaTK(@PathVariable String id){
		try {
			NhanVien nv = nhanVienService.getNhanVienByMaTK(id);
			return new ResponseEntity<NhanVien>(nv, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<NhanVien>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/nhanvien/{id}")
	public ResponseEntity<NhanVien> updateHang(@RequestBody NhanVien nhanVien,
			@PathVariable String id){
		try {
			NhanVien nhanVienExist = nhanVienService.getNhanVienById(id);
			nhanVienService.save(nhanVien);
			return new ResponseEntity<NhanVien>(nhanVien, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<NhanVien>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/nhanvien/{id}")
	public void deleteHangById(@PathVariable String id){
		nhanVienService.deleteNhanVienById(id);
	}
}
