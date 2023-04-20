package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Rom;

@Repository
public interface RomRepository extends JpaRepository<Rom, Integer>{

}
