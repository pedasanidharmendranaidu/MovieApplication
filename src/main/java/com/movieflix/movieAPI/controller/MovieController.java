package com.movieflix.movieAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieflix.movieAPI.Dto.MovieDto;
import com.movieflix.movieAPI.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;


@PostMapping("/add-movie")
   public ResponseEntity<MovieDto> addMovieHandler(@RequestPart MultipartFile file, @RequestPart String movieDto) throws IOException {

       MovieDto dto = convetToMovieDto(movieDto);

       return new ResponseEntity<>(movieService.addMovie(dto,file), HttpStatus.CREATED);
    }

private MovieDto convetToMovieDto(String movieDtoObj) throws JsonProcessingException {
    //   MovieDto movieDto=new MovieDto();

    ObjectMapper objectMapper=new ObjectMapper();
 return    objectMapper.readValue(movieDtoObj, MovieDto.class);
}

}
