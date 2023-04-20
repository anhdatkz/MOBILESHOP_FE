package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.TaiKhoan;
import com.example.repository.TaiKhoanRepository;

@Service
public class TaiKhoanService {
	@Autowired
	private TaiKhoanRepository taiKhoanRepository;
	
	public List<TaiKhoan> listAll(){
		return (List<TaiKhoan>)taiKhoanRepository.findAll();
	}
	
	public void save(TaiKhoan taiKhoan){
		taiKhoanRepository.save(taiKhoan);
	}
	
	public TaiKhoan getTaiKhoanById(String username){
		return taiKhoanRepository.findById(username).get();
	}
	
	public void deleteTaiKhoanById(String username){
		taiKhoanRepository.deleteById(username);
	}
	
	public TaiKhoan getTaiKhoanByUsernameAndPassword(String matk, String password){
		return taiKhoanRepository.getTaiKhoanByUsernameAndPassword(matk, password);
	}
	
	public Boolean exitsByMaTK(String username){
		return taiKhoanRepository.existsById(username);
	}
}
