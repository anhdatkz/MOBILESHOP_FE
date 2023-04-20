package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.DonHang;
import com.example.payload.DoanhThuThang;
import com.example.repository.DonHangRepository;

@Service
public class DonHangService {
	@Autowired
	private DonHangRepository DonHangRepository;
	
	public List<DonHang> listAll(){
		return (List<DonHang>)DonHangRepository.findAll();
	}
	
	public List<DonHang> getAllDonHangByMaKH(String cmnd){
		return (List<DonHang>)DonHangRepository.getAllDonHangByMaKH(cmnd);
	}
	
	public List<DonHang> getDonHangByTrangThai(Integer matrangthai){
		return (List<DonHang>)DonHangRepository.getDonHangByTrangThai(matrangthai);
	}
	
	public List<DonHang> getDonHangByDate(Date ngaybd, Date ngaykt){
		return (List<DonHang>)DonHangRepository.getDonHangByDate(ngaybd, ngaykt);
	}
	
	public Long getThongKeDoanhThu(Date ngaybd, Date ngaykt){
		return DonHangRepository.getThongKeDoanhThu(ngaybd, ngaykt);
	}
	
	public void save(DonHang DonHang){
		DonHangRepository.save(DonHang);
	}
	
	public DonHang getDonHangById(Integer idgio){
		return DonHangRepository.findById(idgio).get();
	}
	
	public void deleteDonHangById(Integer idgio){
		DonHangRepository.deleteById(idgio);
	}
	
	public List<DoanhThuThang> getDoanhThuThang(Date ngaybd, Date ngaykt){
		return DonHangRepository.getThongKeByThang(ngaybd, ngaykt);
	}
}
