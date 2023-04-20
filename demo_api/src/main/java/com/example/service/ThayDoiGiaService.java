package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.ThayDoiGia;
import com.example.repository.ThayDoiGiaRepository;

@Service
public class ThayDoiGiaService {
	@Autowired
	private ThayDoiGiaRepository thayDoiGiaRepository;
	
	public List<ThayDoiGia> listAll(){
		return (List<ThayDoiGia>)thayDoiGiaRepository.findAll();
	}
	
	public void save(ThayDoiGia thayDoiGia){
		thayDoiGiaRepository.save(thayDoiGia);
	}
}
