package com.vn.cines_start.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  
    private Long id;
    private Long price;
    private LocalDate createdAt;
//    @OneToOne
//    @JoinColumn(name = "seat_id", referencedColumnName = "ticket_id")
//    private Seat seats;

    @ManyToOne()
    @JoinColumn(name = "schedule_id")
    private Schedule schedules  ;

	/*
	 * @ManyToOne()
	 * 
	 * @JoinColumn(name = "ticketdetail_id") private TicketDetail ticketdetails;
	 */

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User users;
    
    
    @ManyToMany(cascade= {CascadeType.MERGE},fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
       name="ticket_seat",
       joinColumns={@JoinColumn(name="ticket_id", referencedColumnName="id")},
       inverseJoinColumns={@JoinColumn(name="seat_id", referencedColumnName="id")})
	private List<Seat> seats;

}
