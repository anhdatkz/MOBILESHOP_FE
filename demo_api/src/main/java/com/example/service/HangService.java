package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Hang;
import com.example.repository.HangRepository;

@Service
public class HangService {
	@Autowired
	private HangRepository hangRepository;
	
	public List<Hang> listAll(){
		return (List<Hang>)hangRepository.findAll();
	}
	
	public void save(Hang hang){
		hangRepository.save(hang);
	}
	
	public Hang getHangById(String mahang){
		return hangRepository.getHangById(mahang);
	}
	
	public void deleteHangById(String maHang){
		hangRepository.deleteById(maHang);
	}
	
	public Boolean exitsByMaHang(String maHang){
		return hangRepository.existsById(maHang);
	}
}
