package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.KhachHang;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, String>{

	@Query("select kh from KhachHang kh where kh.taiKhoanKH.matk=:matk")
	public KhachHang getKHByMaTK(@Param("matk") String matk);
}
