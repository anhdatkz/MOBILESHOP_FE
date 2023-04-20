package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.GioHang;

public interface GioHangRepository extends JpaRepository<GioHang, Integer>{
	@Query("select gh from GioHang gh where gh.khachHangGH.taiKhoanKH.matk= :matk")
	public GioHang getGioHangByMaKH(@Param("matk") String matk);
	
//	@Query("select gh from GioHang gh where gh.trangThai.matrangthai = :matrangthai")
//	public List<GioHang> getGioHangByTrangThai(@Param("matrangthai") Integer matrangthai);
	
//	@Query("select gh from GioHang gh where gh.ngay between :ngaybd and :ngaykt ")
//	public List<GioHang> getGioHangByDate(@Param("ngaybd") Date ngaybd, @Param("ngaykt") Date ngaykt);
}
