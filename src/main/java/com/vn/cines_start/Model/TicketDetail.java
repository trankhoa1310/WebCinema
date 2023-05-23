/*
 * package com.vn.cines_start.Model; import java.time.LocalDate; import
 * java.util.Set;
 * 
 * import javax.persistence.Entity; import javax.persistence.GeneratedValue;
 * import javax.persistence.GenerationType; import javax.persistence.Id; import
 * javax.persistence.JoinColumn; import javax.persistence.ManyToOne; import
 * javax.persistence.OneToMany; import javax.persistence.Table;
 * 
 * 
 * import lombok.AllArgsConstructor; import lombok.Getter; import
 * lombok.NoArgsConstructor; import lombok.Setter;
 * 
 * 
 * @Entity
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @Table(name = "TicketDetail") public class TicketDetail {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY)
 * 
 * private Long id;
 * 
 * @ManyToOne()
 * 
 * @JoinColumn(name = "seat_id") private Seat seats;
 * 
 * @OneToMany(mappedBy = "ticketdetails") private Set<Ticket> tickets; }
 */