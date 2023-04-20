package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.PhieuNhap;
import com.example.repository.PhieuNhapRepository;


@Service
public class PhieuNhapService {
	@Autowired
	private PhieuNhapRepository phieuNhapRepository;
	
	public List<PhieuNhap> listAll(){
		return (List<PhieuNhap>)phieuNhapRepository.findAll();
	}
	
	public void save(PhieuNhap phieuNhap){
		phieuNhapRepository.save(phieuNhap);
	}
	
	public PhieuNhap getPhieuNhapById(String mapn){
		return phieuNhapRepository.findById(mapn).get();
	}
	
	public void deletePhieuNhapById(String mapn){
		phieuNhapRepository.deleteById(mapn);
	}
}
