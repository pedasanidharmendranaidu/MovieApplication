package com.movieflix.movieAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieflix.movieAPI.Dto.MovieDto;
import com.movieflix.movieAPI.service.MovieService;
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

    @Autowired
    private MovieService movieService;


@PostMapping("add")
   public ResponseEntity<MovieDto> addMovieHandler(@RequestPart MultipartFile file, @RequestPart String movieDto) throws IOException {

       MovieDto dto = convetToMovieDto(movieDto);

       return new ResponseEntity<>(movieService.addMovie(dto,file), HttpStatus.CREATED);
    }

private MovieDto convetToMovieDto(String movieDtoObj) throws JsonProcessingException {
    //   MovieDto movieDto=new MovieDto();

    ObjectMapper objectMapper=new ObjectMapper();
 return    objectMapper.readValue(movieDtoObj, MovieDto.class);
}
@GetMapping("/{movieid}")
    public ResponseEntity<MovieDto> getMovieByMovieHanler(@PathVariable(name = "movieid") Integer movieId){
    return new ResponseEntity<>(movieService.getMovie(movieId),HttpStatus.OK);
}

@GetMapping("getAllMovies")
    public ResponseEntity<List<MovieDto>> getAllMoviesHandler(){

return new ResponseEntity<>(movieService.getAllMovies(),HttpStatus.OK);
}

    @PutMapping("update/{movieId}")
    public ResponseEntity<MovieDto> updateMovieHandler(@PathVariable Integer movieId,
                                                       @RequestPart MultipartFile file,
                                                       @RequestPart String movieDtoObj) throws IOException {
        if (file.isEmpty()) file = null;
        MovieDto movieDto = convetToMovieDto(movieDtoObj);
        return ResponseEntity.ok(movieService.updateMovie(movieId, movieDto, file));
    }

    @DeleteMapping("delete/{movieId}")
    public ResponseEntity<String> deleteMovieHandler(@PathVariable Integer movieId) throws IOException {
        return ResponseEntity.ok(movieService.deleteMovie(movieId));
    }













}
