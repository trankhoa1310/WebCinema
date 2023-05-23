package com.vn.cines_start.Service;


import com.vn.cines_start.Model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    List<Movie> findAll();

    Optional<Movie> findMovieById(Long id);
    Movie create(Movie movie);

    Movie edit(Movie movie);

    void delete(Long id);
    
    List<Movie> findMovieByStatus(boolean status);
    
    List<Movie> findAllByStatusAndDeleted(boolean status, boolean deleted); 
}
