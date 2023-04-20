package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.CTDH;
import com.example.entities.LoaiSanPham;
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

	@GetMapping("/ctdh/{iddh}")
	public List<CTDH> getCTGHByIdDH(@PathVariable Integer iddh) {
		return this.ctdhService.getCTDHByIdGH(iddh);
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
