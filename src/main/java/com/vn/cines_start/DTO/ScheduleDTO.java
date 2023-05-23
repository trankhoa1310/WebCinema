package com.vn.cines_start.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

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
public class ScheduleDTO implements Serializable {
	 	private Long id;
	    private String name;
	    private boolean status;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private LocalDate showDate;
	    private LocalTime showTime;
	    
	    private Long roomIds;
	    private Long movieIds;
}
