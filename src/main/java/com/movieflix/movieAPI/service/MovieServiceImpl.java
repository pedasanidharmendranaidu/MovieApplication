package com.movieflix.movieAPI.service;

import com.movieflix.movieAPI.Dto.MovieDto;
import com.movieflix.movieAPI.entity.Movie;
import com.movieflix.movieAPI.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private FileService fileService;

    @Value("${project.poster}")
    private String path;

    @Value("${base.url}")
private String baseUrl;
    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
        String uploadedFile = fileService.uploadFile(path, file);
movieDto.setPoster(uploadedFile);
        Movie movie=new Movie();
movie.setMovieId(movieDto.getMovieId());
movie.setTittle(movieDto.getTittle());
movie.setMovieCast(movieDto.getMovieCast());
movie.setPoster(movieDto.getPoster());
movie.setDirector(movieDto.getDirector());
movie.setStudio(movieDto.getStudio());
movie.setReleaseYear(movieDto.getReleaseYear());
        Movie savedmovie = movieRepository.save(movie);

        String posterUrl= baseUrl + "/file/" +uploadedFile;

MovieDto movieDto1=new MovieDto();


movieDto1.getTittle();
movieDto1.getReleaseYear();
movieDto1.getDirector();
movieDto1.getMovieCast();
movieDto1.getPoster();
movieDto1.getStudio();
movieDto1.getPosterUrl();
        movieDto1.getPosterUrl();

        return movieDto1;
    }



    @Override
    public MovieDto getMovie(Integer movieId) {
        return null;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return List.of();
    }
}
