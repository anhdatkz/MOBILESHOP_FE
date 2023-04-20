package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.CTGH;
import com.example.entities.CTGHKeys;

@Repository
public interface CTGHRepository extends JpaRepository<CTGH, CTGHKeys>{
	@Query("select ctgh from CTGH ctgh where ctgh.id.idgiohangctgh = :idgh")
	public List<CTGH> getCTGHByIdGH(@Param("idgh") Integer idgh);
	
	@Query("select ctgh from CTGH ctgh where ctgh.gioHangCTGH.khachHangGH.taiKhoanKH.matk = :matk")
	public List<CTGH> getCTGHByMaTK(@Param("matk") String matk);
	
	@Modifying
	@Query("delete from CTGH ctgh where ctgh.id.idgiohangctgh = :idgh")
	public void deleteCTGHByIdGH(@Param("idgh") Integer idgh);
}
