package com.vn.cines_start.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "billusers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billuser_id")
	private Long id;
    private LocalDate createdAt;
    private String firstName;
    private String lastName;
    private String movieName;
    private String roomName;
    private Long price;
    private LocalDate showDate;
    private LocalTime showTime;
    private String seatList;
    private String urlQR;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User users;
}
