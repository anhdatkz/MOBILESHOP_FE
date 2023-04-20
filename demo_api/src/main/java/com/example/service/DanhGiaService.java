package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.DanhGia;
import com.example.entities.DanhGiaKeys;
import com.example.repository.DanhGiaRepository;

@Service
public class DanhGiaService {
	@Autowired
	private DanhGiaRepository danhGiaRepository;
	
	public List<DanhGia> listAll(){
		return (List<DanhGia>)danhGiaRepository.findAll();
	}
	
	public List<DanhGia> getDanhGiaByMaLoai(String maloai){
		return (List<DanhGia>)danhGiaRepository.getDanhGiaByMaLoai(maloai);
	}
	
	public void save(DanhGia danhGia){
		danhGiaRepository.save(danhGia);
	}
	
	public void deleteById(DanhGiaKeys id){
		danhGiaRepository.deleteById(id);;
	}
	
	public Boolean existByID(DanhGiaKeys danhGiaKeys){
		return danhGiaRepository.existsById(danhGiaKeys);
	}
}
