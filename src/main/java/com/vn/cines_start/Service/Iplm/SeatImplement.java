package com.vn.cines_start.Service.Iplm;

import com.vn.cines_start.Model.Movie;
import com.vn.cines_start.Model.Seat;
import com.vn.cines_start.Repository.SeatRepository;
import com.vn.cines_start.Service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatImplement implements ISeatService {
    @Autowired
    private SeatRepository seatRepository;
    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public Seat create(Seat seat) {
        return seatRepository.save(seat);
    }
    
    @Override
    public Optional<Seat> findSeatById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public Seat edit(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void delete(Long id) {
    	seatRepository.deleteById(id);;
    }
    
    @Override
    public List<Integer> findAllRowSeatsByRoomId(Long roomId){
    	return seatRepository.findAllRowSeatsByRoomId(roomId);
    }
    @Override
    public List<Seat> findAllSeatsByRoomId(Long roomId){
    	return seatRepository.findAllSeatsByRoomId(roomId);
    }

    @Override
    public Seat findAllSeatsByRoomIdAndName(Long roomId, String name){
    	return seatRepository.findAllSeatsByRoomIdAndName(roomId,name);
    }
    
    @Override
    public Seat findSeatsByRoomIdAndColAndRow(Long roomId, int col, int row){
    	return seatRepository.findSeatsByRoomIdAndColAndRow(roomId,col,row);
    }
}
