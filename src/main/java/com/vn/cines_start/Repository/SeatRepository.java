package com.vn.cines_start.Repository;

import com.vn.cines_start.Model.Seat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface SeatRepository extends JpaRepository<Seat,Long> {
	@Query(value = "select s.row_seat from Seats s where s.room_id =?1 group by s.row_seat",nativeQuery = true)
	List<Integer> findAllRowSeatsByRoomId(Long roomId);
	
	@Query(value = "select * from Seats s where s.room_id =?1",nativeQuery = true)
	List<Seat> findAllSeatsByRoomId(Long roomId);
	
	@Query(value = "select * from Seats s where s.room_id =?1 and s.category_name=?2",nativeQuery = true)
	Seat findAllSeatsByRoomIdAndName(Long roomId,String name);
	
	@Query(value = "select * from Seats s where s.room_id =?1 and s.column_seat=?2 and s.row_seat=?3",nativeQuery = true)
	Seat findSeatsByRoomIdAndColAndRow(Long roomId, int col, int row);
}
