package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.TrangThai;
import com.example.repository.TrangThaiRepository;

@Service
public class TrangThaiService {
	@Autowired
	private TrangThaiRepository trangThaiRepository;
	
	public List<TrangThai> listAll(){
		return (List<TrangThai>)trangThaiRepository.findAll();
	}
	
	public void save(TrangThai trangThai){
		trangThaiRepository.save(trangThai);
	}
	
	public TrangThai getTrangThaiById(Integer matrangthai){
		return trangThaiRepository.getById(matrangthai);
	}
	
	public void deleteTrangThaiById(Integer matrangthai){
		trangThaiRepository.deleteById(matrangthai);
	}
}
