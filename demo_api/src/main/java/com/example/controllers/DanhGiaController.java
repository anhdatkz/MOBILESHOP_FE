package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.CTDH;
import com.example.entities.DanhGia;
import com.example.entities.DanhGiaKeys;
import com.example.entities.DonHang;
import com.example.entities.KhachHang;
import com.example.entities.LoaiSanPham;
import com.example.payload.ApiResponse;
import com.example.payload.DanhGiaRequest;
import com.example.payload.DanhGiaResponse;
import com.example.service.CTDHService;
import com.example.service.DanhGiaService;
import com.example.service.DonHangService;
import com.example.service.KhachHangService;
import com.example.service.LoaiSanPhamService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class DanhGiaController {
	@Autowired
	private DanhGiaService danhGiaService;
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	@Autowired
	private KhachHangService khachHangService;
	@Autowired
	private DonHangService donhangService;
	@Autowired
	private CTDHService ctdhService;
	
	@GetMapping("/danhgia")
	public List<DanhGia> getAllDanhGia(){
		return this.danhGiaService.listAll();
	}
	
	@GetMapping("/danhgia/{maloai}")
	public List<DanhGiaResponse> getDanhGiaByMaLoai(@PathVariable String maloai){
		List<DanhGia> danhGias = this.danhGiaService.getDanhGiaByMaLoai(maloai);
		LoaiSanPham loaiSanPham = loaiSanPhamService.getLoaiSanPhamById(maloai);
		List<DanhGiaResponse> danhGiaResponses = new ArrayList<DanhGiaResponse>();
		for(DanhGia danhGia : danhGias){
			DanhGiaResponse danhGiaResponse = new DanhGiaResponse();
			KhachHang khachHang = khachHangService.getKhachHangById(danhGia.getId().getCmnddg().trim()); 
			danhGiaResponse.setTenkh(khachHang.getTenkh());
			danhGiaResponse.setTenloai(loaiSanPham.getTenloai());
			danhGiaResponse.setDiem(danhGia.getDiem());
			danhGiaResponse.setMota(danhGia.getMota());
			danhGiaResponse.setNgaybinhluan(danhGia.getNgaybinhluan());
			danhGiaResponses.add(danhGiaResponse);
		}
		return danhGiaResponses;
	}
	
//	@PostMapping("/danhgia")
//	public DanhGiaResponse saveDanhGia(@RequestBody DanhGiaRequest danhGiaRequest){
//		DanhGiaResponse danhGiaResponse = new DanhGiaResponse();
//		DanhGia danhGia = new DanhGia();
//		DanhGiaKeys danhGiaKeys = new DanhGiaKeys();
//		LoaiSanPham loaiSanPham = loaiSanPhamService.getLoaiSanPhamById(danhGiaRequest.getMaloai());
//		KhachHang khachHang = khachHangService.getKhachHangById(danhGiaRequest.getCmnd().trim()); 
//		danhGiaResponse.setTenkh(khachHang.getTenkh());
//		danhGiaResponse.setTenloai(loaiSanPham.getTenloai());
//		danhGiaResponse.setDiem(danhGiaRequest.getDiem());
//		danhGiaResponse.setMota(danhGiaRequest.getMota());
//		danhGiaResponse.setNgaybinhluan(danhGiaRequest.getNgaybinhluan());
//		//save danh gia
//		danhGiaKeys.setCmnddg(danhGiaRequest.getCmnd());
//		danhGiaKeys.setMaloaidg(danhGiaRequest.getMaloai());
//		danhGia.setId(danhGiaKeys);
//		danhGia.setKhachHangDG(khachHang);
//		danhGia.setDiem(danhGiaRequest.getDiem());
//		danhGia.setMota(danhGiaRequest.getMota());
//		danhGia.setNgaybinhluan(danhGiaRequest.getNgaybinhluan());
//		danhGiaService.save(danhGia);
//		return danhGiaResponse;
//	}
	
	@PostMapping("/danhgia")
	public ResponseEntity<ApiResponse> saveDanhGia(@RequestBody DanhGiaRequest danhGiaRequest){
		boolean check = false;
		DanhGiaResponse danhGiaResponse = new DanhGiaResponse();
		DanhGia danhGia = new DanhGia();
		DanhGiaKeys danhGiaKeys = new DanhGiaKeys();
		LoaiSanPham loaiSanPham = loaiSanPhamService.getLoaiSanPhamById(danhGiaRequest.getMaloai());
		KhachHang khachHang = khachHangService.getKhachHangById(danhGiaRequest.getCmnd().trim()); 
		List<DonHang> donhang = donhangService.getAllDonHangByMaKH(khachHang.getTaiKhoanKH().getMatk().trim());
		for(DonHang dh : donhang){
			if (dh.getTrangThai().getMatrangthai() == 3) {
				List<CTDH> ctdhs = ctdhService.getCTDHByIdDH(dh.getMadh());
				for(CTDH ctdh : ctdhs){
					if(ctdh.getLoaiSanPhamCTDH().getMaloai().trim().equals(danhGiaRequest.getMaloai().trim())){
						check = true;
					}
				}
			}
		}
		
		if(check == false){
			return new ResponseEntity(new ApiResponse(false, "Bạn chưa mua sản phẩm này!"), HttpStatus.OK);
		}
		
		//save danh gia
		danhGiaKeys.setCmnddg(danhGiaRequest.getCmnd());
		danhGiaKeys.setMaloaidg(danhGiaRequest.getMaloai());
		if(danhGiaService.existByID(danhGiaKeys)){
			return new ResponseEntity(new ApiResponse(false, "Bạn đã đánh giá sản phẩm trước đó!"), HttpStatus.OK);
		}
		danhGia.setId(danhGiaKeys);
		danhGia.setKhachHangDG(khachHang);
		danhGia.setDiem(danhGiaRequest.getDiem());
		danhGia.setMota(danhGiaRequest.getMota());
		danhGia.setNgaybinhluan(danhGiaRequest.getNgaybinhluan());
		danhGiaService.save(danhGia);
		return new ResponseEntity(new ApiResponse(true, "Đánh giá thành công!"), HttpStatus.OK);
	}
}
