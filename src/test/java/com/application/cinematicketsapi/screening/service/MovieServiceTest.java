package com.application.cinematicketsapi.screening.service;

import com.application.cinematicketsapi.common.exception.ResourceNotFoundException;
import com.application.cinematicketsapi.screening.model.Movie;
import com.application.cinematicketsapi.screening.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.catchException;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Test
    void GetMovie_ShouldReturnSearchedMovie_IfMovieIsFound() {
        // Given
        String searchedTitle = "TestTitle";
        Movie searchedMovie = Movie.builder().id(1L).build();
        given(movieRepository.findByTitle(Mockito.anyString())).willReturn(Optional.of(searchedMovie));

        // When
        Movie foundMovie = movieService.getMovie(searchedTitle);

        // Then
        then(foundMovie).isEqualTo(searchedMovie);
    }

    @Test
    void GetMovie_ShouldThrowException_IfMovieIsNotFound() {
        // Given
        String searchedTitle = "TestTitle";
        given(movieRepository.findByTitle(searchedTitle)).willReturn(Optional.empty());

        // When
        Exception e = catchException(() -> movieService.getMovie(searchedTitle));

        // Then
        then(e).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void GetMovieWithScreenings_ShouldReturnSearchedMovie_IfMovieIsFound() {
        // Given
        Long searchedId = 1L;
        Movie searchedMovie = Movie.builder().id(1L).build();
        given(movieRepository.findByIdWithScreenings(Mockito.anyLong())).willReturn(Optional.of(searchedMovie));

        // When
        Movie foundMovie = movieService.getMovieWithScreenings(searchedId);

        // Then
        then(foundMovie).isEqualTo(searchedMovie);
    }

    @Test
    void GetMovieWithScreenings_ShouldThrowException_IfMovieIsNotFound() {
        // Given
        Long searchedId = 1L;
        given(movieRepository.findByIdWithScreenings(Mockito.anyLong())).willReturn(Optional.empty());

        // When
        Exception e = catchException(() -> movieService.getMovieWithScreenings(searchedId));

        // Then
        then(e).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void GetAllMovies_ShouldReturnListWithMovies_IfMoviesAreFound() {
        // Given
        Movie movie1 = Movie.builder().id(1L).build();
        Movie movie2 = Movie.builder().id(2L).build();
        given(movieRepository.findAll()).willReturn(List.of(movie1, movie2));

        // When
        List<Movie> foundMovies = movieService.getAllMovies();

        // Then
        then(foundMovies).contains(movie1, movie2);
    }

    @Test
    void GetAllMovies_ShouldReturnEmptyList_IfNoMoviesAreFound() {
        // Given
        given(movieRepository.findAll()).willReturn(List.of());

        // When
        List<Movie> foundMovies = movieService.getAllMovies();

        // Then
        then(foundMovies).isEmpty();
    }

}