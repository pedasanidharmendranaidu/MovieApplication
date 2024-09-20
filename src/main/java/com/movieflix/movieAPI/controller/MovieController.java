package com.movieflix.movieAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieflix.movieAPI.Dto.MovieDto;
import com.movieflix.movieAPI.service.MovieService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie/")
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);


    @Autowired
    private MovieService movieService;


    @PostMapping("add")
    public ResponseEntity<MovieDto> addMovieHandler(@RequestPart MultipartFile file, @RequestPart String movieDto) throws IOException {
        logger.info("info loggeer from movie controller class");
        logger.info(" addMovieHandler mthod from movie cintroller::");
        MovieDto dto = convetToMovieDto(movieDto);

        return new ResponseEntity<>(movieService.addMovie(dto, file), HttpStatus.CREATED);
    }

    private MovieDto convetToMovieDto(String movieDtoObj) throws JsonProcessingException {
        //   MovieDto movieDto=new MovieDto();
        logger.info("info loggeer from movie controller class");
        logger.info(" convert to movie dto mthod from movie cintroller::");

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(movieDtoObj, MovieDto.class);
    }

    @GetMapping("/{movieid}")
    public ResponseEntity<MovieDto> getMovieByMovieHanler(@PathVariable(name = "movieid") Integer movieId) {

        logger.info("info loggeer from movie controller class");
        logger.info(" getmoviebymoviehandler mthod from movie controller::");


        return new ResponseEntity<>(movieService.getMovie(movieId), HttpStatus.OK);
    }

    @GetMapping("getAllMovies/{get}")
    public ResponseEntity<List<MovieDto>> getAllMoviesHandler(@RequestParam(name = "get", required = false) Integer id) {
        logger.info("info loggeer from movie controller class");
        logger.info(" gethandlerMovieHandler mthod from movie cintroller::");
//        if (id != null) {
//    return     new ResponseEntity<>(movieService.getMovie(id), HttpStatus.OK);
//        }

        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);


    }

    @PutMapping("update/{movieId}")
    public ResponseEntity<MovieDto> updateMovieHandler(@PathVariable Integer movieId,
                                                       @RequestPart MultipartFile file,
                                                       @RequestPart String movieDtoObj) throws IOException {

        logger.info("info loggeer from movie controller class");
        logger.info("updaate movie MovieHandler mthod from movie cintroller::");


        if (file.isEmpty()) file = null;
        logger.info("info loggeer from movie controller class");
        // logger.info(" gethandlerMovieHandler mthod from movie cintroller::");

        logger.warn(" file checking from movie controller ");
        MovieDto movieDto = convetToMovieDto(movieDtoObj);
        return ResponseEntity.ok(movieService.updateMovie(movieId, movieDto, file));
    }

    @DeleteMapping("delete/{movieId}")
    public ResponseEntity<String> deleteMovieHandler(@PathVariable Integer movieId) throws IOException {

        logger.info("info loggeer from movie controller class");
        logger.info(" deleteMovieHandler mthod from movie controller::");


        return ResponseEntity.ok(movieService.deleteMovie(movieId));
    }

    @GetMapping("tittle/{tittle}")
    ResponseEntity<List<MovieDto>> fetchMovieTittleHandler(@PathVariable(name = "tittle") String tittle) {
        return new ResponseEntity<>(movieService.fetchMovieTittle(tittle), HttpStatus.OK);
    }


}
