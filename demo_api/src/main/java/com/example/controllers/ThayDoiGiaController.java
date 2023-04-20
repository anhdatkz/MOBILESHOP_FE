package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.ThayDoiGia;
import com.example.service.ThayDoiGiaService;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ThayDoiGiaController {
	@Autowired
	private ThayDoiGiaService thayDoiGiaService;
	
	@GetMapping("/tdg")
	public List<ThayDoiGia> getAllTDG(){
		return this.thayDoiGiaService.listAll();
	}
	
	@PostMapping("/tdg")
	public void saveTDG(@RequestBody ThayDoiGia thayDoiGia){
		this.thayDoiGiaService.save(thayDoiGia);
	}
}
