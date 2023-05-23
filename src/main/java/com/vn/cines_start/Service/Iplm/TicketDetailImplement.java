/*
 * package com.vn.cines_start.Service.Iplm;
 * 
 * import java.util.List; import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import com.vn.cines_start.Model.TicketDetail; import
 * com.vn.cines_start.Repository.TicketDetailRepository; import
 * com.vn.cines_start.Service.ITicketDetailService;
 * 
 * public class TicketDetailImplement implements ITicketDetailService{
 * 
 * @Autowired private TicketDetailRepository ticketDetailRepository;
 * 
 * @Override public List<TicketDetail> findAll() { return
 * ticketDetailRepository.findAll(); }
 * 
 * @Override public TicketDetail create(TicketDetail ticketDetail) { return
 * ticketDetailRepository.save(ticketDetail); }
 * 
 * @Override public Optional<TicketDetail> findTicketDetailById(Long id) {
 * return ticketDetailRepository.findById(id); }
 * 
 * 
 * 
 * @Override public TicketDetail edit(TicketDetail ticketDetail) { return
 * ticketDetailRepository.save(ticketDetail); }
 * 
 * @Override public void delete(Long id) {
 * ticketDetailRepository.deleteById(id); } }
 */