package com.vn.cines_start.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.vn.cines_start.Model.Seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QRDetailsPOJO {
	
	private Long id;
    private LocalDate createdAt;
    private String firstName;
    private String lastName;
    private String movieName;
    private String roomName;
    private LocalDate showDate;
    private LocalTime showTime;
    private String seatList;
	private String userName;

}