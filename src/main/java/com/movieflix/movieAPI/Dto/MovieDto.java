package com.movieflix.movieAPI.Dto;

import jakarta.persistence.CollectionTable;

import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@Setter
@Getter
public class MovieDto {
    @NotBlank(message = " must be enter: ")
    private Integer movieId;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    @NotBlank(message = "tittle name  must be enter: ")
    private String tittle;

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    @NotBlank(message = "studio  must be enter: ")
    private String studio;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @NotBlank(message = "director name  must be enter: ")
    private String director;

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Set<String> getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(Set<String> movieCast) {
        this.movieCast = movieCast;
    }

    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;


    private Integer releaseYear;

    @NotBlank(message = "tittle must be enter: ")
    private String poster;

    @NotBlank(message = "poster url must be enter: ")
    private String posterUrl;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public MovieDto(Integer movieId, String tittle, String studio, String director, Set<String> movieCast, Integer releaseYear, String poster, String posterUrl) {
        this.movieId = movieId;
        this.tittle = tittle;
        this.movieCast = movieCast;
        this.poster = poster;
        this.releaseYear = releaseYear;
        this.director = director;
        this.studio = studio;
        this.posterUrl = posterUrl;
    }
}
