package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.DonHang;
import com.example.payload.DoanhThuThang;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer>{
	@Query("select dh from DonHang dh where dh.khachHang.taiKhoanKH.matk= :matk")
	public List<DonHang> getAllDonHangByMaKH(@Param("matk") String cmnd);
	
	@Query("select dh from DonHang dh where dh.nhanVienGiao.manv = :manvgiao")
	public List<DonHang> getDonHangByMaNVG(@Param("manvgiao") String manvgiao);
	
	@Query("select dh from DonHang dh where dh.nhanVienGiao.manv = :manvgiao AND dh.trangThai.matrangthai=2")
	public List<DonHang> getDonHangByMaNVGPT(@Param("manvgiao") String manvgiao);
	
	@Query("select dh from DonHang dh where dh.trangThai.matrangthai = :matrangthai")
	public List<DonHang> getDonHangByTrangThai(@Param("matrangthai") Integer matrangthai);
	
	@Query("select dh from DonHang dh where dh.trangThai.matrangthai=3 and dh.ngaylap between :ngaybd and :ngaykt ")
	public List<DonHang> getDonHangByDate(@Param("ngaybd") Date ngaybd, @Param("ngaykt") Date ngaykt);
	
	@Query("select sum(dh.tongtien) from DonHang dh where dh.trangThai.matrangthai=3 "
			+ "and dh.ngaylap between :ngaybd and :ngaykt ")
	public Long getThongKeDoanhThu(@Param("ngaybd") Date ngaybd, @Param("ngaykt") Date ngaykt);
	
//	@Procedure(procedureName="GETDOANHTHUTHEOTHANG")
//	public List<DoanhThuThang> getThongKeByThang(Date ngaybd, Date ngaykt);
	@Procedure(procedureName="GETDOANHTHUTHEOTHANG")
	public List<DoanhThuThang> getThongKeByThang(@Param("ngaybd") Date ngaybd, @Param("ngaykt") Date ngaykt);
}
