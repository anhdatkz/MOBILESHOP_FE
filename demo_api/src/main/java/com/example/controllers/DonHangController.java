package com.example.controllers;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.CTDH;
import com.example.entities.DonHang;
import com.example.entities.KhachHang;
import com.example.entities.LoaiSanPham;
import com.example.entities.NhanVien;
import com.example.entities.TrangThai;
import com.example.payload.ApiResponse;
import com.example.payload.DateRequest;
import com.example.payload.DoanhThuThang;
import com.example.payload.DonHangRequest;
import com.example.payload.DonHangResponse;
import com.example.payload.DonHangUpdateRequest;
import com.example.service.CTDHService;
import com.example.service.DonHangService;
import com.example.service.KhachHangService;
import com.example.service.LoaiSanPhamService;
import com.example.service.NhanVienService;
import com.example.service.TrangThaiService;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class DonHangController {
	@Autowired
	private DonHangService donhangService;
	
	@Autowired
	private CTDHService ctdhService;
	
	@Autowired
	private TrangThaiService trangThaiSerVice;
	
	@Autowired
	private KhachHangService khachHangService;
	
	@Autowired
	private NhanVienService nhanVienService;
	
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@GetMapping("/donhang")
	public List<DonHang> getAlldonhang(){
		return this.donhangService.listAll();
	}
	
	@PostMapping("/donhang")
	public Integer savedonhang(@RequestBody DonHangRequest donHangRequest){
		DonHang donhang = new DonHang();
		TrangThai trangThai = trangThaiSerVice.getTrangThaiById(donHangRequest.getMatrangthai());
		KhachHang khachHang = khachHangService.getKhachHangById(donHangRequest.getCmnd());
		donhang.setNgayLap(donHangRequest.getNgaylap());
		donhang.setTennguoinhan(donHangRequest.getTennguoinhan());
		donhang.setDiachinhan(donHangRequest.getDiachinhan());
		donhang.setSdtnguoinhan(donHangRequest.getSdtnguoinhan());
		donhang.setEmailnguoinhan(donHangRequest.getEmailnhan());
		donhang.setTongtien(donHangRequest.getTongtien());
		donhang.setTrangThai(trangThai);
		donhang.setKhachHang(khachHang);
		this.donhangService.save(donhang);
		return donhang.getMadh();
	}
	
	@GetMapping("/donhang/{id}")
	public ResponseEntity<DonHangResponse> getdonhangById(@PathVariable Integer id){
		try {
			DonHang donhang = donhangService.getDonHangById(id);
			KhachHang khachHang = donhang.getKhachHang();
			DonHangResponse donHangRespose = new DonHangResponse();
			donHangRespose.setMadh(donhang.getMadh());
			donHangRespose.setCmnd(khachHang.getCmnd());
			donHangRespose.setDiachinhan(donhang.getDiachinhan());
			donHangRespose.setManvduyet(donhang.getNhanVienDuyet().getManv().trim());
			donHangRespose.setManvgiao(donhang.getNhanVienGiao().getManv().trim());
			donHangRespose.setMatrangthai(donhang.getTrangThai().getMatrangthai());
			donHangRespose.setNgaylap(donhang.getNgayLap());
			donHangRespose.setSdtnguoinhan(donhang.getSdtnguoinhan());
			donHangRespose.setTennguoinhan(donhang.getTennguoinhan());
			donHangRespose.setTongtien(donhang.getTongtien());
			return new ResponseEntity<DonHangResponse>(donHangRespose, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<DonHangResponse>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/donhang/trangthai/{id}")
	public List<DonHang> getdonhangByTrangThai(@PathVariable Integer id){
		try {
			List<DonHang> donhang = donhangService.getDonHangByTrangThai(id);
			return donhang;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return (List<DonHang>) new ResponseEntity<List<DonHang>>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/donhang/kh/{username}")
	public List<DonHang> getdonhangByMaKH(@PathVariable String username){
		try {
			List<DonHang> donhang = donhangService.getAllDonHangByMaKH(username);
			return donhang;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return (List<DonHang>) new ResponseEntity<List<DonHang>>(HttpStatus.NOT_FOUND);
		}
	}

	
	@GetMapping("/doanhthu/{ngaybd}/{ngaykt}")
	public List<DoanhThuThang> getDoangThuByThang(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ngaybd, 
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ngaykt){
		try {
			List<DoanhThuThang> doanhthu = donhangService.getDoanhThuThang(ngaybd, ngaykt);
			return doanhthu;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return (List<DoanhThuThang>) new ResponseEntity<List<DoanhThuThang>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/donhang/{ngaybd}/{ngaykt}")
	public List<DonHang> getDonHangTheoNgay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ngaybd, 
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ngaykt) {
		try {
			List<DonHang> donhang = donhangService.getDonHangByDate(ngaybd, ngaykt);
			return donhang;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return (List<DonHang>) new ResponseEntity<List<DonHang>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/donhang")
	public ResponseEntity<ApiResponse> updateTrangThaiDH(@RequestBody Integer madh){
		DonHang donHang = donhangService.getDonHangById(madh);
		TrangThai trangThai = trangThaiSerVice.getTrangThaiById(3);
		donHang.setTrangThai(trangThai);
		donhangService.save(donHang);
		return new ResponseEntity(new ApiResponse(true, "Cập nhật trạng thái đơn hàng thành công!"), HttpStatus.OK);
	}
	
	@PutMapping("/donhang/{id}")
	public ResponseEntity<DonHangResponse> updatedonhang(@RequestBody DonHangUpdateRequest donHangUpdateRequest,
			@PathVariable Integer id){
		try {
			DonHang donhang = donhangService.getDonHangById(id);
			TrangThai trangThai = trangThaiSerVice.getTrangThaiById(donHangUpdateRequest.getMatrangthai());
			NhanVien nhanVienDuyet = nhanVienService.getNhanVienById(donHangUpdateRequest.getManvduyet());
			NhanVien nhanVienGiao = nhanVienService.getNhanVienById(donHangUpdateRequest.getManvgiao());
			KhachHang khachHang = donhang.getKhachHang();
			donhang.setTrangThai(trangThai);
			donhang.setNhanVienDuyet(nhanVienDuyet);
			donhang.setNhanVienGiao(nhanVienGiao);
			donhangService.save(donhang);
			
			DonHangResponse donHangRespose = new DonHangResponse();
			
			donHangRespose.setMadh(donhang.getMadh());
			donHangRespose.setCmnd(khachHang.getCmnd());
			donHangRespose.setDiachinhan(donhang.getDiachinhan());
			donHangRespose.setManvduyet(nhanVienDuyet.getManv().trim());
			donHangRespose.setManvgiao(nhanVienGiao.getManv().trim());
			donHangRespose.setMatrangthai(trangThai.getMatrangthai());
			donHangRespose.setNgaylap(donhang.getNgayLap());
			donHangRespose.setSdtnguoinhan(donhang.getSdtnguoinhan());
			donHangRespose.setTennguoinhan(donhang.getTennguoinhan());
			donHangRespose.setTongtien(donhang.getTongtien());
			
			List<CTDH> ctghs = ctdhService.getCTDHByIdGH(id);
			for(CTDH ctdh : ctghs){
				LoaiSanPham loaiSanPham = loaiSanPhamService.getLoaiSanPhamById(ctdh.getId().getMaloaictdh());
				Integer newslt = loaiSanPham.getSoluongton() - ctdh.getSoluong();
				loaiSanPham.setSoluongton(newslt);
				loaiSanPhamService.save(loaiSanPham);
			}
 			
			return new ResponseEntity<DonHangResponse>(donHangRespose, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<DonHangResponse>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/donhang/{id}")
	public void deletedonhangById(@PathVariable Integer id){
		donhangService.deleteDonHangById(id);;
	}
}
