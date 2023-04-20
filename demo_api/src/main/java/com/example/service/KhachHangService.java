package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.KhachHang;
import com.example.repository.KhachHangRepository;


@Service
public class KhachHangService {
	@Autowired
	private KhachHangRepository khachHangRepository;
	
	public List<KhachHang> listAll(){
		return (List<KhachHang>)khachHangRepository.findAll();
	}
	
	public void save(KhachHang khachHang){
		khachHangRepository.save(khachHang);
	}
	
	public KhachHang getKhachHangById(String makh){
		return khachHangRepository.findById(makh).get();
	}
	
	public KhachHang getKhachHangByMaTK(String matk){
		return khachHangRepository.getKHByMaTK(matk);
	}
	
	public void deleteKhachHangById(String makh){
		khachHangRepository.deleteById(makh);
	}
	
	public Boolean existByCMND(String cmnd){
		return khachHangRepository.existsById(cmnd);
	}
}
