package com.vn.cines_start.Repository;

import com.vn.cines_start.Model.Movie;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

	List<Movie> findAllByStatus(boolean status);
	
	List<Movie> findAllByStatusAndDeleted(boolean status, boolean deleted); 
	
}
