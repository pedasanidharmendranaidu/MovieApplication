package com.movieflix.movieAPI.service;

import com.movieflix.movieAPI.Dto.MovieDto;
import com.movieflix.movieAPI.controller.MovieController;
import com.movieflix.movieAPI.entity.Movie;
import com.movieflix.movieAPI.exceptions.MovieNotFoundException;
import com.movieflix.movieAPI.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);



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

        logger.info("logger from the movieServiceImle class ");
        logger.info("from addMovie method implementation::");
        String uploadedFile = fileService.uploadFile(path, file);
        movieDto.setPoster(uploadedFile);
        // 3. map dto to Movie object
        Movie movie = new Movie(
                null,
                movieDto.getTittle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster()
        );

        // 4. save the movie object -> saved Movie object
        Movie savedMovie = movieRepository.save(movie);

        // 5. generate the posterUrl
        String posterUrl = baseUrl + "/file" + uploadedFile;

        // 6. map Movie object to DTO object and return it
        MovieDto response = new MovieDto(
                savedMovie.getMovieId(),
                savedMovie.getTittle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getReleaseYear(),
                savedMovie.getPoster(),
                posterUrl
        );

        return response;
    }


    @Override
    public MovieDto getMovie(Integer movieId) {
        logger.info("logger from the movieServiceImle class ");
        logger.info("from getMovie method implementation::");
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found with id = " + movieId));
        String posterUrl = baseUrl + "/file" + movie.getPoster();
        MovieDto response = new MovieDto(
                movie.getMovieId(),
                movie.getTittle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );
        logger.info("logger from the movieServiceImle class ");
        logger.info("from getMovie method implementation::");
        return response;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        logger.info("logger from the movieServiceImle class ");
        logger.info("from getAllMovie method implementation::");
        List<Movie> movieList = movieRepository.findAll();

        List<MovieDto> movieDtos = new ArrayList<>();

        for (Movie movie : movieList) {
            String posterUrl = baseUrl + "/file" + movie.getPoster();
            MovieDto response = new MovieDto(
                    movie.getMovieId(),
                    movie.getTittle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    posterUrl
            );
            movieDtos.add(response);

        }
        return movieDtos;

    }


    @Override
    public MovieDto updateMovie(Integer movieId, MovieDto movieDto, MultipartFile file) throws IOException {
        logger.info("logger from the movieServiceImle class ");
        logger.info("from updateMovie method implementation::");
        Movie mv = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id = " + movieId));

        String fileName = mv.getPoster();
        if (file != null) {
            logger.info("logger from the movieServiceImle class ");
            logger.warn("from getMovie method implementation::");
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            fileName = fileService.uploadFile(path, file);
        }
        logger.info("logger from the movieServiceImle class ");
        logger.info("from getMovie method implementation::");

        movieDto.setPoster(fileName);


        Movie movie = new Movie(
                mv.getMovieId(),
                movieDto.getTittle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster()
        );


        Movie updatedMovie = movieRepository.save(movie);


        String posterUrl = baseUrl + "/file/" + fileName;


        MovieDto response = new MovieDto(
                movie.getMovieId(),
                movie.getTittle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );

        return response;
    }

    @Override
    public String deleteMovie(Integer movieId) throws IOException {

        Movie mv = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id = " + movieId));
        Integer id = mv.getMovieId();


        Files.deleteIfExists(Paths.get(path + File.separator + mv.getPoster()));


        movieRepository.delete(mv);

        return "Movie deleted with id = " + id;
    }



}
