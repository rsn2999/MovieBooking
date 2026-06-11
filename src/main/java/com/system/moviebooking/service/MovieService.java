package com.system.moviebooking.service;

import com.system.moviebooking.model.Movie;
import com.system.moviebooking.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(String id){
        return movieRepository.findById(id).orElse(null);
    }

    public Movie updateMovie(String id, Movie updatedMovie) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie != null) {
            movie.setTitle(updatedMovie.getTitle());
            movie.setAvailableSeats(updatedMovie.getAvailableSeats());
            movie.setPrice(updatedMovie.getPrice());
            return movieRepository.save(movie);
        }

        return null;
    }
    public String deleteMovie(String id) {
        movieRepository.deleteById(id);
        return "Movie deleted successfully";
    }
}
