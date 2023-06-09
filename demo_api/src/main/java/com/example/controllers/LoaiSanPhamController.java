package com.example.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.CTDH;
import com.example.entities.DonHang;
import com.example.entities.Hang;
import com.example.entities.LoaiSanPham;
import com.example.entities.Ram;
import com.example.entities.Rom;
import com.example.payload.ApiResponse;
import com.example.payload.LoaiSanPhamRequest;
import com.example.payload.LoaiSanPhamResponse;
import com.example.repository.LoaiSanPhamRepository;
import com.example.service.CTDHService;
import com.example.service.DonHangService;
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
	private LoaiSanPhamRepository loaiSanPhamRepository;
	
	@Autowired
	private HangService hangService;
	
	@Autowired
	private RamService ramService;
	
	@Autowired
	private RomService romService;
	
	@Autowired
	private CTDHService ctdhService;
	
	@Autowired
	private DonHangService donHangService;
	
//	@GetMapping("/loaisanpham")
//	public List<LoaiSanPham> getAllLoaiSanPham(){
//		return this.loaiSanPhamService.get8LSP();
//	}
	
	@GetMapping("/loaisanpham")
	public List<LoaiSanPham> getAllLoaiSanPham(
			@RequestParam(value = "ram", required = false) Integer ram,
			@RequestParam(value = "rom", required = false) Integer rom,
			@RequestParam(value = "hang", required = false) Integer hang){
		return this.loaiSanPhamService.get8LSP();
	}
	
	@GetMapping("lsp/pageable")
	public Page<LoaiSanPham> testPage(@Param(value = "page") int page, @Param(value = "size") int size){
		Pageable requestedPage = PageRequest.of(page, size);
		Page<LoaiSanPham> products = loaiSanPhamRepository.findAll(requestedPage);
		return products;
	}
	
	@GetMapping("/lspnew")
	public List<LoaiSanPham> getAllLoaiSanPhamNew(){
		return this.loaiSanPhamService.get8LSPNew();
	}
	
	@GetMapping("/lspkm")
	public List<LoaiSanPham> getAllLoaiSanPhamKM(){
		List<LoaiSanPham> alllsp = loaiSanPhamService.listAll();
		List<LoaiSanPham> lspKM = new ArrayList<LoaiSanPham>();
		
		for(LoaiSanPham lsp : alllsp){
			if(lsp.getCtGiamGiaLSP().size() > 0) {
				lspKM.add(lsp);
			}
		}
		if(lspKM.size() > 8) return lspKM.subList(0, 8);
		else return lspKM;
	}
	
	@GetMapping("/lspbestseller")
	public List<LoaiSanPham> getAllLoaiSanPhamBestSale(){
		List<CTDH> ctdhs = ctdhService.listAll();
		List<LoaiSanPham> alllsp = loaiSanPhamService.listAll();
		List<LoaiSanPham> lspBestSale = new ArrayList<LoaiSanPham>();
		Map<String , Integer> mapLSP = new HashMap<>();
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        
        for(LoaiSanPham lsp : alllsp){
			Integer soluong = 0;
			for(CTDH ctdh : ctdhs){
				DonHang donHang = donHangService.getDonHangById(ctdh.getId().getMadhctdh());
				if(donHang.getTrangThai().getMatrangthai().equals(3)){
					System.out.println("============================================"+donHang.getMadh());
					if(ctdh.getId().getMaloaictdh().equals(lsp.getMaloai())){
						soluong++;
					}
				}
			}
			mapLSP.put(lsp.getMaloai(), soluong);
		}
        
//		for(LoaiSanPham lsp : alllsp){
//			Integer soluong = 0;
//			for(CTDH ctdh : ctdhs){
//				if(ctdh.getId().getMaloaictdh().equals(lsp.getMaloai())){
//					soluong++;
//				}
//			}
//			mapLSP.put(lsp.getMaloai(), soluong);
//		}
        
		for (Map.Entry<String, Integer> entry : mapLSP.entrySet()) {
            list.add(entry.getValue());
        }
		
		Collections.sort(list);
        Collections.reverse(list);
        for (int num : list) {
            for (Entry<String, Integer> entry : mapLSP.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
		
		Set<String> setLSP = sortedMap.keySet();
        for (String key : setLSP) {
        	if(mapLSP.get(key) > 0){
        		lspBestSale.add(loaiSanPhamService.getLoaiSanPhamById(key.trim()));
        		System.out.println(key + " : " + mapLSP.get(key));
        	}
//        	System.out.println(key + " : " + mapLSP.get(key));
        }
        if(lspBestSale.size() > 8) return lspBestSale.subList(0, 7);
		else return lspBestSale;
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
	
//	@GetMapping("/filterlsp")
//	public List<LoaiSanPham> filterLSP(
//			@RequestParam(value = "maram", required = false) Integer maram,
//			@RequestParam(value = "marom", required = false) Integer marom){
//		List<LoaiSanPham> lsp = loaiSanPhamService.listAll();
//		
//		
//		return lsp;
//	}
	
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
