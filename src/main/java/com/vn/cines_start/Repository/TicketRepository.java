package com.vn.cines_start.Repository;

import com.vn.cines_start.Model.Schedule;
import com.vn.cines_start.Model.Ticket;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface TicketRepository extends JpaRepository<Ticket,Long> {
	@Query(value = "select ts.seat_id from tickets as t join ticket_seat as ts on t.id=ts.ticket_id where t.schedule_id=?1", nativeQuery = true)
	List<Long> showListSeatId(Long scheduleId);
}
