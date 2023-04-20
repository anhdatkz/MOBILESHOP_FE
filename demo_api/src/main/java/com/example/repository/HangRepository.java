package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.Hang;

@Repository
public interface HangRepository extends JpaRepository<Hang, String>{
	@Query("select h from Hang h where h.mahang= :id")
	public Hang getHangById(@Param("id") String mahang);
}
