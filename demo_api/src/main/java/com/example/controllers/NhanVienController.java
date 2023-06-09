package com.example.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.DonHang;
import com.example.entities.KhachHang;
import com.example.entities.NhanVien;
import com.example.payload.NhanVienPhanCongResponse;
import com.example.service.DonHangService;
import com.example.service.NhanVienService;


@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class NhanVienController {
	@Autowired
	private NhanVienService nhanVienService;
	
	@Autowired
	private DonHangService donHangService;
	
	@GetMapping("/nhanvien")
	public List<NhanVien> getAllNhanVien(){
		return this.nhanVienService.listAll();
	}
	
	@GetMapping("/nhanvien/profile")
	public ResponseEntity<NhanVien> getNhanVien(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
			NhanVien nhanVien = nhanVienService.getNhanVienByMaTK(username);
			return new ResponseEntity<NhanVien>(nhanVien, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<NhanVien>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/nhanvien/phancong")
	public List<NhanVienPhanCongResponse> getAllNhanVienPhanCong(){
		List<NhanVien> nhanViens = nhanVienService.listAll();
		List<NhanVienPhanCongResponse> nhanVienPhanCongResponses = new ArrayList<NhanVienPhanCongResponse>();
		for(NhanVien nhanVien : nhanViens){
			if(nhanVien.getTaiKhoanNV().getQuyen().getMaquyen().trim().equals("AD") == false){
				NhanVienPhanCongResponse nhanVienPhanCongResponse = new NhanVienPhanCongResponse();
				nhanVienPhanCongResponse.setNhanVien(nhanVien);
				List<DonHang> donHangs = donHangService.getAllDonHangByNVGPT(nhanVien.getManv());
				nhanVienPhanCongResponse.setSodon(donHangs.size());
				nhanVienPhanCongResponses.add(nhanVienPhanCongResponse);
			}
		}
		return nhanVienPhanCongResponses;
	}
	
	@PostMapping("/nhanvien")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteHangById(@PathVariable String id){
		nhanVienService.deleteNhanVienById(id);
	}
}
