package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.LoaiSanPham;
import com.example.repository.LoaiSanPhamRepository;

@Service
public class LoaiSanPhamService {
	@Autowired
	private LoaiSanPhamRepository loaiSanPhamRepository;
	
	public List<LoaiSanPham> listAll(){
		return (List<LoaiSanPham>)loaiSanPhamRepository.findAll();
	}
	
	public List<LoaiSanPham> get8LSP(){
		return (List<LoaiSanPham>)loaiSanPhamRepository.findTopN();
	}
	
	public List<LoaiSanPham> get8LSPNew(){
		return (List<LoaiSanPham>)loaiSanPhamRepository.findTopNew();
	}
	
	public List<LoaiSanPham> getLSPKM(){
		return (List<LoaiSanPham>)loaiSanPhamRepository.getLoaiSanPhamKM();
	}
	
	public void save(LoaiSanPham loaiSanPham){
		loaiSanPhamRepository.save(loaiSanPham);
	}
	
	public LoaiSanPham getLoaiSanPhamById(String maLoai){
		return loaiSanPhamRepository.findLoaiSanPhamByID(maLoai);
	}
	
	public void deleteLoaiSanPhamById(String maLoai){
		loaiSanPhamRepository.deleteById(maLoai);
	}
	
	public List<LoaiSanPham> findLoaiSanPhamByHang(String maHang){
		return loaiSanPhamRepository.findLoaiSanPhamByHang(maHang);
	}
	
	public List<LoaiSanPham> findLoaiSanPhamByName(String tenloai){
		return loaiSanPhamRepository.findLoaiSanPhamByName(tenloai);
	}
	
	public Boolean existsByMaLoai(String maloai){
		return loaiSanPhamRepository.existsById(maloai);
	}
	
//	public List<LoaiSanPham> findLoaiSanPhamKM(){
//		return loaiSanPhamRepository.findLoaiSanPhamKM();
//	}
	
//	public List<LoaiSanPham> getLoaiSPKM(){
//		return loaiSanPhamRepository.getLoaiSPKM();
//	}
}
