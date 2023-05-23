package com.vn.cines_start.Service.Iplm;

import com.vn.cines_start.Model.Movie;
import com.vn.cines_start.Repository.MovieRepository;
import com.vn.cines_start.Service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieImplement implements IMovieService {
    @Autowired
    MovieRepository movieRepository;
    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findMovieById(Long id) {
        return movieRepository.findById(id);
    }


    @Override
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie edit(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);;
    }

	@Override
	public List<Movie> findMovieByStatus(boolean status) {
		return movieRepository.findAllByStatus(status);
	}

	@Override
	public List<Movie> findAllByStatusAndDeleted(boolean status, boolean deleted) {
		return movieRepository.findAllByStatusAndDeleted(status, deleted);
	}


}
