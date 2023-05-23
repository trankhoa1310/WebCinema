package com.vn.cines_start.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean status;
    private LocalDate showDate;
    private LocalTime showTime;
    private LocalTime endTime;
    
//    LocalDateTime a;
    

	/*
	 * @OneToMany(mappedBy = "schedules") // @JoinColumn(name = "ticket_id",
	 * referencedColumnName = "schedule_id") private Set<Room> rooms;
	 */
    
    @ManyToOne(/* cascade = CascadeType.ALL */)
    @JoinColumn(name = "room_id")
    private Room rooms;

    @OneToMany(mappedBy = "schedules",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
//    @JoinColumn(name = "ticket_id", referencedColumnName = "schedule_id")
    private Set<Ticket> tickets;

	@ManyToOne(/* cascade = CascadeType.ALL */)
    @JoinColumn(name = "movie_id")
    private Movie movies;

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", name=" + name + ", status=" + status + ", showDate=" + showDate + ", showTime="
				+ showTime + ", rooms=" + rooms + ", tickets=" + tickets + ", movies=" + movies + "]";
	}
	
	
}
