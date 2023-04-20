package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.CTGH;
import com.example.entities.CTGHKeys;
import com.example.repository.CTGHRepository;

@Service
public class CTGHService {
	@Autowired
	private CTGHRepository ctghRepository;
	
	public List<CTGH> listAll(){
		return (List<CTGH>)ctghRepository.findAll();
	}
	
	public void save(CTGH ctgh){
		ctghRepository.save(ctgh);
	}
	
	public List<CTGH> getCTGHByIdGH(Integer idgh){
		return (List<CTGH>) ctghRepository.getCTGHByIdGH(idgh);
	}
	
	public List<CTGH> getCTGHByMaTk(String matk){
		return (List<CTGH>) ctghRepository.getCTGHByMaTK(matk);
	}
	
	public CTGH getCTGHById(CTGHKeys ctghKeys){
		return ctghRepository.getById(ctghKeys);
	}
	
	public void deleteById(CTGHKeys id){
		ctghRepository.deleteById(id);;
	}
	
	public void deleteCTGH(Integer idgh){
		ctghRepository.deleteCTGHByIdGH(idgh);
	}
	
	public Boolean exitsCTGH(CTGHKeys ctghKeys){
		return ctghRepository.existsById(ctghKeys);
	}
}
