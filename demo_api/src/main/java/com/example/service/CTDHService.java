package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.CTDH;
import com.example.repository.CTDHRepository;

@Service
public class CTDHService {
	@Autowired
	private CTDHRepository ctdhRepository;
	
	public List<CTDH> listAll(){
		return (List<CTDH>)ctdhRepository.findAll();
	}
	
	public void save(CTDH ctdh){
		ctdhRepository.save(ctdh);
	}
	
	public List<CTDH> getCTDHByIdGH(Integer madh){
		return (List<CTDH>) ctdhRepository.getCTDHByMaDH(madh);
	}
}
