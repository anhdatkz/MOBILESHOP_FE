package com.example.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Hang;
import com.example.entities.LoaiSanPham;
import com.example.entities.Ram;
import com.example.entities.Rom;
import com.example.payload.ApiResponse;
import com.example.payload.LoaiSanPhamRequest;
import com.example.payload.LoaiSanPhamResponse;
import com.example.service.HangService;
import com.example.service.LoaiSanPhamService;
import com.example.service.RamService;
import com.example.service.RomService;

@CrossOrigin(origins ="http://localhost:3000, http://localhost:3001")
@RestController
@RequestMapping("/api/v1")
public class LoaiSanPhamController {
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@Autowired
	private HangService hangService;
	
	@Autowired
	private RamService ramService;
	
	@Autowired
	private RomService romService;
	
	@GetMapping("/loaisanpham")
	public List<LoaiSanPham> getAllLoaiSanPham(){
		return this.loaiSanPhamService.get8LSP();
	}
	
	@GetMapping("/lspnew")
	public List<LoaiSanPham> getAllLoaiSanPhamNew(){
		return this.loaiSanPhamService.get8LSPNew();
	}
	
	@GetMapping("/lspkm")
	public List<LoaiSanPham> getAllLoaiSanPhamKM(){
		return this.loaiSanPhamService.getLSPKM();
	}
	
	@GetMapping("/lsp")
	public List<LoaiSanPham> getAllLSP(){
		return this.loaiSanPhamService.listAll();
	}
	
	@GetMapping("/loaisanpham/hang={id}")
	public List<LoaiSanPham> findLoaiSanPhamByHang(@PathVariable String id){
		return this.loaiSanPhamService.findLoaiSanPhamByHang(id);
	}
	
	@GetMapping("/loaisanpham/query={name}")
	public List<LoaiSanPham> findLoaiSanPhamByname(@PathVariable String name){
		return this.loaiSanPhamService.findLoaiSanPhamByName(name);
	}
	
//	@PostMapping("/loaisanpham")
//	public void saveLoaiSanPham(@RequestBody LoaiSanPham loaiSanPham){
//		this.loaiSanPhamService.save(loaiSanPham);
//	}
	
	@PostMapping("/loaisanpham")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> saveLoaiSanPham(@RequestBody LoaiSanPhamRequest loaiSanPham){
		if(loaiSanPhamService.existsByMaLoai(loaiSanPham.getMaloai())){
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Mã sản phẩm đã tồn tại!"),
                    HttpStatus.OK);
		}
		LoaiSanPham lspNew = new LoaiSanPham();
		Hang hang = hangService.getHangById(loaiSanPham.getMahang().toString().trim());
		Ram ram = ramService.getRamById(loaiSanPham.getRam());
		Rom rom = romService.getRomById(loaiSanPham.getRom());
		lspNew.setMaloai(loaiSanPham.getMaloai());
		lspNew.setTenloai(loaiSanPham.getTenloai());
		lspNew.setAnh(loaiSanPham.getAnh());
		lspNew.setMota(loaiSanPham.getMota());
		lspNew.setChip(loaiSanPham.getChip());
		lspNew.setRam(ram);
		lspNew.setRom(rom);
		lspNew.setMahdh(loaiSanPham.getHedieuhanh());
		lspNew.setPin(loaiSanPham.getPin());
		lspNew.setManhinh(loaiSanPham.getManhinh());
		lspNew.setSoluongton(loaiSanPham.getSoluongton());
		lspNew.setThoigianbh(loaiSanPham.getThoigianbh());
		lspNew.setHang(hang);
		lspNew.setRamat(loaiSanPham.getRamat());
		lspNew.setGia(loaiSanPham.getGia());
		this.loaiSanPhamService.save(lspNew);
		return new ResponseEntity(new ApiResponse(true, "Thêm sản phẩm thành công!"), HttpStatus.OK);
	}
	
//	@GetMapping("/loaisanpham/{id}")
//	public ResponseEntity<LoaiSanPham> getLoaiSanPhamById(@PathVariable String id){
//		try {
//			LoaiSanPham loaiSanPham = loaiSanPhamService.getLoaiSanPhamById(id);
//			return new ResponseEntity<LoaiSanPham>(loaiSanPham, HttpStatus.OK);
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//			return new ResponseEntity<LoaiSanPham>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	@GetMapping("/loaisanpham/{id}")
	public ResponseEntity<LoaiSanPhamResponse> getLoaiSanPhamById(@PathVariable String id){
		try {
			LoaiSanPham loaiSanPham = loaiSanPhamService.getLoaiSanPhamById(id);
			LoaiSanPhamResponse lsp = new LoaiSanPhamResponse();
			lsp.setAnh(loaiSanPham.getAnh());
			lsp.setChip(loaiSanPham.getChip());
			lsp.setGia(loaiSanPham.getGia());
			lsp.setHedieuhanh(loaiSanPham.getMahdh());
			lsp.setMahang(loaiSanPham.getHang().getTenhang());
			lsp.setMaloai(loaiSanPham.getMaloai());
			lsp.setManhinh(loaiSanPham.getManhinh());
			lsp.setMota(loaiSanPham.getMota());
			lsp.setPin(loaiSanPham.getPin());
			lsp.setRam(loaiSanPham.getRam().getDungluong());
			lsp.setRom(loaiSanPham.getRom().getDungluong());
			lsp.setSoluongton(loaiSanPham.getSoluongton());
			lsp.setTenloai(loaiSanPham.getTenloai());
			lsp.setThoigianbh(loaiSanPham.getThoigianbh());
			lsp.setRamat(loaiSanPham.getRamat());
			lsp.setGiamgia(loaiSanPham.getCtGiamGiaLSP());
			return new ResponseEntity<LoaiSanPhamResponse>(lsp, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<LoaiSanPhamResponse>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/loaisanpham/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> updateLoaiSanPham(@RequestBody LoaiSanPhamRequest loaiSanPham,
			@PathVariable String id){
		try {
			LoaiSanPham loaiSP = loaiSanPhamService.getLoaiSanPhamById(id);
			Hang hang = hangService.getHangById(loaiSanPham.getMahang());
			Ram ram = ramService.getRamById(loaiSanPham.getRam());
			Rom rom = romService.getRomById(loaiSanPham.getRom());
			loaiSP.setTenloai(loaiSanPham.getTenloai());
			loaiSP.setAnh(loaiSanPham.getAnh());
			loaiSP.setMota(loaiSanPham.getMota());
			loaiSP.setChip(loaiSanPham.getChip());
			loaiSP.setRam(ram);
			loaiSP.setRom(rom);
			loaiSP.setMahdh(loaiSanPham.getHedieuhanh());
			loaiSP.setPin(loaiSanPham.getPin());
			loaiSP.setManhinh(loaiSanPham.getManhinh());
			loaiSP.setSoluongton(loaiSanPham.getSoluongton());
			loaiSP.setThoigianbh(loaiSanPham.getThoigianbh());
			loaiSP.setHang(hang);
			loaiSP.setRamat(loaiSanPham.getRamat());
			loaiSanPhamService.save(loaiSP);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Chỉnh sửa sản phẩm thành công!"), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Chỉnh sửa sản phẩm thất bại!"),
                    HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/loaisanpham/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean deleteLoaiSanPhamById(@PathVariable String id){
		LoaiSanPham lsp = loaiSanPhamService.getLoaiSanPhamById(id);
		if(lsp.getSoluongton().equals(0)){
			loaiSanPhamService.deleteLoaiSanPhamById(id);
			return true;
		} else return false;
	}
}
