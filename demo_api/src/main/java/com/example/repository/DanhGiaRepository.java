package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.DanhGia;
import com.example.entities.DanhGiaKeys;

@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, DanhGiaKeys>{
	@Query("select dg from DanhGia dg where dg.id.maloaidg = :maloai")
	public List<DanhGia> getDanhGiaByMaLoai(@Param("maloai") String maloai);
}
