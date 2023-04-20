package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.CTDH;
import com.example.entities.CTDHKeys;

@Repository
public interface CTDHRepository extends JpaRepository<CTDH, CTDHKeys>{
	@Query("select ctdh from CTDH ctdh where ctdh.id.madhctdh = :madh")
	public List<CTDH> getCTDHByMaDH(@Param("madh") Integer madh);
}
