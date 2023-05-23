package com.vn.cines_start.Service;

import com.vn.cines_start.Model.Ticket;
import com.vn.cines_start.Model.User;

import java.util.List;
import java.util.Optional;

public interface ITicketService {
    List<Ticket> findAll();
    Ticket create(Ticket ticket);
	Optional<Ticket> findTicketById(Long id);
	Ticket edit(Ticket ticket);
	void delete(Long id);
	List<Long> showListSeatId(Long scheduleId);
}
