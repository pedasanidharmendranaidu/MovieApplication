package com.movieflix.movieAPI.service;

import com.movieflix.movieAPI.Dto.MovieDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface MovieService {

 public MovieDto   addMovie(MovieDto movieDto, MultipartFile file) throws IOException;

 public MovieDto getMovie(Integer movieId);


  public List<MovieDto> getAllMovies();
public MovieDto updateMovie(Integer movieId,MovieDto movieDto, MultipartFile file) throws IOException;
public String deleteMovie(Integer movieId) throws IOException;

}
