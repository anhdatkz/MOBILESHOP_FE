package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.GioHang;
import com.example.repository.GioHangRepository;


@Service
public class GioHangService {
	@Autowired
	private GioHangRepository gioHangRepository;
	
	public List<GioHang> listAll(){
		return (List<GioHang>)gioHangRepository.findAll();
	}
	
	public GioHang getGioHangByMaKH(String matk){
		return gioHangRepository.getGioHangByMaKH(matk);
	}
	
//	public List<GioHang> getGioHangByTrangThai(Integer matrangthai){
//		return (List<GioHang>)gioHangRepository.getGioHangByTrangThai(matrangthai);
//	}
//	
//	public List<GioHang> getGioHangByDate(Date ngaybd, Date ngaykt){
//		return (List<GioHang>)gioHangRepository.getGioHangByDate(ngaybd, ngaykt);
//	}
	
	public void save(GioHang gioHang){
		gioHangRepository.save(gioHang);
	}
	
	public GioHang getGioHangById(Integer idgio){
		return gioHangRepository.findById(idgio).get();
	}
	
	public void deleteGioHangById(Integer idgio){
		gioHangRepository.deleteById(idgio);
	}
}
