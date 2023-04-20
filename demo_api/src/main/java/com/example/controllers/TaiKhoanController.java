package com.example.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.json.JSONException;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.configsJWT.JwtTokenProvider;
import com.example.entities.TaiKhoan;
import com.example.payload.ApiResponse;
import com.example.repository.TaiKhoanRepository;
import com.example.service.TaiKhoanService;



@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class TaiKhoanController {
	
	private static Logger log = LoggerFactory.getLogger(TaiKhoanController.class);

	@Autowired
	private TaiKhoanRepository taiKhoanRepository;
	
	@Autowired
	private TaiKhoanService taiKhooanService;
	
	@GetMapping("/taikhoan")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<TaiKhoan> getAllTaiKhoan(){
		return this.taiKhooanService.listAll();
	}
	
	@PostMapping("/taikhoan")
	public ResponseEntity<ApiResponse> saveTaiKhoan(@RequestBody TaiKhoan taiKhoan){
		if(taiKhooanService.exitsByMaTK(taiKhoan.getMatk())){
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Username đã được sử dụng!"),
                    HttpStatus.BAD_REQUEST);
		}
		this.taiKhooanService.save(taiKhoan);
		return new ResponseEntity(new ApiResponse(true, "Tạo tài khoản thành công!"), HttpStatus.OK);
	}
	
	@GetMapping("/taikhoan/{id}")
	public ResponseEntity<TaiKhoan> getTaiKhoanById(@PathVariable String id){
		try {
			TaiKhoan taiKhoan = taiKhooanService.getTaiKhoanById(id);
			return new ResponseEntity<TaiKhoan>(taiKhoan, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<TaiKhoan>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/taikhoan/{id}/{pass}")
	public ResponseEntity<TaiKhoan> getTaiKhoanByUsernameAndPassword(@PathVariable String id, @PathVariable String pass){
		try {
			TaiKhoan taiKhoan = taiKhooanService.getTaiKhoanByUsernameAndPassword(id, pass);
			return new ResponseEntity<TaiKhoan>(taiKhoan, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<TaiKhoan>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/taikhoan/{id}")
	public ResponseEntity<TaiKhoan> updateTaiKhoan(@RequestBody TaiKhoan taiKhoan,
			@PathVariable String id){
		try {
			TaiKhoan taiKhoanExist = taiKhooanService.getTaiKhoanById(id);
			taiKhooanService.save(taiKhoan);
			return new ResponseEntity<TaiKhoan>(taiKhoan, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<TaiKhoan>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/taikhoan/{id}")
	public void deleteTaiKhoanById(@PathVariable String id){
		taiKhooanService.deleteTaiKhoanById(id);
	}
}
