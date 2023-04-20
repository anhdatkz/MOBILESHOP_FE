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
import org.springframework.web.client.HttpClientErrorException.Gone;

import com.example.entities.CTGH;
import com.example.entities.GioHang;
import com.example.payload.GioHangResponse;
import com.example.service.GioHangService;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class GioHangController {
	@Autowired
	private GioHangService gioHangService;
	
	@GetMapping("/giohang")
	public List<GioHang> getAllGioHang(){
		return this.gioHangService.listAll();
	}
	
	@PostMapping("/giohang")
	public Integer saveGioHang(@RequestBody GioHang gioHang){
		this.gioHangService.save(gioHang);
		return gioHang.getIdgiohang();
	}
	
	@GetMapping("/giohang/{id}")
	public ResponseEntity<GioHang> getGioHangById(@PathVariable Integer id){
		try {
			GioHang gioHang = gioHangService.getGioHangById(id);
			return new ResponseEntity<GioHang>(gioHang, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<GioHang>(HttpStatus.NOT_FOUND);
		}
	}
	
//	@GetMapping("/giohang/trangthai/{id}")
//	public List<GioHang> getGioHangByTrangThai(@PathVariable Integer id){
//		try {
//			List<GioHang> gioHang = gioHangService.getGioHangByTrangThai(id);
//			return gioHang;
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//			return (List<GioHang>) new ResponseEntity<List<GioHang>>(HttpStatus.NOT_FOUND);
//		}
//		
//	}
	
	@GetMapping("/giohang/kh/{matk}")
	public GioHangResponse getGioHangByMaKH(@PathVariable String matk){
		try {
			GioHang gioHang = gioHangService.getGioHangByMaKH(matk);
			GioHangResponse gioHangResponse = new GioHangResponse();
			gioHangResponse.setIdgiohang(gioHang.getIdgiohang());
			gioHangResponse.setCmnd(gioHang.getKhachHangGH().getCmnd().trim());
			return gioHangResponse;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@PutMapping("/giohang/{id}")
	public ResponseEntity<GioHang> updateGioHang(@RequestBody CTGH ctgh,
			@PathVariable Integer id){
		try {
			GioHang gioHangSP = gioHangService.getGioHangById(id);
			gioHangService.save(gioHangSP);
			return new ResponseEntity<GioHang>(gioHangSP, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<GioHang>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/giohang/{id}")
	public void deleteGioHangById(@PathVariable Integer id){
		gioHangService.deleteGioHangById(id);
	}
}
