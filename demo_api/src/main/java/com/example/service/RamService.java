package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Ram;
import com.example.repository.RamRepository;

@Service
public class RamService {
	@Autowired
	private RamRepository ramRepository;
	
	public List<Ram> listAll(){
		return (List<Ram>)ramRepository.findAll();
	}
	
	public void save(Ram ram){
		ramRepository.save(ram);
	}
	
	public Ram getRamById(Integer maram){
		return ramRepository.getById(maram);
	}
	
	public void deleteRamById(Integer maram){
		ramRepository.deleteById(maram);
	}
}
