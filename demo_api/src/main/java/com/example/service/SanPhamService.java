package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.SanPham;
import com.example.repository.SanPhamRepository;



@Service
public class SanPhamService {
	@Autowired
	private SanPhamRepository sanPhamRepository;
	
	public List<SanPham> listAll(){
		return (List<SanPham>)sanPhamRepository.findAll();
	}
	
	
	public void save(SanPham sanPham){
		sanPhamRepository.save(sanPham);
	}
	
	public SanPham getSanPhamById(String soseri){
		return sanPhamRepository.findById(soseri).get();
	}
	
	public void deleteSanPhamById(String soseri){
		sanPhamRepository.deleteById(soseri);
	}
}
