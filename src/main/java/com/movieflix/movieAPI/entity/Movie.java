package com.movieflix.movieAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Movie {


    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    @Column(nullable = false, length = 200)
    @NotBlank(message = "tittle name  must be enter: ")
    private String tittle;

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    @Column(nullable = false, length = 200)
    @NotBlank(message = "studio  must be enter: ")
    private String studio;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Column(nullable = false, length = 200)
    @NotBlank(message = "director name  must be enter: ")
    private String director;

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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

    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    @NotBlank(message = "tittle must be enter: ")
    private String poster;

    public Movie(Integer movieId, String tittle, String studio, String director, Set<String> movieCast, Integer releaseYear, String poster) {
        this.movieId = movieId;
        this.tittle = tittle;
        this.movieCast = movieCast;
        this.poster = poster;
        this.releaseYear = releaseYear;
        this.director = director;
        this.studio = studio;

    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", tittle='" + tittle + '\'' +
                ", studio='" + studio + '\'' +
                ", director='" + director + '\'' +
                ", movieCast=" + movieCast +
                ", releaseYear=" + releaseYear +
                ", poster='" + poster + '\'' +
                '}';
    }

}
