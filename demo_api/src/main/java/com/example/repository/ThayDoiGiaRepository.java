package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.ThayDoiGia;
import com.example.entities.ThayDoiGiaKeys;

@Repository
public interface ThayDoiGiaRepository extends JpaRepository<ThayDoiGia, ThayDoiGiaKeys>{

}
