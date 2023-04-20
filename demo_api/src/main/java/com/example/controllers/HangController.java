package com.example.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Hang;
import com.example.entities.LoaiSanPham;
import com.example.payload.ApiResponse;
import com.example.service.HangService;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class HangController {
	@Autowired
	private HangService hangService;
	
	@GetMapping("/hang")
	public List<Hang> getAllHang(){
		return this.hangService.listAll();
	}
	
	@PostMapping("/hang")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> saveHang(@RequestBody Hang hang){
		if(hangService.exitsByMaHang(hang.getMahang())){
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Mã hãng đã tồn tại!"),
                    HttpStatus.OK);
		}
		this.hangService.save(hang);
		return new ResponseEntity(new ApiResponse(true, "Tạo tài khoản thành công!"), HttpStatus.OK);
	}
	
	@GetMapping("/hang/{id}")
	public ResponseEntity<Hang> getHangById(@PathVariable String id){
		try {
			Hang hang = hangService.getHangById(id);
			return new ResponseEntity<Hang>(hang, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<Hang>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/hang/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Hang> updateHang(@RequestBody Hang hang,
			@PathVariable String id){
		try {
			Hang hangExist = hangService.getHangById(id);
			hangExist.setTenhang(hang.getTenhang());
			hangExist.setAnh(hang.getAnh());
			hangService.save(hangExist);
			return new ResponseEntity<Hang>(hang, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<Hang>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/hang/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean deleteHangById(@PathVariable String id){
		Hang hang = hangService.getHangById(id);
		List<LoaiSanPham> lsp = hang.getLoaiSanPhams();
		if(lsp.size() == 0){
			hangService.deleteHangById(id);
			return true;
		}
		else return false;
	}
}
