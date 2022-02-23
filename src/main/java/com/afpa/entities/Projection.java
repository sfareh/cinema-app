package com.afpa.entities;

import java.util.Collection;
import java.util.Date;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Projection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date projectionDate;
	private double price;

	@ManyToOne
	private Room room;

	@ManyToOne
	private Movie movie;
	
	@OneToMany(mappedBy="projection")
	private Collection<Ticket> tickets;
	
	@ManyToOne
	private Screening screening;
}
