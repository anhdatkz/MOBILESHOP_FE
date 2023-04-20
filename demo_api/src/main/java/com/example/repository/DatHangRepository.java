package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.DatHang;

@Repository
public interface DatHangRepository extends JpaRepository<DatHang, String>{

}
