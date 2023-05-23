package com.vn.cines_start.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id ;

    private String name;
    
    private int capacity;
    
    private boolean status;

    @OneToMany(mappedBy = "rooms")
    private Set<Seat> seats;

    /*@OneToOne()
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;*/

	/*
	 * @ManyToOne()
	 * 
	 * @JoinColumn(name = "schedule_id") private Schedule schedules;
	 */
    
    @OneToMany(mappedBy = "rooms")
    private Set<Schedule> schedules;


}
