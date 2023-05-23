package com.vn.cines_start.Service;

import com.vn.cines_start.Model.Movie;
import com.vn.cines_start.Model.Seat;
import com.vn.cines_start.Repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface ISeatService {
    List<Seat> findAll();
    Seat create(Seat seat);
	Optional<Seat> findSeatById(Long id);
	Seat edit(Seat seat);
	void delete(Long id);
	
	
	List<Integer> findAllRowSeatsByRoomId(Long roomId);
	
	List<Seat> findAllSeatsByRoomId(Long roomId);
	
	Seat findAllSeatsByRoomIdAndName(Long roomId,String name);
	
	Seat findSeatsByRoomIdAndColAndRow(Long roomId, int col, int row);
}
