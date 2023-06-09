package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.CTDH;
import com.example.entities.CTGH;
import com.example.entities.CTGiamGia;
import com.example.entities.LoaiSanPham;
import com.example.payload.CTDHResponse;
import com.example.payload.CTGHResponse;
import com.example.service.CTDHService;
import com.example.service.LoaiSanPhamService;

@RestController
@RequestMapping("/api/v1")
public class CTDHController {
	@Autowired
	private CTDHService ctdhService;
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;

	@GetMapping("/ctdh")
	public List<CTDH> getAllCTDH() {
		return this.ctdhService.listAll();
	}

//	@GetMapping("/ctdh/{iddh}")
//	public List<CTDH> getCTGHByIdDH(@PathVariable Integer iddh) {
//		return this.ctdhService.getCTDHByIdGH(iddh);
//	}
	
	@GetMapping("/ctdh/{iddh}")
	public List<CTDHResponse> getCTGHByIdDH(@PathVariable Integer iddh) {
		List<CTDH> ctdhs = ctdhService.getCTDHByIdDH(iddh);
		List<CTDHResponse> ctdhResponses = new ArrayList<CTDHResponse>();
		
		for(CTDH ctdh : ctdhs){
			LoaiSanPham lsp = loaiSanPhamService.getLoaiSanPhamById(ctdh.getId().getMaloaictdh().trim());
			List<CTGiamGia> ctGiamGias = lsp.getCtGiamGiaLSP();
			CTDHResponse ctdhResponse  = new CTDHResponse();
			ctdhResponse.setMadh(ctdh.getId().getMadhctdh());
			ctdhResponse.setTenloai(lsp.getTenloai());
			ctdhResponse.setAnh(lsp.getAnh());
			if (ctGiamGias.size() == 0) {
				ctdhResponse.setGia(lsp.getGia());
			} else {
				ctdhResponse.setGia(lsp.getGia() - (lsp.getGia() * ctGiamGias.get(0).getPhantram() / 100));
			}
			ctdhResponse.setSoluong(ctdh.getSoluong());
			ctdhResponse.setTong(ctdh.getTonggia());
			ctdhResponses.add(ctdhResponse);
		}
		
		return ctdhResponses;
	}

	@PostMapping("/ctdh")
	public CTDH saveCTDH(@RequestBody CTDH ctdh) {
		this.ctdhService.save(ctdh);	
//		LoaiSanPham lsp = loaiSanPhamService.getLoaiSanPhamById(ctdh.getId().getMaloaictdh().trim());
//		Integer soluong = 0;
//		soluong = lsp.getSoluongton() - ctdh.getSoluong();
//		lsp.setSoluongton(soluong);
//		loaiSanPhamService.save(lsp);
		return ctdh;
	}
}
