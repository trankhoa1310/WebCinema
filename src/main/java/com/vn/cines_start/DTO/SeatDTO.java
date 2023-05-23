package com.vn.cines_start.DTO;

import java.util.List;

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
public class SeatDTO {
	private Long id;
    private String categoryName;
    private boolean status;
    private double price;
    private Integer rowSeat;
    private Integer columnSeat;
    
    private Long roomIds;
}
