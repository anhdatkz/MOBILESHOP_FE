package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String>{
	@Query("select nv from NhanVien nv where nv.taiKhoanNV.matk=:matk")
	public NhanVien getKHByMaTK(@Param("matk") String matk);
}
