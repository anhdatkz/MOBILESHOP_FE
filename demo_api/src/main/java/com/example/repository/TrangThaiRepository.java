package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.TrangThai;

@Repository
public interface TrangThaiRepository extends JpaRepository<TrangThai, Integer>{

}
