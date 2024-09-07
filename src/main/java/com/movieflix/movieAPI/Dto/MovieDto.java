package com.movieflix.movieAPI.Dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class MovieDto {
    private Integer movieId;

    @NotBlank(message = "tittle name  must be enter: ")
    private String tittle;
    @NotBlank(message = "studio  must be enter: ")
    private String studio;
    @NotBlank(message = "director name  must be enter: ")
    private String director;
    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;
    @NotBlank(message = "date must be enter: ")
    private Integer releaseYear;
    @NotBlank(message = "tittle must be enter: ")
    private String poster;
    @NotBlank(message = "poster url must be enter: ")
    private String posterUrl;

}
