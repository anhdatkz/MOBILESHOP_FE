package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.DatHang;
import com.example.repository.DatHangRepository;


@Service
public class DatHangService {
	@Autowired
	private DatHangRepository datHangRepository;
	
	public List<DatHang> listAll(){
		return (List<DatHang>)datHangRepository.findAll();
	}
	
	public void save(DatHang datHang){
		datHangRepository.save(datHang);
	}
	
	public DatHang getDathangById(String maddh){
		return datHangRepository.findById(maddh).get();
	}
	
	public void deleteDatHangById(String maddh){
		datHangRepository.deleteById(maddh);
	}
}
