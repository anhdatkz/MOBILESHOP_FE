package com.example.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.CTGH;
import com.example.entities.CTGHKeys;
import com.example.entities.CTGiamGia;
import com.example.entities.LoaiSanPham;
import com.example.payload.CTGHResponse;
import com.example.service.CTGHService;
import com.example.service.LoaiSanPhamService;;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class CTGHController {
	@Autowired
	private CTGHService ctghService;
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;

	@GetMapping("/ctgh")
	public List<CTGH> getAllCTGH() {
		return this.ctghService.listAll();
	}

	@GetMapping("/ctgh/{idgh}")
	public List<CTGH> getCTGHByIdGH(@PathVariable Integer idgh) {
		return this.ctghService.getCTGHByIdGH(idgh);
	}
	
	@GetMapping("/ctgh/kh")
	public List<CTGHResponse> getCTGHKH() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
		List<CTGH> ctghs = ctghService.getCTGHByMaTk(username);
		List<CTGHResponse> ctghResponses = new ArrayList<CTGHResponse>();
		for(CTGH ctgh : ctghs){
			LoaiSanPham lsp = loaiSanPhamService.getLoaiSanPhamById(ctgh.getId().getMaloaictgh().trim());
			List<CTGiamGia> ctGiamGias = lsp.getCtGiamGiaLSP();
			CTGHResponse ctghResponse  = new CTGHResponse();
			ctghResponse.setIdgiohang(ctgh.getId().getIdgiohangctgh());
			ctghResponse.setMaloai(ctgh.getId().getMaloaictgh());
			ctghResponse.setAnh(lsp.getAnh());
			ctghResponse.setTenloai(lsp.getTenloai());
			if (ctGiamGias.size() == 0) {
				ctghResponse.setGia(lsp.getGia());
			} else {
				ctghResponse.setGia(lsp.getGia() - (lsp.getGia() * ctGiamGias.get(0).getPhantram() / 100));
			}
			ctghResponse.setSoluong(ctgh.getSoluong());
			ctghResponse.setTong(ctgh.getTong());
			ctghResponses.add(ctghResponse);
		}
		
		return ctghResponses;
	}
	
	@GetMapping("/ctgh/kh/{matk}")
	public List<CTGHResponse> getCTGHBymaTK(@PathVariable String matk) {
		List<CTGH> ctghs = ctghService.getCTGHByMaTk(matk);
		List<CTGHResponse> ctghResponses = new ArrayList<CTGHResponse>();
		for(CTGH ctgh : ctghs){
			LoaiSanPham lsp = loaiSanPhamService.getLoaiSanPhamById(ctgh.getId().getMaloaictgh().trim());
			List<CTGiamGia> ctGiamGias = lsp.getCtGiamGiaLSP();
			CTGHResponse ctghResponse  = new CTGHResponse();
			ctghResponse.setIdgiohang(ctgh.getId().getIdgiohangctgh());
			ctghResponse.setMaloai(ctgh.getId().getMaloaictgh());
			ctghResponse.setAnh(lsp.getAnh());
			ctghResponse.setTenloai(lsp.getTenloai());
			if (ctGiamGias.size() == 0) {
				ctghResponse.setGia(lsp.getGia());
			} else {
				ctghResponse.setGia(lsp.getGia() - (lsp.getGia() * ctGiamGias.get(0).getPhantram() / 100));
			}
			ctghResponse.setSoluong(ctgh.getSoluong());
			ctghResponse.setTong(ctgh.getTong());
			ctghResponses.add(ctghResponse);
		}
		
		return ctghResponses;
	}

	@PostMapping("/ctgh")
	public CTGH saveCTGH(@RequestBody CTGH ctgh) {
		this.ctghService.save(ctgh);
		return ctgh;
	}
	
	@PutMapping("/ctgh")
	public ResponseEntity<CTGH> updateLoaiSanPham(@RequestBody CTGH ctgh){
		try {
			CTGH newCTGH = ctghService.getCTGHById(ctgh.getId());
			newCTGH.setSoluong(ctgh.getSoluong());
			newCTGH.setTong(ctgh.getTong());
			ctghService.save(newCTGH);
			return new ResponseEntity<CTGH>(newCTGH, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<CTGH>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/ctgh")
	public void deleteCTGH(@RequestBody CTGHKeys id){
		ctghService.deleteById(id);
	}

	@DeleteMapping("/ctgh/{idgh}")
	public void deleteGH(@PathVariable Integer idgh){
		ctghService.deleteCTGH(idgh);
	}
}
