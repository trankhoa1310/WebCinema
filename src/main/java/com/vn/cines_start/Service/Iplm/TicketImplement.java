package com.vn.cines_start.Service.Iplm;

import com.vn.cines_start.Model.Ticket;
import com.vn.cines_start.Model.User;
import com.vn.cines_start.Repository.TicketRepository;
import com.vn.cines_start.Service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
@Service
public class TicketImplement implements ITicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    
    @Override
    public Optional<Ticket> findTicketById(Long id) {
        return ticketRepository.findById(id);
    }



    @Override
    public Ticket edit(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long id) {
    	ticketRepository.deleteById(id);
    }
    
    @Override
    public List<Long> showListSeatId(Long scheduleId) {
    	return ticketRepository.showListSeatId(scheduleId);
    }	
    
}
