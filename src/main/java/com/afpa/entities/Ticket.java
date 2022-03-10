package com.afpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	private boolean reserved;
	
	@Column(unique=true, nullable=true)
	private Integer payment_id;
	
	
	@ManyToOne
	private Seat seat;
	
	@ManyToOne
	private Client client;
	
	@ManyToOne
	private Projection projection;
}
