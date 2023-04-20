package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Rom;
import com.example.repository.RomRepository;



@Service
public class RomService {
	@Autowired
	private RomRepository romRepository;
	
	public List<Rom> listAll(){
		return (List<Rom>)romRepository.findAll();
	}
	
	public void save(Rom rom){
		romRepository.save(rom);
	}
	
	public Rom getRomById(Integer marom){
		return romRepository.getById(marom);
	}
	
	public void deleteRomById(Integer marom){
		romRepository.deleteById(marom);
	}
}
