package com.afpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.afpa.entities.Room;

@RepositoryRestResource
public interface RoomRepository extends JpaRepository<Room, Long>{

}
