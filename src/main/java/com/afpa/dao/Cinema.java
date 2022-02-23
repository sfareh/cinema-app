package com.afpa.dao;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString 
public class Cinema {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double longitude, latitude, altitude;
	private int numberOfRooms;
	@OneToMany(mappedBy="rooms")
	private Collection<Room> rooms;
	@ManyToOne
	private City city;
}
