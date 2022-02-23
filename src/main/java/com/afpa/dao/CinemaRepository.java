package com.afpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afpa.entities.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long>{

	
}
