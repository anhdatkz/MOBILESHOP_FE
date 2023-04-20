package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.TaiKhoan;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String>{
	
	@Query(value = "select tk from TaiKhoan tk where tk.matk = :matk and tk.password = :password")
	public TaiKhoan getTaiKhoanByUsernameAndPassword(@Param("matk") String matk, @Param("password") String password);
}
