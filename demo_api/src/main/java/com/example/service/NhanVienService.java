package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.NhanVien;
import com.example.repository.NhanVienRepository;

@Service
public class NhanVienService {
	@Autowired
	private NhanVienRepository nhanVienRepository;
	
	public List<NhanVien> listAll(){
		return (List<NhanVien>)nhanVienRepository.findAll();
	}
	
	public void save(NhanVien nhanVien){
		nhanVienRepository.save(nhanVien);
	}
	
	public NhanVien getNhanVienById(String manv){
		return nhanVienRepository.findById(manv).get();
	}
	
	public NhanVien getNhanVienByMaTK(String matk){
		return nhanVienRepository.getKHByMaTK(matk);
	}
	
	public void deleteNhanVienById(String manv){
		nhanVienRepository.deleteById(manv);
	}
	
}
