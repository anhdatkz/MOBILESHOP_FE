package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.CTDDH;
import com.example.entities.CTDDHKeys;

@Repository
public interface CTDDHRepository extends JpaRepository<CTDDH, CTDDHKeys>{
	
}
