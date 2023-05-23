package com.vn.cines_start.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String actors;
    private String content;
    private String categories;
    private String director;
    private String producer;
    private String imageURl;
    private int duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private boolean status;
    private boolean deleted;
    
    @OneToMany(mappedBy = "movies")
//    @JoinColumn(name = "schedule_id",referencedColumnName = "movie_id")
    private Set<Schedule> schedules;
}
