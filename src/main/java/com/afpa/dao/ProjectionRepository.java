package com.afpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.afpa.entities.Projection;

@RepositoryRestResource
public interface ProjectionRepository extends JpaRepository<Projection, Long>{

}
