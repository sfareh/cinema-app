package com.afpa.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Seat {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int seatNumber;
	private double longitude, latitude, altitude;
}
